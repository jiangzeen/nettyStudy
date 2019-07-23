package com.jze.example2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/8 20:59
 * @description:
 */
public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        LocalDateTime data = LocalDateTime.now();
        System.out.println(ctx.channel().remoteAddress() + "" + msg);
        ctx.channel().writeAndFlush("form client" + data);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ctx.writeAndFlush("来自客户端");
    }
}
