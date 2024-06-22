package io.github.harvies.charon.util.io.bio;

import lombok.Cleanup;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author harvies
 */
public class BioClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("127.0.0.1", 9999));
            System.out.println("连接成功");
            @Cleanup OutputStream outputStream = socket.getOutputStream();
            System.out.println("开始写入数据");
            outputStream.write("你好".getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
