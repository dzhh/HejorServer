package com.fly.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fly.model.M2Power;
import com.fly.model.Machine;
import com.fly.model.Power;
import com.fly.netty.server.NettyChannelMap;
import com.fly.service.M2PowerService;
import com.fly.service.MachineService;
import com.fly.util.AscPowerComparator;
import com.fly.util.JsonUtil;

import io.netty.channel.Channel;

/**
 *
 * @author fly
 * @version 创建时间：2017年4月6日 类说明
 *
 */
@Controller
public class MobileController {
	
	@Autowired
	private MachineService machineService;
	
	@Autowired
	private M2PowerService m2PowerService;
	
	/**
	 * 二维码扫描 跳转到指定app(微信、支付宝)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/mobile/saomiao",  method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getDetail(HttpServletRequest request){
	    ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("msg", "充电宝数量不足");

//	    String agent = request.getHeader("User-Agent").toLowerCase();
//	    int way = 0;
//
//	    modelAndView.addObject("agent", agent);
//	    modelAndView.setViewName("/chongzhi");
	    return modelAndView;
	}
	
	
	/**
	 * 判断充电宝
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/mobile/rent",  method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getPowerSeril(HttpServletRequest request){
	    ModelAndView modelAndView = new ModelAndView();
//	    Machine machine = machineService.selectByPrimaryKey("1CSb5BSoG5SaiNKQIgKnWBjKR8TkEVdV");
	    List<M2Power> power = m2PowerService.selectByM_Id("1CSb5BSoG5SaiNKQIgKnWBjKR8TkEVdV");
	    M2Power mpower = getPower(power);
	    modelAndView.addObject(mpower);
		return modelAndView;
	    //判断充电宝情况
//	    String mId = (String) request.getAttribute("m_id");
//	    int machineState = analyzeMachine(mId);
//	    if(machineState == -1) {
//		    modelAndView.addObject("msg", "机器未联网");
//		    modelAndView.setViewName("/weilianwang");
//		    return modelAndView;
//	    }
//	    
//	    if(machineState == 0) {
//	    	modelAndView.addObject("msg", "充电宝数量不足");
//		    modelAndView.setViewName("/weilianwang");
//		    return modelAndView;
//	    }
//	    
//	    // 接着判断执行
//	    String openId = (String) request.getAttribute("openId");
//	    
//
//	    
//	    
//	    String agent = request.getHeader("User-Agent").toLowerCase();
//	    int way = 0;
//
//	    modelAndView.addObject("agent", agent);
//	    modelAndView.setViewName("/chongzhi");
//	    return modelAndView;
	}
	
	/**
	 * 判断充电宝的情况 循环次数最少的
	 * @param 
	 * @return
	 */
	private M2Power getPower(List<M2Power> powerList){
		if(powerList.size() <=0){
			return null;
		}
		Collections.sort(powerList, new AscPowerComparator());
		M2Power p = powerList.get(0);
		return p;
	}
//	private static class AscPowerComparator implements Comparator<M2Power>{
//
//		@Override
//		public int compare(M2Power o1, M2Power o2) {
//			// TODO Auto-generated method stub
//			return o1.getPower().getpCount() - o2.getPower().getpCount();
//		}
//	}
	
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
