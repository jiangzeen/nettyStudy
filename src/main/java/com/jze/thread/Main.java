package com.jze.thread;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/21 22:28
 * @description:
 */
public class Main {

    public static void main(String[] args) {
//        TicketWindows t1 = new TicketWindows("一号柜台");
//        t1.start();
//
//        TicketWindows t2 = new TicketWindows("二号柜台");
//        t2.start();
//
//        TicketWindows t3 = new TicketWindows("三号柜台");
//        t3.start();
//
//        TicketWindows t4 = new TicketWindows("四号柜台");
//        t4.start();

        final TicketWindowRunnable task = new TicketWindowRunnable();

        Thread windowsThread1 = new Thread(task, "一号窗口");


        Thread windowsThread2 = new Thread(task, "二号窗口");


        Thread windowsThread3 = new Thread(task, "三号窗口");


        Thread windowsThread4 = new Thread(task, "四号窗口");

        windowsThread1.start();
        windowsThread2.start();
        windowsThread3.start();
        windowsThread4.start();
    }
}
