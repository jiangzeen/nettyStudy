package com.jze.im.client;

import com.jze.im.protocol.response.MessageResponsePacket;
import com.jze.im.util.IMUtil;
import com.jze.im.protocol.request.LoginRequestPacket;
import com.jze.im.protocol.response.LoginResponsePacket;
import com.jze.im.protocol.Packet;
import com.jze.im.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: 蒋泽恩
 * @date: 2019/7/22 10:27
 * @description: 客户端channel处理器，处理业务逻辑
 */
public class IMClientHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        IMUtil.logger.info("客户端开始登陆");
        //创建登录对象
        LoginRequestPacket loginPacket = new LoginRequestPacket();
        loginPacket.setUserId(2019);
        loginPacket.setUsername("JZE");
        loginPacket.setPassword("123456abc");

        //进行编码成ByteBuf传输
        ByteBuf data = IMUtil.packetCodeC.encode(loginPacket);
        ctx.writeAndFlush(data);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf responseData = (ByteBuf)msg;

        Packet packet = IMUtil.packetCodeC.decode(responseData);
        if (packet instanceof LoginResponsePacket){
            LoginResponsePacket responsePacket = (LoginResponsePacket)packet;
            if (responsePacket.isSuccess()){
                LoginUtil.markAsLogin(ctx.channel());
                IMUtil.logger.info("登录成功");
            }else {
                IMUtil.logger.info("登录失败:{}", responsePacket.getReason());
            }
        }else if (packet instanceof MessageResponsePacket){
            MessageResponsePacket responsePacket =
                    (MessageResponsePacket) packet;
            IMUtil.logger.info("收到服务器的答复：{}", responsePacket.getMessage());
        }
    }
}
