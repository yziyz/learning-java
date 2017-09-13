package yz.learning.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.util.concurrent.GlobalEventExecutor;

import javax.net.ssl.SSLException;
import java.net.InetAddress;
import java.security.cert.CertificateException;

/**
 * Simple SSL chat server.
 */
public class SecureChatServer {
    private static final int PORT = Integer.parseInt(System.getProperty("port", "8992"));

    public static void main(String[] args) throws CertificateException, SSLException, InterruptedException {
        SelfSignedCertificate ssc = new SelfSignedCertificate();
        SslContext sslContext = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();

        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new SecureChatServerInitializer(sslContext));

            b.bind(PORT).sync().channel().closeFuture().sync();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

/**
 * Handles a server-side channel.
 */
class SecureChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //Send the received message to all channels but the current one.
        channels.forEach(channel -> {
            if (channel != ctx.channel()) {
                channel.writeAndFlush("[" + ctx.channel().remoteAddress() + "] " + msg + "\n");
            } else {
                channel.writeAndFlush("[me] " + msg + "\n");
            }
        });

        //Close the connection if the client has send 'bye'.
        if ("bye".equals(msg.toLowerCase())) {
            ctx.close();
            System.out.println("[" + ctx.channel().remoteAddress() + "] is offline.");
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //Once session is secured, send a greeting and register the channel to the global channel list, so the channel
        //received the messages from others.
        ctx.pipeline().get(SslHandler.class).handshakeFuture().addListener(
                future -> {
                    ctx.writeAndFlush("Welcome to " + InetAddress.getLocalHost().getHostName() + " secure chat service.\r\n");
                    ctx.writeAndFlush("Your session is protected by "
                            + ctx.pipeline().get(SslHandler.class).engine().getSession().getCipherSuite() + " cipher suite.\r\n");

                    channels.add(ctx.channel());
                }
        );
        System.out.println("[" + ctx.channel().remoteAddress() + "] is online.");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

/**
 * Creates a newly configured {@link ChannelInitializer} for a new channel.
 */
class SecureChatServerInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslContext;

    SecureChatServerInitializer(SslContext sslContext) {
        this.sslContext = sslContext;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //Add SSl handler first to encrypt and decrypt everything. In this example, we use a bogus certificate in the
        //server side and accept any invalid certificate in the client side. You will need something more complicated to
        //identify both and server in the real world.
        pipeline.addLast(sslContext.newHandler(ch.alloc()));

        //On top of SSL handler, add the text line codec.
        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new StringEncoder());

        //and then business logic.
        pipeline.addLast(new SecureChatServerHandler());
    }
}