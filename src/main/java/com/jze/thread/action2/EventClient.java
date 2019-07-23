package com.jze.thread.action2;

import java.util.concurrent.TimeUnit;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/23 15:39
 * @description:
 */
public class EventClient {

    public static void main(String[] args) {
        final EventQueue queue = new EventQueue();

        new Thread(()->{
            for (;;){
                queue.offer(new EventQueue.Event());
            }
        },"生产者线程1").start();

        new Thread(()->{
            for (;;){
                queue.offer(new EventQueue.Event());
            }
        },"生产者线程2").start();


        new Thread(()->{
            for (;;){
                queue.offer(new EventQueue.Event());
            }
        },"生产者线程3").start();

        new Thread(()->{
            for (;;){
                queue.take();
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"消费者线程1").start();

        new Thread(()->{
            for (;;){
                queue.take();
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"消费者线程2").start();

        new Thread(()->{
            for (;;){
                queue.take();
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"消费者线程3").start();

        new Thread(()->{
            for (;;){
                queue.take();
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"消费者线程4").start();
    }
}
