package yz.learning.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
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
