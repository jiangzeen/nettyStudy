package com.jze.io;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.*;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/12 21:10
 * @description: nio写入文件信息
 */
public class NioExample2 {

    public static void main(String[] args) throws Exception{
        FileChannel fc = new FileOutputStream("nio2.txt").getChannel();
        byte[] bytes = "this is nio write information".getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        for (byte aByte : bytes) {
            buffer.put(aByte);
        }

        buffer.flip();
        fc.write(buffer);
        System.out.println("限制是：" + buffer.limit() + "容量是：" + buffer.capacity()+
                "位置是：" + buffer.position());

        ScheduledExecutorService executor =
                Executors.newScheduledThreadPool(10);

        ScheduledFuture<?> future = executor.schedule(()->{
                    System.out.println("60s seconds later");
                },60,
                TimeUnit.SECONDS);
        executor.shutdown();
    }
}
