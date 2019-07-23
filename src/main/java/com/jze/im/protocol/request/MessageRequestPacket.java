package com.jze.im.protocol.request;

import com.jze.im.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.jze.im.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @author: 蒋泽恩
 * @date: 2019/7/22 13:28
 * @description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageRequestPacket extends Packet {
    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
