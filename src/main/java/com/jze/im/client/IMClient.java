package com.jze.im.client;

import com.jze.im.protocol.request.MessageRequestPacket;
import com.jze.im.util.IMUtil;
import com.jze.im.util.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

/**
 * @author: 蒋泽恩
 * @date: 2019/7/22 10:18
 * @description: 客户端启动类
 */
public class IMClient {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).
                    handler(new IMClientInitializer());
            Channel channel = bootstrap.connect("127.0.0.1",8080).
                    sync().channel();
            startThreadConsole(channel);
        } catch (InterruptedException e) {
            System.out.println("连接失败");;
        }
    }

    /**
     * 启动读取输入线程
     * @param channel
     */
    private static void startThreadConsole(Channel channel){
        new Thread(()->{
            while (!Thread.interrupted()){
                if (LoginUtil.hasLogin(channel)){
                    System.out.println("请输入要发送的信息：");
                    Scanner scanner = new Scanner(System.in);
                    String msg = scanner.nextLine();

                    MessageRequestPacket messagePacket =
                            new MessageRequestPacket();
                    messagePacket.setMessage(msg);
                    ByteBuf byteBuf = IMUtil.packetCodeC.encode(messagePacket);
                    channel.writeAndFlush(byteBuf);
                }
            }
        }).start();
    }
}
