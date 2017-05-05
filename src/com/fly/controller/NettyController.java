package com.fly.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fly.netty.codec.protobuf.MsgClient2Server;
import com.fly.netty.codec.protobuf.MsgServer2Client;
import com.fly.netty.server.MsgReqMap;
import com.fly.netty.server.NettyChannelMap;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
 * 消息发送服务
 * @author fly
 *
 */
@Controller
public class NettyController {

	
	/**
	 * 向客户端发送数据
	 * http://127.0.0.1:8080/send/meassage?sessonID=001&cID=a_2&msgType=open
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/send/meassage", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String sendMeassage(HttpServletRequest request) {
		String msgType = request.getParameter("msgType");
		String sessonId = request.getParameter("sessonID");
		String cID = request.getParameter("cID");
		//获取客户端连接
		Channel channel = NettyChannelMap.getSocketChannel(sessonId);
		MsgClient2Server.Msg msg = MsgReqMap.get(sessonId);
		
		if(channel==null) {
			return "{client:null}";
		} else {
			if(msgType.equals("open")) {
				MsgServer2Client.Msg.Builder msgReqbuilder = MsgServer2Client.Msg.newBuilder();
		    	msgReqbuilder.setMsgType(MsgServer2Client.MsgType.open);
		    	msgReqbuilder.setCId(cID);
//				NettySendMsg.sendMsg(channel, msgReqbuilder.build());
				//发送消息
		        channel.writeAndFlush(msgReqbuilder.build()).addListener(new ChannelFutureListener() {
					@Override
					public void operationComplete(ChannelFuture future) throws Exception {
						if(future.isSuccess()) {
							System.out.println("发送成功");
							//处理记录 
							
						} else {
							//发送失败 处理
							System.out.println("发送失败");
						}
					}
				});
			}
		}
        
        return "{aa:aa}";
	}
}
