package com.jze.thread.action1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/22 20:25
 * @description:
 */
public class FightQueryTask extends Thread implements FightQuery {
    private final String original;
    private final String destination;
    private final List<String> flightList = new ArrayList<>();

    public FightQueryTask(String airLine, String original, String destination) {
        super("[" + airLine + "]");
        this.original = original;
        this.destination = destination;
    }

    @Override
    public void run(){
        System.out.printf("从%s查询到从%s到%s的航空路线\n",
                getName(), original, destination);
        int randomTime = ThreadLocalRandom.current().nextInt(10);
        try {
            this.flightList.add(getName() + "--" + randomTime);
            TimeUnit.SECONDS.sleep(randomTime);
            System.out.println(getName() + "查询成功!!!");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<String> get() {
        return flightList;
    }


}
