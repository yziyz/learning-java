package yz.learning.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Simple SSL chat client.
 *
 * @author 袁臻
 * 2017/09/12 19:30
 */
public class SecureChatClient {
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8992"));

    public static void main(String[] args) throws Exception {
        //Configure SSL.
        final SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE).build();

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new SecureChatClientInitializer(sslContext));

            //Start the connection attempt.
            Channel ch = b.connect(HOST, PORT).sync().channel();

            //Read commands from the stdin.
            ChannelFuture lastWriteFuture = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while (true) {
                if ((line = in.readLine()) == null) {
                    break;
                }

                //Send the received line to the server.
                lastWriteFuture = ch.writeAndFlush(line + "\r\n");

                //If user typed the 'bye' command, wait until the server closes the connection.
                if ("bye".equals(line.toLowerCase())) {
                    ch.closeFuture().sync();
                    break;
                }
            }

            //Wait until all messages are flushed before closing the channel.
            if ((lastWriteFuture != null)) {
                lastWriteFuture.sync();
            }

        } finally {
            //The connection is closed automatically on shutdown.
            group.shutdownGracefully();
        }
    }
}

/**
 * Creates a newly configured {@link ChannelPipeline} for a new channel.
 */
class SecureChatClientInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslContext;

    public SecureChatClientInitializer(SslContext sslContext) {
        this.sslContext = sslContext;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //Add SSL handler first to encrypt and decrypt everything,.
        //In this example, we use a bogus certificate in the server side and accept any invalid certificates int client side.
        //You will need something more complicated to identify both and server int the real world.
        pipeline.addLast(sslContext.newHandler(ch.alloc(), SecureChatClient.HOST, SecureChatClient.PORT));

        //On top of the SSL handler, add the text line codec.
        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new StringEncoder());

        //and then business logic
        pipeline.addLast(new SecureChatClientHandler());
    }
}

/**
 * Handles a client-side channel.
 */
class SecureChatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
