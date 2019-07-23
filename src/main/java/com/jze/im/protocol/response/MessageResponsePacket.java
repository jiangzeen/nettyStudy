package com.jze.im.protocol.response;

import com.jze.im.protocol.Packet;
import lombok.Data;

import static com.jze.im.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @author: 蒋泽恩
 * @date: 2019/7/22 13:29
 * @description:
 */
@Data
public class MessageResponsePacket extends Packet {

    private String message;


    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
