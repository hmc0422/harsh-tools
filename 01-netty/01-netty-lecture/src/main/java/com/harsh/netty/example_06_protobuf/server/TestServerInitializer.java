package com.harsh.netty.example_06_protobuf.server;

import com.harsh.netty.example_06_protobuf.MyDataInfo;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline cp = ch.pipeline();

        // protobuf处理器
        //用于decode前解决半包和粘包问题（利用包头中的包含数组长度来识别半包粘包）
        cp.addLast(new ProtobufVarint32FrameDecoder());
        /*
            ProtobufDecoder(MessageLite m) 是解码器，将protobuf构造出来的字节数组，转化成真正的对象
            MessageLite(参数) 表示要转换/传输的类的实例

            反序列化指定的Probuf字节数组为protobuf类型。
         */
        //这里解码器解析的对象是DataInfo.Message 最外层的消息体/类
        cp.addLast(new ProtobufDecoder(MyDataInfo.Message.getDefaultInstance()));
        //用于在序列化的字节数组前加上一个简单的包头，只包含序列化的字节长度。
        cp.addLast(new ProtobufVarint32LengthFieldPrepender());
        //用于对Probuf类型序列化。
        cp.addLast(new ProtobufEncoder());

        // 自定义处理器
        cp.addLast(new TestServerHandler());
    }
}
