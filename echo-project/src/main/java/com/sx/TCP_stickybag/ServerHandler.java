package com.sx.TCP_stickybag;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author sunxu93@163.com
 * @date 19/7/24/024 12:30
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    private int count ;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = msg.toString();
        System.out.println("服务端收到的消息内容为:" + body + ",收到消息的次数为:" + ++count);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
