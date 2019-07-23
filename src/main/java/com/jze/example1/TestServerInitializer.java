package com.jze.example1;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/8 19:09
 * @description: 初始化服务的handler--编解码等
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
       ChannelPipeline pipeline =  ch.pipeline();
       //添加http协议编解码handler
       pipeline.addLast("http", new HttpServerCodec());
       pipeline.addLast("myHandler", new  TestServerHandle());
    }
}
