package com.jze.im.util;

import com.jze.im.protocol.PacketCodeC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: 蒋泽恩
 * @date: 2019/7/22 10:34
 * @description:
 */
public abstract class IMUtil {
    public static Logger logger = LoggerFactory.getLogger("CONSOLE");
    public static PacketCodeC packetCodeC = new PacketCodeC();
}
