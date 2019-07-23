package com.sx.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * @author sunxu93@163.com
 * @date 19/7/21/021 18:54
 */
public class EchoServer {
    private int port ;
    public EchoServer(int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(10);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    /*
                    默认为false
                    为true,代表关闭nagle算法,关闭包最大再传输,提高的传输效率,减少的传输次数,但降低了实时性
                     */
                    .option(ChannelOption.TCP_NODELAY, true)
                    /*
                    tcp的等待队列;有半连接队列和全连接队列,半连接队列
                    so_backlog是作用于全连接队列   SO_BACKLOG的值  与 系统默认的somaxconn  取较小值
                    半连接队列处有洪水攻击
                    百万连接的时候,需要修改SO_BACKLOG的值

                     */
                    .option(ChannelOption.SO_BACKLOG, 1000)
                    /*
                    创建handler来处理请求
                     */
                    .childHandler(
                            new ChannelInitializer<SocketChannel>() {

                                @Override
                                protected void initChannel(SocketChannel socketChannel) throws Exception {
                                    socketChannel.pipeline().addLast(new EchoServerHandler());

                                }
                            }
                    );
            System.out.println("echo服务器启动成功");
            //启动的时候绑定端口,此过程是同步的, 其他过程是异步非阻塞
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            //同步等待服务器监听端口关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println();
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        new EchoServer(port).run();
    }
}
