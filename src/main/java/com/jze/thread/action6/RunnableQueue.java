package com.jze.thread.action6;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/31 17:40
 * @description:
 */
public interface RunnableQueue {

    /**
     * <P>
     *   新任务优先进入queue
     * </P>
     * @param runnable 任务
     */
    void offer(Runnable runnable);

    /**
     * <P>
     *   为线程提供任务
     * </P>
     * @return Runnable
     */
    Runnable take();

    /**
     *
     * @return 返回当前队列任务数量
     */
    int size();
}
