package com.jze.thread.action6;

import java.util.concurrent.TimeUnit;

/**
 * @author: 蒋泽恩
 * @date: 2019/6/8 23:13
 * @description:
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws IllegalAccessException {
        final ThreadPool threadPool = new BasicThreadPool(2,6,4,1000);

        //提交20个任务给线程池消费
        for (int i = 0; i < 30; i++) {
            threadPool.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() +
                            " is running and done");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
        for (;;){
            System.out.println("线程池活跃数量:" + threadPool.getActiveCount());
            System.out.println("线程池任务数量:" + threadPool.getQueueSize());
            System.out.println("线程池核心数量:" + threadPool.getCoreSize());
            System.out.println("线程池最大数量:" + threadPool.getMaxSize());
            System.out.println("====================================");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
