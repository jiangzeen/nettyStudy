package com.jze.im.attribute;

import io.netty.util.AttributeKey;

/**
 * @author: 蒋泽恩
 * @date: 2019/7/22 13:37
 * @description:
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
