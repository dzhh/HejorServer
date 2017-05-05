package com.fly.service.impl;

import org.springframework.stereotype.Service;

import com.fly.netty.codec.protobuf.MsgServer2Client.Msg;
import com.fly.netty.server.NettyChannelMap;
import com.fly.service.NettyService;

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
			channel.writeAndFlush(msg).addListener(channelFutureListener);
		}
	}

	
}
