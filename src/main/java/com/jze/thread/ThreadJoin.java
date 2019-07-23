package com.jze.thread;


import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


import static java.util.stream.Collectors.toList;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/22 17:39
 * @description: 线程阻塞方法值join
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads =
                IntStream.range(1,3).mapToObj(ThreadJoin::create).collect(toList());
        threads.forEach(Thread::start);

        for (Thread thread : threads) {
            //使用join阻塞main线程，使得main线程在该线程之后再运行
            thread.join();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "#" + i);
            shortSleep();
        }
    }

    public static Thread create(int index){
        return new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "#" + i);
                shortSleep();
            }
        }, String.valueOf(index));
    }

    public static void  shortSleep(){
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
