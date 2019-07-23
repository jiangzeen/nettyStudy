package com.jze.im.protocol.response;

import com.jze.im.protocol.command.Command;
import com.jze.im.protocol.Packet;
import lombok.Data;

/**
 * @author: 蒋泽恩
 * @date: 2019/7/22 10:57
 * @description:
 */
@Data
public class LoginResponsePacket extends Packet {

    private boolean success = false;
    private String reason;
    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
