package com.fly.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fly.netty.common.Body;
import com.fly.netty.common.Header;
import com.fly.netty.common.MessageType;
import com.fly.netty.common.NettyMessage;
import com.fly.netty.server.NettyChannelMap;
import com.fly.netty.server.NettySendMsg;
import com.fly.netty.util.JsonUtil;

import io.netty.channel.Channel;

/**
 * 发送服务
 * @author fly
 *
 */
@Controller
public class NettyController {

	/**
	 * 向客户端发送数据
	 * http://127.0.0.1:8080/send/meassage?sessonID=001&msgBody=hello
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/send/meassage", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String login_mobile(HttpServletRequest request) {
		String sessonId = request.getParameter("sessonID");
		String msgBody = request.getParameter("msgBody");
		
		Channel channel = NettyChannelMap.getSocketChannel(sessonId);
		
		NettyMessage nettyMessage = new NettyMessage();
    	Header header = new Header();
    	header.setType(MessageType.SENDMSG_REQ.value());
    	nettyMessage.setHeader(header);
    	
    	Body body = new Body();
    	body.setMsgBody(msgBody);
    	nettyMessage.setBody(body);
    	
    	//发送
    	if(channel!=null && nettyMessage!=null) {
        	NettySendMsg.sendMsg(channel, nettyMessage);
    	}
        
        return "{aa:aa}";
	}
}
