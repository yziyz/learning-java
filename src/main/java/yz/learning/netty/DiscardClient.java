package yz.learning.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

/**
 * Keeps sending random data to the specified address.
 */
public final class DiscardClient {
    static final boolean SSL = System.getProperty("ssl") != null;
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8009"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    public static void main(String[] args) throws Exception {
        //Configure SSL.
        final SslContext sslCtx;
        if (SSL) {
            sslCtx = SslContextBuilder
                    .forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE)
                    .build();
        } else {
            sslCtx = null;
        }

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            if (sslCtx != null) {
                                p.addLast(sslCtx.newHandler(ch.alloc(), HOST, PORT));
                            }
                            p.addLast(new DiscardClientHandler());
                        }
                    });

            //Make the connection attempt.
            ChannelFuture f = b.connect(HOST, PORT).sync();

            //Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}

/**
 * Handles a client-side channel.
 */
class DiscardClientHandler extends SimpleChannelInboundHandler<Object> {

    private ByteBuf content;
    private ChannelHandlerContext ctx;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        this.ctx = ctx;

        //Initialize the message.
        content = ctx
                .alloc()
                .directBuffer(DiscardClient.SIZE)
                .writeZero(DiscardClient.SIZE);

        //Send the messages.
        generateTraffic();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        content.release();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        //Server is supposed to send nothing, but if sends something, discard it.
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //Close the connection when an exception is raised.
        cause.printStackTrace();
    }

    long counter;

    private void generateTraffic() {
        //Flush the outbound buffer to the socket.
        //Once flushed, generate the same amount of traffic again.
        ctx.writeAndFlush(content.retainedDuplicate()).addListener(trafficGenerator);
    }

    private final ChannelFutureListener trafficGenerator = new ChannelFutureListener() {
        @Override
        public void operationComplete(ChannelFuture channelFuture) throws Exception {
            if (channelFuture.isSuccess()) {
                generateTraffic();
            } else {
                channelFuture.cause().printStackTrace();
                channelFuture.channel().close();
            }
        }
    };
}

