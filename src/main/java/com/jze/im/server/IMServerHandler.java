package com.jze.im.server;

import com.jze.im.protocol.request.MessageRequestPacket;
import com.jze.im.protocol.response.MessageResponsePacket;
import com.jze.im.util.IMUtil;
import com.jze.im.protocol.request.LoginRequestPacket;
import com.jze.im.protocol.response.LoginResponsePacket;
import com.jze.im.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: 蒋泽恩
 * @date: 2019/7/22 10:48
 * @description:
 */
public class IMServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        //解码
        Packet packet = IMUtil.packetCodeC.decode(byteBuf);
        if (packet instanceof LoginRequestPacket){
            //若为登录packet则创建登录responsePacket进行答复
            LoginResponsePacket responsePacket = new LoginResponsePacket();
            responsePacket.setVersion(packet.getVersion());
            LoginRequestPacket loginPacket = (LoginRequestPacket) packet;
            if (isValid(loginPacket)){
                IMUtil.logger.info("用户{}登录成功", loginPacket.getUsername());
                responsePacket.setSuccess(true);
                ByteBuf responseData = IMUtil.packetCodeC.encode(responsePacket);
                ctx.writeAndFlush(responseData);
            }else {
                IMUtil.logger.info("登录失败");
                responsePacket.setReason("账号密码校验失败");
                responsePacket.setSuccess(false);
            }
        }else if (packet instanceof MessageRequestPacket){
            //若为消息packet则创建messageResponse进行答复
            MessageRequestPacket messagePacket = (MessageRequestPacket) packet;
            MessageResponsePacket messageResponse = new MessageResponsePacket();
            IMUtil.logger.info("收到客户端发送的信息：{}", messagePacket.getMessage());
            messageResponse.setMessage("【服务端答复：已收到信息】--" +
                    messagePacket.getMessage());
            ByteBuf responseData = IMUtil.packetCodeC.encode(messageResponse);
            ctx.channel().writeAndFlush(responseData);
        }
    }

    private static boolean isValid(LoginRequestPacket packet){
        int userId = packet.getUserId();
        String password = packet.getPassword();
        return userId == 2019 && password.equals("123456abc");
    }
}
