package com.harsh.protobuf;

public class ProtoBufTest {

    public static void main(String[] args) throws Exception {
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("张三")
                .setAge(23)
                .setAddress("李家村")
                .build();

        byte[] bytes = student.toByteArray();

        DataInfo.Student student2 = DataInfo.Student.parseFrom(bytes);
        System.out.println(student2.getName());
        System.out.println(student2.getAddress());
        System.out.println(student2.getAge());
    }


}
