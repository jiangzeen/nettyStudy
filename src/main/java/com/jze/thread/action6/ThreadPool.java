package com.jze.thread.action6;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/31 17:28
 * @description: 线程池接口
 */
public interface ThreadPool {
    /**
     * <p>
     *     关闭线程池
     * </p>
     */
    void shutdown();

    /**
     * <P>
     *     提交任务到线程池
     * </P>
     * @param runnable 任务-交给一个线程处理
     */
    void execute(Runnable runnable) throws IllegalAccessException;

    /**
     * <P>
     *     获取线程池初始化大小
     * </P>
     * @return size
     */
    int getInitSize();

    /**
     * <P>
     *    获取到线程池的最大值
     * </P>
     * @return max size
     */
    int getMaxSize();

    /**
     * <P>
     *     获取到线程池的核心数量
     * </P>
     * @return core size
     */
    int getCoreSize();

    /**
     * <P>
     *     获取到线程池中用于缓存队列的大小
     * </P>
     * @return queue size
     */
    int getQueueSize();

    /**
     * <P>
     *     获取到线程池中活跃的线程数量
     * </P>
     * @return active size
     */
    int getActiveCount();

    /**
     * <P>
     *     线程池是否已经被销毁
     * </P>
     * @return shutdown
     */
    boolean isShutdown();
}
