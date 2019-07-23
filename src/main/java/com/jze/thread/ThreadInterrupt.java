package com.jze.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/22 17:18
 * @description:
 */
public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            try {
                TimeUnit.MINUTES.sleep(1);
            }catch (InterruptedException e){
                System.out.println("线程阻塞被打断");
            }
        });
        thread.start();

        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();
    }
}
