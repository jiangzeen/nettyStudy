package com.jze.im.protocol.request;

import com.jze.im.protocol.command.Command;
import com.jze.im.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author: 蒋泽恩
 * @date: 2019/7/21 22:13
 * @description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class LoginRequestPacket extends Packet implements Command {

    private Integer userId;
    private String username;
    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
