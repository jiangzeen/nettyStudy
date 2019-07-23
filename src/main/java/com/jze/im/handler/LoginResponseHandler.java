package com.jze.im.handler;

import com.jze.im.protocol.response.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: jiangzeen
 * @date: 19-7-23 05:22
 * @description: 处理消息的响应
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {

    }
}
