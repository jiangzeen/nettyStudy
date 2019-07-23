package com.jze.im.bioversion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author: 蒋泽恩
 * @date: 2019/7/21 17:08
 * @description:
 */
public class BIOClient {
    private static Logger logger = LoggerFactory.getLogger(BIOClient.class);
    private static org.apache.log4j.Logger log4jLogger =
            org.apache.log4j.Logger.getLogger(BIOServer.class);
    public static void main(String[] args){
        //不断的启动一个客户端
        new Thread(()->{
            while (true){
                try {
                    Socket socket = new Socket("127.0.0.1",8080);
                    socket.getOutputStream().write((new Date() + ":Hello World").getBytes());
                    Thread.sleep(2000);
                }catch (Exception e){
                    log4jLogger.info("服务器断开连接，请稍后重试");
                }
            }
        }).start();
    }
}
