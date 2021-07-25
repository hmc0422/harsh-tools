package com.harsh.netty.example_05_webscoket.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline cp = ch.pipeline();

        // 处理Http请求
        cp.addLast(new HttpServerCodec());
        cp.addLast(new ChunkedWriteHandler());
        cp.addLast(new HttpObjectAggregator(10000));

        // 处理WebSocket
        cp.addLast(new WebSocketServerProtocolHandler("/ws"));

        // 处理自定义处理器
        cp.addLast(new TextWebSocketFrameHandler());

    }
}
