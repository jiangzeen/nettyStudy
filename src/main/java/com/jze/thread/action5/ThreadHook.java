package com.jze.thread.action5;

import java.util.concurrent.TimeUnit;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/29 19:17
 * @description:
 */
public class ThreadHook {

    public static void main(String[] args) throws InterruptedException {
        //hook线程，JVM进程退出时启动hook线程
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("主程序结束");
        }));
        TimeUnit.SECONDS.sleep(1);
        System.out.println("123");
    }
}
