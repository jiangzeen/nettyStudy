package com.jze.io;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/8 22:28
 * @description:
 */
public class IOIServer {
    public static void main(String[] args) throws IOException {
        server(80);
        client(80);
    }

    public static void server(int port) throws IOException {
        final ServerSocket ss = new ServerSocket(port);
        try {
            for (; ; ) {
                final Socket s = ss.accept();
                System.out.println("连接到一个客户端socket" + s);
                new Thread(() -> {
                    OutputStream out;
                    try {
                        out = s.getOutputStream();
                        out.write("hello".getBytes(CharsetUtil.UTF_8));
                        out.flush();
                        s.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            s.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void client(int port) throws IOException {
        Socket s = new Socket("127.0.0.1", port);

    }
}