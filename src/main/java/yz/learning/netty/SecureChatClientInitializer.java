package yz.learning.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;


/**
 * Create a newly configured {@link io.netty.channel.ChannelPipeline} for a new channel.
 *
 * @author 袁臻
 * 2017/09/12 19:36
 */
public class SecureChatClientInitializer extends ChannelInitializer<SocketChannel> {

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
