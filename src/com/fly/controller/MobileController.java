package com.fly.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fly.netty.server.NettyChannelMap;

import io.netty.channel.Channel;

/**
 *
 * @author fly
 * @version 创建时间：2017年4月6日 类说明
 *
 */
@Controller
public class MobileController {

	/**
	 * 二维码扫描 跳转到指定app(微信、支付宝)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/mobile/saomiao",  method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getDetail(HttpServletRequest request){
	    ModelAndView modelAndView = new ModelAndView();

	    String agent = request.getHeader("User-Agent").toLowerCase();
	    int way = 0;

	    modelAndView.addObject("agent", agent);
	    modelAndView.setViewName("/chongzhi");
	    return modelAndView;
	}
	
	
	/**
	 * 判断充电宝
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/mobile/chongdianbao",  method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getChongdianbao(HttpServletRequest request){
	    ModelAndView modelAndView = new ModelAndView();

	    //判断充电宝情况
	    String mId = (String) request.getAttribute("m_id");
	    int machineState = analyzeMachine(mId);
	    if(machineState == -1) {
		    modelAndView.addObject("msg", "机器未联网");
		    modelAndView.setViewName("/weilianwang");
		    return modelAndView;
	    }
	    
	    if(machineState == 0) {
	    	modelAndView.addObject("msg", "充电宝数量不足");
		    modelAndView.setViewName("/weilianwang");
		    return modelAndView;
	    }
	    
	    // 接着判断执行
	    String openId = (String) request.getAttribute("openId");
	    

	    
	    
	    String agent = request.getHeader("User-Agent").toLowerCase();
	    int way = 0;

	    modelAndView.addObject("agent", agent);
	    modelAndView.setViewName("/chongzhi");
	    return modelAndView;
	}
	
	/**
	 * 三种情况
	 * 判断机器的情况
	 * @param mId
	 * @return
	 */
	private int analyzeMachine(String mId) {
		int state = -1;
		//1. 判断机器是否联网 根据nutty
		Channel channel = NettyChannelMap.getSocketChannel(mId);
		if(channel == null) {
			state = -1;
		}
		
		//2. 判断机器充电宝数量 查询数据库或者缓存
		int count = 0;
		
		if(count <= 1) {
			state = 0;
		}
		
		count = 5;
		//3. 机器联网 充电宝数量大于1
		if(channel!=null && count>=1) {
			state = 1;
		}
		
		return state;
	}
	
	
	
	
	
}
