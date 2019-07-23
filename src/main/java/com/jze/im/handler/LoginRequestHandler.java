package com.jze.im.handler;

import com.jze.im.protocol.request.LoginRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: 蒋泽恩
 * @date: 2019/7/22 19:53
 * @description: 添加login的handler处理器
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {

    }
}
