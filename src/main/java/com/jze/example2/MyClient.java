package com.jze.example2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/8 20:21
 * @description:
 */
public class MyClient {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup clientLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(clientLoopGroup).channel(NioSocketChannel.class).
                    handler(new MyClientInitializer());
            ChannelFuture channelFuture =
                    bootstrap.connect("localhost",8080).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            clientLoopGroup.shutdownGracefully();
        }
    }
}
