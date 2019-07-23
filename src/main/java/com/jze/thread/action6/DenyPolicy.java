package com.jze.thread.action6;

/**
 * @author: 蒋泽恩
 * @date: 2019/6/8 21:24
 * @description:
 */
@FunctionalInterface
public interface DenyPolicy {
    /**
     * 拒绝策略接口方法,用户自定义处理任务过多时的情况
     * @param runnable 新进来的任务
     * @param threadPool 当前线程池
     */
    void reject(Runnable runnable, ThreadPool threadPool);

    class DisCardDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {

        }
    }
}
