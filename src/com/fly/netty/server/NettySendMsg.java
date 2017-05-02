package com.fly.netty.server;


import com.fly.netty.codec.protobuf.MsgServer2Client;

import io.netty.channel.Channel;

public class NettySendMsg {

	/**
	 * 发送消息
	 */
	public static void sendMsg(Channel channel, MsgServer2Client.Msg msg) {
        channel.writeAndFlush(msg);
        //处理是否发送成功
	}
}
