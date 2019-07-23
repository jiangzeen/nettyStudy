package com.jze.thread.action7;

/**
 * @author: 蒋泽恩
 * @date: 2019/6/9 09:50
 * @description:
 */
public class ThreadInterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            while (!Thread.currentThread().isInterrupted()){
                   System.out.println("thread is running");
               }
               System.out.println("thread is stop");

        });
        thread.start();

        System.out.println(thread.getState());
        Thread.sleep(3000);

        thread.interrupt();
        Thread.sleep(3000);
        System.out.println(thread.getState());
    }
}
