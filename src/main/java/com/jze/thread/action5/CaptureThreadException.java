package com.jze.thread.action5;

import java.util.concurrent.TimeUnit;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/29 18:42
 * @description: 捕获线程异常，设置UncaughtExceptionHandler
 */
public class CaptureThreadException {

    public static void main(String[] args) throws InterruptedException {
         Thread.setDefaultUncaughtExceptionHandler((t, e)->{
             System.out.println(t.getName() + "发生异常");
             e.printStackTrace();
         });
         Thread t = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){

            }
            System.out.println(1/0);
         }, "thread1");
         t.start();
         TimeUnit.SECONDS.sleep(2);
         System.out.println("子线程抛出了异常，但我主线程还是在跑");
        System.out.println("子线程抛出了异常，但我主线程还是在跑");
        System.out.println("子线程抛出了异常，但我主线程还是在跑");
        System.out.println("子线程抛出了异常，但我主线程还是在跑");
    }
}
