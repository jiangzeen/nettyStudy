package com.jze.thread;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/22 15:45
 * @description: 认识守护线程
 */
public class DaemonThread {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
           while (true){
               try {
                   System.out.println("线程运行中");
               }catch (Exception e){
                   e.printStackTrace();
               }
           }
        });
        /*
         * 设置为守护线程--->具备自动结束生命周期的特点
         * 当所有的线程都为守护线程是，Java虚拟机会结束程序
         * 比如JVM中的垃圾回收线程即为一个守护线程
         */
        t.setDaemon(true);
        t.start();
        System.out.println("Main thread finished lifecycle.");
    }
}
