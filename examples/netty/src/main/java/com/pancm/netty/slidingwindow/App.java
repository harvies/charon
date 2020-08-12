package com.pancm.netty.slidingwindow;

import com.pancm.netty.slidingwindow.client.NettyClient;
import com.pancm.netty.slidingwindow.server.NettyServer;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        //启动服务端和客户端
        new Thread(() -> new NettyServer().run()).start();
        new Thread(() -> new NettyClient().run()).start();


    }
}
