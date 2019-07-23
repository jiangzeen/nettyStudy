package com.jze.thread.action2;

import java.util.LinkedList;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/23 15:22
 * @description: 多线程处理事件
 */
public class EventQueue {

    private int max;

    private final static int DEFAULT_MAX = 10;

    static class Event{

    }
    private final LinkedList<Event> events = new LinkedList<>();

    public EventQueue(){
        this(DEFAULT_MAX);
    }

    public EventQueue(int max){
        this.max = max;
    }

    public void offer(Event event){
        synchronized (events){
            if(events.size() >= max){
                try {
                    System.out.println(Thread.currentThread().getName() +
                            ":事件处理队列已满");
                    //队列已满,队列沉睡
                    events.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ":事件已提交");
            //唤醒，获取monitor
            events.addLast(event);
            events.notify();
        }
    }

    public void take(){
        synchronized (events){
            if (events.isEmpty()){
                try {
                    System.out.println(Thread.currentThread().getName() +
                            "事件处理队列已空");
                    events.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ":事件已处理");
            events.removeFirst();
            events.notify();
        }
    }
}
