package com.jze.example4;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;


/**
 * @author: 蒋泽恩
 * @date: 2019/5/9 21:33
 * @description:
 */
public class MyHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        String eventType = null;
        if(evt instanceof IdleStateEvent){
           IdleStateEvent event = (IdleStateEvent) evt;
           switch (event.state()){
               case READER_IDLE:
                   eventType = "读空闲";
                   break;
               case WRITER_IDLE:
                   eventType = "写空闲";
                   break;
               case ALL_IDLE:
                   eventType = "读写空闲";
                   break;
           }
        }
        System.out.println(ctx.channel().remoteAddress() + "超时事件：" + eventType);
        ctx.channel().close();
    }
}
