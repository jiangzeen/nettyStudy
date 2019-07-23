package com.jze.im.handler.codec;

import com.jze.im.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author: jiangzeen
 * @date: 19-7-23 05:30
 * @description:
 */
public class PacketEncodeHandler extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {

    }
}
