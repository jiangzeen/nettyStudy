package com.jze.example1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * @author: 蒋泽恩
 * @date: 2019/5/6 20:30
 * @description: netty--Hello World
 */
public class TestServer {
    public static void main(String[] args) throws InterruptedException {
        //bossGroup只获取获取连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //workGroup用来
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            //netty提供快速启动服务类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //可以使用handler与childHandler
            serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).
                    childHandler(new TestServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
