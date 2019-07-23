package com.jze.example3;

import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/9 15:05
 * @description: serverHandler
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {
    //用来保存channel对象,默认的构造
    private static ChannelGroup channelGroup =
            new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        //当任意一个客户端发来消息时,进行处理
        Channel channel = ctx.channel();
        channelGroup.forEach(ch->{
            if(ch == channel){
               ch.writeAndFlush( "[自己]:"+ msg + "\n");
            }else {
                ch.writeAndFlush("[用户-" + channel.remoteAddress() +
                        "]:"  + msg + "\n");
            }
        });
    }


    //连接建立好后，进行回调
    @Override
    public void handlerAdded(ChannelHandlerContext ctx){
        //ChannelHandlerContext可以获取到channel对象
        Channel channel = ctx.channel();
        /*
        向通道发送消息--广播，服务器向其他channel发送消息xxx已经上线-->需要保存好所有的channel对象
        channelGroup也有自己的writeAndFlush()方法，调用该方法后会调用group里面所有的channel的writeAndFlush()方法
        */
        channelGroup.writeAndFlush("[服务器]---" + channel.remoteAddress() +
                "加入\n");
        channelGroup.add(channel);
    }

    //客户端连接断开后，进行回调
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[服务器]---" + channel.remoteAddress() +
                "离开\n");
        /* channelGroup会自动将已经断开的channel移除group */

    }

    //连接活动状态时
    @Override
    public void channelActive(ChannelHandlerContext ctx){
        System.out.println((ctx.channel().remoteAddress() + "上线"));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println(ctx.channel().remoteAddress() + "下线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)  {
        cause.printStackTrace();
        ctx.close();
    }
}
