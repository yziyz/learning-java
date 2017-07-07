package yz.learning.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Handles a client-side channel.
 */
public class DiscardClientHandler extends SimpleChannelInboundHandler<Object> {

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
