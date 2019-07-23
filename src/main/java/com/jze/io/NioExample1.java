package com.jze.io;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/7 19:36
 * @description: nio读取文件内容
 */
public class NioExample1 {
    public static void main(String[] args) throws IOException {
        FileChannel fc =
                new FileInputStream("normal_io.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("限制是：" + buffer.limit() + "容量是：" + buffer.capacity()+
                "位置是：" + buffer.position());
        fc.read(buffer);
        System.out.println("限制是：" + buffer.limit() + "容量是：" + buffer.capacity()+
                "位置是：" + buffer.position());
        //一旦调用read来读取buffer存储的内容，需要调用flip()方法做好读取准备
        //flip会改变buffer的初始位置position和内容的大小limit
        buffer.flip();
        System.out.println("限制是：" + buffer.limit() + "容量是：" + buffer.capacity()+
                "位置是：" + buffer.position());
        while (buffer.remaining() > 0){
            System.out.print((char)buffer.get());
        }
        System.out.println();
        System.out.println(fc.size());
        fc.close();
    }
}
