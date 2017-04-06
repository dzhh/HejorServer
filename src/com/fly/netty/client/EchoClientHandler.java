package com.fly.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

	//�������Ӻ�� channelActive() ����������һ�Ρ��߼��ܼ򵥣�һ�����������ӣ��ֽ����б����͵���������
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer(Thread.currentThread().getName() + "  Netty rocks!", CharsetUtil.UTF_8));
    }

    /**
     * ���ַ������ڽ��յ�����ʱ�����á�ע�⣬�ɷ����������͵���Ϣ�����Կ����ʽ�����ա�
     * ���������������� 5 ���ֽ��ǲ��Ǳ�֤���е� 5 ���ֽڻ������յ� - ��ʹ��ֻ�� 5 ���ֽڣ�
     * channelRead0() �����ɱ��������Σ���һ����һ��ByteBuf��Netty���ֽ�������
     * װ��3���ֽں͵ڶ���һ�� ByteBuf װ�� 2 ���ֽڡ�ΨһҪ��֤���ǣ�
     * ���ֽڽ��������Ƿ��͵�˳��ֱ𱻽��ա�
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