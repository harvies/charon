package io.github.harvies.eris.base.jdk.io.bio;

import lombok.Cleanup;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author harvies
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(9999));
            Socket accept = serverSocket.accept();
            System.out.println("有连接接入");
            @Cleanup InputStream inputStream = accept.getInputStream();
            byte[] bytes = new byte[1024];
            inputStream.read(bytes);
            System.out.printf("有连接接入,收到数据:%s", new String(bytes, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
