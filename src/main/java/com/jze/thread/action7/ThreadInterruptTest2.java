package com.jze.thread.action7;

import java.util.concurrent.TimeUnit;

/**
 * @author: 蒋泽恩
 * @date: 2019/6/9 10:26
 * @description:
 */
public class ThreadInterruptTest2 {
    public static void main(String[] args) {
        //判断线程是否被中断
        System.out.println("Main Thread is interrupted? :" + Thread.interrupted());

        //中断当前线程
        Thread.currentThread().interrupt();

        //判断是否中断
        System.out.println("Main thread is interrupted? :" + Thread.currentThread().isInterrupted());

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            System.out.println("I am interrupted");
        }
    }
}
