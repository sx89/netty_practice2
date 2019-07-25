package com.sx.separator.line;


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
        ByteBuf mes = null;
        byte[] bytes = ("你好"+System.getProperty("line.separator")).getBytes();
        for (int i = 0; i < 100; i++) {
            mes = Unpooled.buffer(bytes.length);
            mes.writeBytes(bytes);
            ctx.writeAndFlush(mes);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
