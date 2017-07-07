package yz.learning.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Handles a server-side channel.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //Discard the received data silently;
        //沉默地忽略接受到的数据
        ByteBuf in = (ByteBuf) msg;
        try {
            System.out.println(in.toString(CharsetUtil.UTF_8));
        } finally {
            //ReferenceCountUtil.release(msg);
            //in.release();
        }

        ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.channel();
    }
}
