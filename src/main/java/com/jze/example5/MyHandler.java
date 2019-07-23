package com.jze.example5;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;


/**
 * @author: 蒋泽恩
 * @date: 2019/5/11 22:52
 * @description:
 */
public class MyHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + ": " + msg.text());
        if(msg.text().equals("客户端")){
//            ctx.channel().writeAndFlush(new TextWebSocketFrame("服务端"));
            ctx.channel().writeAndFlush("hi");
            return;
        }
        ctx.channel().writeAndFlush(new TextWebSocketFrame("来自服务端: " + LocalDateTime.now()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush("服务器已连接");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush("服务器已断开");
    }

}
