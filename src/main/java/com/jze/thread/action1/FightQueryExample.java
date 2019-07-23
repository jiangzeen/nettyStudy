package com.jze.thread.action1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/22 21:48
 * @description:
 */
public class FightQueryExample {

    private static List<String> airCompanys = new ArrayList<>(Arrays.asList("南方航空", "东方航空", "海南航空"));
    public static void main(String[] args) {
        List<String> result = search("上海","北京");
        System.out.println("****************查询到如下信息*****************");
        result.forEach(System.out::println);
        System.out.println("程序即将结束");
    }

    public static List<String> search(String original, String dest){
        //创建查询线程
        List<FightQueryTask> tasks = airCompanys.stream().map(it->
                createSearchTask(it, original, dest)).collect(Collectors.toList());
        tasks.forEach(Thread::start);
        tasks.forEach(it->{
            try {
                it.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        return tasks.stream().map(FightQueryTask::get).reduce(new ArrayList<>(), (it,all)->{
            all.addAll(it);
            return all;
        });
    }

    private static FightQueryTask createSearchTask(String air, String original
            , String dest){
        return new FightQueryTask(air, original, dest);
    }
}
