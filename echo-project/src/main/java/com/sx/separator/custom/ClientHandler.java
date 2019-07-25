package com.sx.separator.custom;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author sunxu93@163.com
 * @date 19/7/24/024 12:30
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String message = "Netty is a NIO client server framework which enables quick&_" +
                "and easy development of network applications&_ " +
                "such as protocol servers and clients.&_" +
                " It greatly simplifies and streamlines&_" +
                "network programming such as TCP and UDP socket server.&_";

        ByteBuf mes = null;

        mes = Unpooled.buffer(message.getBytes().length);
        mes.writeBytes(message.getBytes());
        ctx.writeAndFlush(mes);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
