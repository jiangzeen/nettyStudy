package com.jze.im.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author: 蒋泽恩
 * @date: 2019/7/22 10:44
 * @description: 进行逻辑处理之前的一些配置
 */
public class IMServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new IMServerHandler());
    }
}
