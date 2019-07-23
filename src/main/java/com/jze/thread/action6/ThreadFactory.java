package com.jze.thread.action6;

/**
 * @author: 蒋泽恩
 * @date: 2019/6/8 22:56
 * @description:
 */
public interface ThreadFactory {

    Thread createThread(Runnable runnable);
}
