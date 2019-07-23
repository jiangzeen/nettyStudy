package com.jze.thread.action6;

import java.util.LinkedList;

/**
 * @author: 蒋泽恩
 * @date: 2019/6/8 21:41
 * @description:
 */
public class LinkedRunnableQueue implements RunnableQueue {

    //任务队列的最大值
    private final int limit;

    //任务队列的拒绝策略
    private final DenyPolicy denyPolicy;

    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    //存放任务的队列
    private final LinkedList<Runnable> runnableLinkedList = new LinkedList<>();

    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableLinkedList){
            if (runnableLinkedList.size() >= limit){
                denyPolicy.reject(runnable, threadPool);
            }else {
                runnableLinkedList.addLast(runnable);
                //唤醒所有的阻塞线程消费任务
                runnableLinkedList.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() {
        synchronized (runnableLinkedList){
            while (runnableLinkedList.isEmpty()){
               try {
                   //队列为空，则将该线程加入RunnableList的wait set中等待唤醒
                   runnableLinkedList.wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
            }
            return runnableLinkedList.removeFirst();
        }
    }

    @Override
    public int size(){
        synchronized (runnableLinkedList){
            return runnableLinkedList.size();
        }
    }
}
