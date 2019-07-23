package com.jze.thread;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/21 22:23
 * @description:
 */
public class TicketWindows extends Thread {

    private final String NAME;

    public TicketWindows(String NAME) {
        this.NAME = NAME;
    }

    private static final Integer MAX = 50;

    /**
     * 静态变量：使用static关键字定义的变量。static可以修饰变量和方法，
     * 也有static静态代码块。被static修饰的成员变量和成员方法独立于该类的任何对象。
     * 也就是说，它不依赖类特定的实例，被类的所有实例共享。只要这个类被加载，
     * Java虚拟机就能根据类名在运行时数据区的方法区内定找到他们。
     * 因此，static对象可以在它的任何对象创建之前访问，无需引用任何对象
     */
    private static int index = 1;

    @Override
    public void run() {
        while (index <= MAX){
            System.out.printf("柜台号码是%s,当前的号码是%d\n", NAME, index++);
        }
    }


}
