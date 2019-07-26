package com.sx;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sunxu93@163.com
 * @date 19/7/26/026 21:47
 */
//此注解可以让所有Netty线程访问该Handler
@ChannelHandler.Sharable
public class TcpCountHandler extends ChannelInboundHandlerAdapter {

    private AtomicInteger atomicInteger = new AtomicInteger();

    /**
     * 当构建一个TcpHandler的时候,每隔3秒就执行runnable线程,runnable里面的功能是 打印出当前得原子Integer的连接个数
     */
    public TcpCountHandler() {
        Runnable runnable = () -> System.out.println("当前连接数为" + atomicInteger.get());
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(runnable, 0, 3, TimeUnit.SECONDS);
    }


    /**
     * 连接成功就给 原子Integer加一  连接失效了就减一
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        atomicInteger.incrementAndGet();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        atomicInteger.decrementAndGet();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("TCPHandler,连接错误");
        ctx.close();
    }
}
