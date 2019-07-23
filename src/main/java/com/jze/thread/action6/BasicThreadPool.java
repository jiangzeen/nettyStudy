package com.jze.thread.action6;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: 蒋泽恩
 * @date: 2019/6/8 21:55
 * @description:
 */
public class BasicThreadPool extends Thread implements ThreadPool {

    //线程池初始化大小
    private final int initSize;

    //线程池最大数量
    private final int maxSize;

    //线程池最大线程数量
    private final int coreSize;

    //当前活跃的线程数量
    private int activeCount;

    private final ThreadFactory threadFactory;

    private final RunnableQueue runnableQueue;

    private volatile boolean isShutdown = false;

    private final Queue<ThreadTask> threadTaskQueue =  new ArrayDeque<>();


    private final static DenyPolicy DEFAULT_DENY_POLICY =
            new DenyPolicy.DisCardDenyPolicy();

    private final static ThreadFactory DEFAULT_THREAD_FACTORY =
            new DefaultThreadFactory();

    private final long keepAliveTime;

    private final TimeUnit timeUnit;

    public BasicThreadPool(int initSize, int maxSize, int coreSize,
                           int queueSize){
        this(initSize, maxSize, coreSize, DEFAULT_THREAD_FACTORY, queueSize,
                DEFAULT_DENY_POLICY,10, TimeUnit.SECONDS);
    }

    public BasicThreadPool(int initSize, int maxSize, int coreSize,
                           ThreadFactory threadFactory, int queueSize,
                           DenyPolicy denyPolicy, long keepAliveTime,
                           TimeUnit timeUnit){
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy,
                this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.init();
    }

    private void init(){
        start();
        for (int i = 0; i < initSize; i++) {
            newThread();
        }
    }

    /**
     * 创建任务线程，并且启动
     */
    private void newThread(){

        //创建任务
        InternalTask internalTask = new InternalTask(runnableQueue);

        //提交任务给线程
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadTaskQueue.offer(threadTask);
        this.activeCount++;
        thread.start();
    }

    /**
     * 从线程池移除线程
     */
    private void removeThread(){
        ThreadTask threadTask =  threadTaskQueue.remove();
        threadTask.task.stop();
        this.activeCount--;
    }

    /**
     * 维持线程数量
     */
    @Override
    public void run(){
        while (!isShutdown && !isInterrupted()){
            try {
                TimeUnit.SECONDS.sleep(keepAliveTime);
            }catch (InterruptedException e){
                isShutdown = true;
                break;
            }
            synchronized (this){
                if (isShutdown){
                    break;
                }

                //
                if (runnableQueue.size() > 0 && activeCount < coreSize){
                    for (int i = initSize; i < coreSize; i++) {
                        newThread();
                    }
                    continue;
                }

                //
                if (runnableQueue.size() > 0 && activeCount < maxSize){
                    for (int i = coreSize; i < maxSize; i++) {
                        newThread();
                    }
                }

                //回收
                if (runnableQueue.size() == 0 && activeCount > coreSize){
                    for (int i = coreSize; i < activeCount; i++) {
                        removeThread();
                    }
                }
            }
        }
    }

    @Override
    public void shutdown() {
        synchronized (this){
            if (isShutdown) return;
            isShutdown = true;
            threadTaskQueue.forEach(threadTask -> {
                threadTask.task.stop();
                threadTask.thread.interrupt();
            });
            this.interrupt();
        }
    }

    @Override
    public void execute(Runnable runnable) throws IllegalAccessException {
        if (this.isShutdown){
            throw new IllegalAccessException("线程池已经销毁");
        }
        this.runnableQueue.offer(runnable);
    }

    @Override
    public int getInitSize() {
        return initSize;
    }

    @Override
    public int getMaxSize() {
        return maxSize;
    }

    @Override
    public int getCoreSize() {
        return coreSize;
    }

    @Override
    public int getQueueSize() {
        return runnableQueue.size();
    }

    @Override
    public int getActiveCount() {
        synchronized (this){
            return this.activeCount;
        }
    }

    @Override
    public boolean isShutdown() {
        return this.isShutdown;
    }

    private static class ThreadTask{
        Thread thread;
        InternalTask task;

        private ThreadTask(Thread thread, InternalTask task){
            this.task = task;
            this.thread = thread;
        }

    }

    private static class DefaultThreadFactory implements ThreadFactory {

        private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);

        private static final ThreadGroup group = new ThreadGroup(
                "MyThreadGroup--" + GROUP_COUNTER.getAndDecrement());

        private static final AtomicInteger COUNTER = new AtomicInteger(0);

        @Override
        public Thread createThread(Runnable runnable) {
            return new Thread(group, runnable, "thread-pool-" + COUNTER.getAndDecrement());
        }
    }
}
