package com.jze.thread.action2;


import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/23 14:50
 * @description:
 */
public class HashMapThread {
    public final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
    public void add(String k, String v){
        map.put(k, v);
    }

    public static void main(String[] args) {
        HashMapThread map =  new HashMapThread();
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                for (int j = 0; j < Integer.MAX_VALUE; ++j){
                    map.add(String.valueOf(j), String.valueOf(j));
                    System.out.println("当前map元素：" + map.map.size());
                }
            }).start();
        }
    }
}
