package com.jze.im.protocol.command;

/**
 * @author: 蒋泽恩
 * @date: 2019/7/21 22:12
 * @description:
 */
public interface Command {
    byte LOGIN_REQUEST = 1;
    byte LOGIN_RESPONSE = 2;
    byte MESSAGE_REQUEST = 3;
    byte MESSAGE_RESPONSE = 4;
}

