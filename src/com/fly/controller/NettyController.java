package com.fly.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fly.netty.codec.protobuf.MsgClient2Server;
import com.fly.netty.codec.protobuf.MsgServer2Client;
import com.fly.netty.common.Body;
import com.fly.netty.common.Header;
import com.fly.netty.common.MessageType;
import com.fly.netty.common.NettyMessage;
import com.fly.netty.server.MsgReqMap;
import com.fly.netty.server.NettyChannelMap;
import com.fly.netty.server.NettySendMsg;
import com.fly.netty.util.JsonUtil;

import io.netty.channel.Channel;

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
				NettySendMsg.sendMsg(channel, msgReqbuilder.build());
			}
		}
        
        return "{aa:aa}";
	}
}
