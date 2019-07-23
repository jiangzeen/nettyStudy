package com.jze.thread.action3;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * <p>
 *     自定义显示锁BooleanLock接口，主要用于解决synchronized阻塞中的两个问题
 *     1.阻塞时长无法控制
 *     2.因争抢monitor锁而进入阻塞状态,无法中断线程造成线程阻塞时间过长
 * </p>
 * @author: 蒋泽恩
 * @date: 2019/5/23 21:59
 */
public interface Lock {


    /**
     * <p>
     *     该方法永远是阻塞的，除非获取到了锁，这一点和{@code synchronized}非常的类似
     *     当时该方法不同之处在于可以被中断,中断是会抛出异常{@code InterruptedException}
     * </p>
     * @throws InterruptedException 当线程被中断时抛出异常
     */
     void lock() throws InterruptedException;

    /**
     * <P>
     *     与{@code lock()}相比增加了超时功能
     * </P>
     * @param mills 阻塞时长
     * @throws InterruptedException 当线程被中断时抛出异常
     * @throws TimeoutException 当获取锁超时时抛出该异常
     */
    void lock(long mills) throws InterruptedException, TimeoutException;

    /**
     * <p>
     *     进行锁的释放
     * </p>
     */
    void unlock();

    /**
     * <p>
     *    用于获取当前有哪些线程被阻塞
     * </p>
     * @return List<Thread>
     */
    List<Thread> getBlockedThreads();
}
