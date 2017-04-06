package com.fly.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.util.CharsetUtil;

//��ʶ�����ʵ��֮������� channel ���湲��
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ByteBuf in = (ByteBuf) msg;
		//��־��Ϣ���������̨
		System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));
		//�������յ���Ϣ���ظ������ߡ�ע�⣬�⻹û�г�ˢ����
		ctx.write(in);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		//��ˢ���д�����Ϣ��Զ�̽ڵ㡣�ر�ͨ���󣬲������
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		//��ӡ�쳣��ջ����
		cause.printStackTrace();
		//�ر�ͨ��
		ctx.close();
	}
}
