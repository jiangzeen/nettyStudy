package com.jze.thread.action6;

/**
 * @author: 蒋泽恩
 * @date: 2019/6/8 21:30
 * @description:
 */
public class InternalTask implements Runnable {

    private final RunnableQueue runnableQueue;

    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue){
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        while (running && !Thread.currentThread().isInterrupted()){
            //取出任务进行消费
            Runnable task = runnableQueue.take();
            task.run();
        }
    }

    public void stop(){
        this.running = false;
    }
}
