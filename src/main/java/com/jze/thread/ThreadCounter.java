package com.jze.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/22 15:26
 * @description:
 */
public class ThreadCounter extends Thread {
    final static AtomicInteger counter = new AtomicInteger(0);
    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        try {
            while (true){
                new ThreadCounter().start();
            }
        }catch (Throwable e){
            System.out.println("failed At =>" + counter.get());
        }
    }

    @Override
    public void run() {
        System.out.println(counter.getAndIncrement() + "the thread create!");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
