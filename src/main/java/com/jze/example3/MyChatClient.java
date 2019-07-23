package com.jze.example3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.*;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/9 15:31
 * @description:
 */
public class MyChatClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup loopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(loopGroup).channel(NioSocketChannel.class).
                    handler(new MyChatClientInitializer());

            Channel channel = bootstrap.connect("localhost", 8099)
                    .sync().channel();
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(System.in));
            for (;;){
                channel.writeAndFlush(in.readLine() + "\r\n");
            }
        }finally {
            loopGroup.shutdownGracefully();
        }
    }
}
