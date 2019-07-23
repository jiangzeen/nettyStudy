package com.jze.thread.action3;

import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/23 22:53
 * @description: booleanLock锁测试
 */
public class BooleanLockTest {
    private final Lock lock = new BooleanLock();

    public void syncMethod(){
        try {
            lock.lock();
            int random = current().nextInt(10);
            System.out.println("线程" + currentThread().getName() + "获取到了锁");
            TimeUnit.SECONDS.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        BooleanLockTest booleanLock = new BooleanLockTest();
        IntStream.range(0,10).mapToObj(it-> new Thread(booleanLock::syncMethod))
                .forEach(Thread::start);
        TimeUnit.SECONDS.sleep(1);
        System.out.println(booleanLock.lock.getBlockedThreads().stream().
                map(Thread::getName).collect(Collectors.toList()));
    }
}
