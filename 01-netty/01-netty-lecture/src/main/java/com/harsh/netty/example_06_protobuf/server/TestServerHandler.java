package com.harsh.netty.example_06_protobuf.server;

import com.harsh.netty.example_06_protobuf.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Message msg) throws Exception {
        MyDataInfo.Message.DataType dt = msg.getDataType();
        switch (dt) {
            case PersonType:
                MyDataInfo.Person person = msg.getPerson();
                System.out.println(person.getName());
                System.out.println(person.getAge());
                System.out.println(person.getAddress());
                break;
            case DogType:
                MyDataInfo.Dog dog = msg.getDog();
                System.out.println(dog.getName());
                System.out.println(dog.getAge());
                break;
            case CatType:
                MyDataInfo.Cat cat = msg.getCat();
                System.out.println(cat.getName());
                System.out.println(cat.getCity());
                break;
            default:
                break;
        }
    }
}
