package com.sx;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.ExecutionException;

/**
 * @author sunxu93@163.com
 * @date 19/7/26/026 22:03
 */
public class NettyClient {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new NettyClient().run(Config.BEGIN_PORT, Config.END_PORT);

    }

    public void run(int beginPort, int endPort) throws ExecutionException, InterruptedException {
        System.out.println("客户端启动");
        NioEventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {

            }
        });
        int index = 0;
        int finalPort;
        while (true) {
            finalPort = beginPort + index;
            index++;
            bootstrap.connect(Config.SERVER, finalPort).addListener(future -> {
                if (!future.isSuccess()) {
                    System.out.println("创建连接失败");

                }
            }).get();
            if (index == (endPort - beginPort)) {
                index = 0;
            }
        }
    }
}
