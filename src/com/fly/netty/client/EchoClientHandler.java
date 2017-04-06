package com.fly.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

	//建立连接后该 channelActive() 方法被调用一次。逻辑很简单：一旦建立了连接，字节序列被发送到服务器。
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer(Thread.currentThread().getName() + "  Netty rocks!", CharsetUtil.UTF_8));
    }

    /**
     * 这种方法会在接收到数据时被调用。注意，由服务器所发送的消息可以以块的形式被接收。
     * 即，当服务器发送 5 个字节是不是保证所有的 5 个字节会立刻收到 - 即使是只有 5 个字节，
     * channelRead0() 方法可被调用两次，第一次用一个ByteBuf（Netty的字节容器）
     * 装载3个字节和第二次一个 ByteBuf 装载 2 个字节。唯一要保证的是，
     * 该字节将按照它们发送的顺序分别被接收。
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));    //3
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {                    //4
        cause.printStackTrace();
        ctx.close();
    }
}