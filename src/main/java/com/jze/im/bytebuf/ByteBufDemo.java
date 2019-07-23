package com.jze.im.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author: 蒋泽恩
 * @date: 2019/7/21 21:00
 * @description: ByteBuf的一些属性介绍
 */
public class ByteBufDemo {
    public static void main(String[] args) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(8,100);
        //给定初始化bytebuf后
        print("allocate(20,100)", buf);

        //写入数据byte数组4字节，改变写指针的位置
        buf.writeBytes(new byte[]{1,2,3,4});
        print("writeInt((bytes)1,2,3,4)", buf);
        //写入一个整型(4个字节)
        buf.writeInt(12);
        print("write((int)12)", buf);

        //再写入一个字节,buf进行扩容
        buf.writeBytes(new byte[]{12});
        print("write(byte(12))", buf);

        //read方法改变读指针，getByte和setByte不改变读写指针的位置
        byte[] dst = new byte[buf.readableBytes()];
        buf.readBytes(dst);
        print("readBytes(dst(9))", buf);
    }

    private static void print(String msg, ByteBuf byteBuf){
        System.out.println("===========action+("+msg+")============");
        System.out.println("capacity():" + byteBuf.capacity());
        System.out.println("maxCapacity():" + byteBuf.maxCapacity());
        System.out.println("readerIndex():" + byteBuf.readerIndex());
        System.out.println("readableBytes():" + byteBuf.readableBytes());
        System.out.println("isReadable():" + byteBuf.isReadable());
        System.out.println("writeIndex():" + byteBuf.writerIndex());
        System.out.println("writableBytes():" + byteBuf.writableBytes());
        System.out.println("isWritable():" + byteBuf.isWritable());
        System.out.println("maxWritableBytes():" + byteBuf.maxWritableBytes());
        System.out.println();
    }
}
