package com.harsh.netty.example_06_protobuf.client;

import com.harsh.netty.example_06_protobuf.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //这里用一个随机数类，0 1 2
        int randomInt = new Random().nextInt(3);
        MyDataInfo.Message message = null;
        //根据不同的数字生成不同的proto消息类型
        if (randomInt == 0) {
            MyDataInfo.Person person = MyDataInfo.Person.newBuilder()
                    .setName("星空").setAge(18).setAddress("宇宙").build();
            message = MyDataInfo.Message.newBuilder()
                    .setDataType(MyDataInfo.Message.DataType.PersonType).setPerson(person).build();
        } else if (randomInt == 1) {
            message = MyDataInfo.Message.newBuilder().setDataType(MyDataInfo.Message.DataType.DogType)
                    .setDog(MyDataInfo.Dog.newBuilder().setName("阿拉斯加").setAge(2).build()).build();
        } else {
            message = MyDataInfo.Message.newBuilder().setDataType(MyDataInfo.Message.DataType.CatType)
                    .setCat(MyDataInfo.Cat.newBuilder().setName("大花猫").setCity("北京").build()).build();
        }
        ctx.channel().writeAndFlush(message);
    }

}
