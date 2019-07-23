package com.jze.thread.action3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;

/**
 * <p>
 *     lock接口的一个boolean实现,使用一个boolean变量开关来控制是否允许当前线程
 *     获取该锁
 * </p>
 *
 * @author: 蒋泽恩
 * @date: 2019/5/23 22:31
 * @description:
 */
public class BooleanLock implements Lock {

    private Thread currentThread;

    private boolean locked = false;

    private final List<Thread> blockList = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this){
            while (locked){
                blockList.add(currentThread);
                this.wait();
            }
            //从wait set中删除
            blockList.remove(currentThread);

            //修改锁的状态
            this.locked = true;

            //记录当前获取到锁的线程
            this.currentThread = currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this){
            if(mills < 0){
                throw new IllegalArgumentException("输入的最长等待时间不非法！");
            }
            long remainMills = mills;
            long endMills = remainMills + currentTimeMillis();
            while (locked){
                if (remainMills < 0){
                    throw new TimeoutException("无法在规定时间内获取到锁");
                }
                if (!blockList.contains(currentThread())){
                    blockList.add(currentThread());
                    this.wait(remainMills);
                    remainMills = endMills - currentTimeMillis();
                }
            }
            blockList.remove(currentThread());
            this.locked = true;
            this.currentThread = currentThread();
        }

    }

    @Override
    public void unlock() {
        synchronized (this){
            if (currentThread == currentThread()){
                this.locked = false;
                System.out.println(currentThread().getName() + "释放了锁");
                //释放锁的同时唤醒所有的block线程
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockList);
    }
}
