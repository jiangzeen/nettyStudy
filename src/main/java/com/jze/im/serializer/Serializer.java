package com.jze.im.serializer;

import com.jze.im.serializer.impl.JSONSerializer;

/**
 * @author: 蒋泽恩
 * @date: 2019/7/21 22:17
 * @description: 自定义tcp包序列化接口
 */
public interface Serializer {

    byte JSON_Serializer = 1;
    
    /**
     * 默认序列化算法为JSON
     */
    Serializer DEFAULT = new JSONSerializer();
    /**
     * @return 序列化算法名称
     */
    byte getSerializerAlgorithm();

    /**
     *
     * @return 序列化后字节数组
     */
    byte[] serializer(Object object);

    /**
     * 对bytes数组进行反序列化
     * @param clazz
     * @param bytes
     * @param <T> Java对象
     * @return 反序列化后的java对象
     */
    <T> T deserializer(Class<T> clazz, byte[] bytes);
}
