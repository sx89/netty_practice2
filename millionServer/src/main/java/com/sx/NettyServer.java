package com.sx;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author sunxu93@163.com
 * @date 19/7/26/026 21:38
 */
public class NettyServer {
    public static void main(String[] args) {
        new NettyServer().run(Config.BEGIN_PORT, Config.END_PORT);
    }

    public void run(int beginPort, int endPort) {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_REUSEADDR, true)
                .childHandler(new TcpCountHandler());
        for (; beginPort < endPort; beginPort++) {
            int port = beginPort;
            serverBootstrap.bind(port).addListener(future -> {
                System.out.println("服务端成功绑定端口 port = " + port);
            });

        }
    }
}
