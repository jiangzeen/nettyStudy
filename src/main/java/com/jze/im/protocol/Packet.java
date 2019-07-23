package com.jze.im.protocol;

import lombok.Data;
import lombok.ToString;

/**
 * @author: 蒋泽恩
 * @date: 2019/7/21 22:09
 * @description:
 */
@Data
@ToString
public abstract class Packet {
    /**
     * 协议版本
     */
    public Byte version = 1;

    /**
     * 指令,用于识别客户端请求
     */
    public abstract Byte getCommand();
}

