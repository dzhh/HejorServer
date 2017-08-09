package com.fly.service.impl;

import org.springframework.stereotype.Service;

import com.fly.netty.codec.protobuf.MsgServer2Client.Msg;
import com.fly.netty.server.NettyChannelMap;
import com.fly.service.NettyService;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;


@Service("NettyService")
public class NettyServiceImpl implements NettyService {

	/**
	 * 发送消息 监听
	 */
	@Override
	public void sendMsg(String sessonId, Msg msg, ChannelFutureListener channelFutureListener) {
		Channel channel = NettyChannelMap.getSocketChannel(sessonId);
		if(channel !=null ) {
        	ByteBuf buf = Unpooled.buffer(4);  
        	buf.writeByte(0x56);  
        	buf.writeByte(0x01); 
        	buf.writeByte(0xAA); 
        	buf.writeByte(0x01); 
			channel.writeAndFlush(buf).addListener(channelFutureListener);
		}
	}

	
}
