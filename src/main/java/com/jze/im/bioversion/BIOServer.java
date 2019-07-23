package com.jze.im.bioversion;


import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author: 蒋泽恩
 * @date: 2019/7/21 16:25
 * @description:
 */
public class BIOServer {
    private static Logger logger = LoggerFactory.getLogger(BIOServer.class);
    private static org.apache.log4j.Logger log4jLogger =
            org.apache.log4j.Logger.getLogger("CONSOLE");
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        //开启线程进行不断的轮询获取到连接
        new  Thread(()->{
            while (true){
                try {
                    //获取到客户端连接
                    Socket socket = serverSocket.accept();
                    //开启线程不断的读取客户端的信息
                    new Thread(()->{
                        byte[] bytes = new byte[1024];
                        int len = 0;
                        try {
                            InputStream in = socket.getInputStream();
                            while ((len = in.read(bytes)) != -1){
//                                logger.info("获取到客户端{}的信息:{}", socket,bytes);
                                log4jLogger.info("获取到客户端"+socket+"的信息:"+ new String(bytes));
                            }
                        } catch (IOException e) {
                            System.out.println("当前客户端连接已断开");
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
