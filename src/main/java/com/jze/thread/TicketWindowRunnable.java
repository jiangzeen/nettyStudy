package com.jze.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/21 22:39
 * @description:
 */
public class TicketWindowRunnable implements Runnable {

    private final Integer MAX = 50;
    private AtomicInteger index = new AtomicInteger(1);

    @Override
    public void run() {
            while (index.get() <= MAX){
                System.out.printf("柜台号码是%s,当前的号码是%d\n", Thread.currentThread().getName(),
                        index.getAndIncrement());
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
    }
}
