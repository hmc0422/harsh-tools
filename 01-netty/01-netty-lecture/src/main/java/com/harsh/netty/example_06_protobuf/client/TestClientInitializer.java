package com.harsh.netty.example_06_protobuf.client;

import com.harsh.netty.example_06_protobuf.MyDataInfo;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class TestClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline cp = ch.pipeline();

        cp.addLast(new ProtobufVarint32FrameDecoder());
        /*
            ProtobufDecoder 是解码器，将protobuf构造出来的字节数组，转化成真正的对象
            MessageLite(参数)表示要转换的类的实例
         */
        cp.addLast(new ProtobufDecoder(MyDataInfo.Message.getDefaultInstance()));
        cp.addLast(new ProtobufVarint32LengthFieldPrepender());
        cp.addLast(new ProtobufEncoder());

        cp.addLast(new TestClientHandler());
    }
}
