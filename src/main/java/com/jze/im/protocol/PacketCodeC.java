package com.jze.im.protocol;

import com.jze.im.protocol.command.Command;
import com.jze.im.serializer.impl.JSONSerializer;
import com.jze.im.serializer.Serializer;
import com.jze.im.protocol.request.LoginRequestPacket;
import com.jze.im.protocol.response.LoginResponsePacket;
import com.jze.im.protocol.request.MessageRequestPacket;
import com.jze.im.protocol.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author: 蒋泽恩
 * @date: 2019/7/21 22:39
 * @description: 对包进行序列化封装
 */
public class PacketCodeC {
    /**
     * 定义好魔数，用于识别是否为我们自定义好的通信协议包
     */
    private static final int MAGIC_NUMBER = 0x12345678;

    /**
     * 对packet包进行序列化，
     * @param packet 通信数据包
     * @return 封装好的ByteBuf
     */
    public ByteBuf encode(Packet packet){
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();

        byte[] bytes = Serializer.DEFAULT.serializer(packet);

        /*
         * 对数据进行写入：
         * 1.写入魔数：用于识别通信协议包
         * 2.写入协议版本
         * 3.写入序列化算法，用于反序列化出Java对象
         * 4.写入数据长度
         * 5.写入请求指令：用于识别请求
         * 6.写入请求数据
         */
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    /**
     * 对数据进行解码
     * @param byteBuf 包含通信包数据
     * @return Java对象packet
     */
    public Packet decode(ByteBuf byteBuf){
        //跳过魔数4个字节，跳过版本号1个字节
        byteBuf.skipBytes(4);
        byteBuf.skipBytes(1);

        //获取序列化算法
        byte serializerAlgorithm = byteBuf.readByte();

        //获取指令
        byte command = byteBuf.readByte();

        //获取数据长度
        int length = byteBuf.readInt();

        byte[] data = new byte[length];
        byteBuf.readBytes(data);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializerAlgorithm);
        if (requestType != null && serializer != null){
            return serializer.deserializer(requestType, data);
        }
        return null;
    }

    /**
     * 获取通信包的原序列化类 根据序列化算法<code>serializerAlgorithm</code>
     * @param serializerAlgorithm 序列化算法
     * @return Serializer
     */
    private Serializer getSerializer(byte serializerAlgorithm) {
        //JSON序列化方式
        if (serializerAlgorithm == Serializer.JSON_Serializer){
            return  new JSONSerializer();
        }
        return null;
    }

    /**
     * 获取所需序列化的通信包的类型根据command指令
     * @param command 请求指令
     * @return packet的类型
     */
    private Class<? extends Packet> getRequestType(byte command) {
        //这是登录指令
        if (command == Command.LOGIN_REQUEST){
            return LoginRequestPacket.class;
        }
        if (command == Command.LOGIN_RESPONSE){
            return LoginResponsePacket.class;
        }
        if (command == Command.MESSAGE_REQUEST){
            return MessageRequestPacket.class;
        }
        if (command == Command.MESSAGE_RESPONSE){
            return MessageResponsePacket.class;
        }
        return null;
    }
}
