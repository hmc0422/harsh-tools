package com.harsh.netty.example_06_protobuf.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TestClient {

    public static void main(String[] args) throws Exception {
        EventLoopGroup elg = new NioEventLoopGroup();

        try {
            Bootstrap bs = new Bootstrap();
            bs.group(elg).channel(NioSocketChannel.class)
                    .handler(new TestClientInitializer());

            ChannelFuture cf = bs.connect("localhost", 10000).sync();
            cf.channel().closeFuture().sync();
        } finally {
            elg.shutdownGracefully();
        }
    }

}
