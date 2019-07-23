package com.jze.im.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author: 蒋泽恩
 * @date: 2019/7/22 10:24
 * @description:
 */
public class IMClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //增加自定义的处理器
        pipeline.addLast(new IMClientHandler());
    }
}
