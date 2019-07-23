package com.jze.im.server;

import com.jze.im.util.IMUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author: 蒋泽恩
 * @date: 2019/7/22 10:42
 * @description:
 */
public class IMServer {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new IMServerInitializer());
            ChannelFuture future = bootstrap.bind(8080).sync();
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
            IMUtil.logger.error("服务器出现异常");
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
