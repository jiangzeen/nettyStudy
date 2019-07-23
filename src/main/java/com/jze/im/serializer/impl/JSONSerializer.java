package com.jze.im.serializer.impl;

import com.alibaba.fastjson.JSON;
import com.jze.im.serializer.Serializer;
import com.jze.im.serializer.SerializerAlgorithm;

/**
 * @author: 蒋泽恩
 * @date: 2019/7/21 22:27
 * @description: 使用alibaba的fastjson进行序列化和反序列化
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serializer(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserializer(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
