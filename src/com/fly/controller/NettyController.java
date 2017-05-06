package com.fly.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fly.netty.codec.protobuf.MsgServer2Client;
import com.fly.service.NettyService;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
 * 消息发送服务
 * @author fly
 *
 */
@Controller
public class NettyController {

	
	@Autowired
	private NettyService nettyService;
	
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
		
		if(msgType.equals("open")) {
			MsgServer2Client.Msg.Builder msgReqbuilder = MsgServer2Client.Msg.newBuilder();
	    	msgReqbuilder.setMsgType(MsgServer2Client.MsgType.open);
	    	msgReqbuilder.setCId(cID);
//				NettySendMsg.sendMsg(channel, msgReqbuilder.build());

			//发送消息
	    	ChannelFutureListener channelFutureListener = new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					if(future.isSuccess()) {
						System.out.println("发送成功");
						//处理记录等
						
					} else {
						//发送失败 处理
						System.out.println("发送失败");
					}
				}
			};
	    	nettyService.sendMsg(sessonId, msgReqbuilder.build(), channelFutureListener);
		}
        
        return "{aa:aa}";
	}
}
