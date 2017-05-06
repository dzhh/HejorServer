package com.fly.service;

import com.fly.netty.codec.protobuf.MsgServer2Client;

import io.netty.channel.ChannelFutureListener;

/**
 * 
 * @author fly
 *
 */
public interface NettyService {
	
	public void sendMsg(String sessonId, MsgServer2Client.Msg msg, ChannelFutureListener channelFutureListener);
}
