package com.fly.netty.server;

import com.fly.netty.common.NettyMessage;
import com.fly.netty.util.JsonUtil;

import io.netty.channel.Channel;

public class NettySendMsg {

	/**
	 * 发送消息
	 */
	public static void sendMsg(Channel channel, NettyMessage nettyMessage) {
        channel.writeAndFlush(JsonUtil.beanToJson(nettyMessage));
        
      //如何记录发送状态
	}
}
