package com.fly.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fly.model.M2Power;
import com.fly.model.Machine;
import com.fly.model.Order;
import com.fly.model.User;
import com.fly.service.M2PowerService;
import com.fly.service.MachineService;
import com.fly.service.OrderService;
import com.fly.service.UserService;
import com.fly.util.AscPowerComparator;
import com.fly.util.JsonUtil;


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
	
	@Autowired
	private UserService usrService;
	
	@Autowired
	private OrderService orderService;
	
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
	 * @param request  http://127.0.0.1:8080/mobile/rent?m_id=1CSb5BSoG5SaiNKQIgKnWBjKR8TkEVdV&openId=o5UR3xFIif1N2qtNNc4HHsYxMohg
	 * @return
	 */
	@RequestMapping(value="/mobile/rent",  method = {RequestMethod.GET, RequestMethod.POST},produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getPowerSeril(@RequestParam(value="m_id") String mId, @RequestParam(value="openId") String userId){
	    ModelAndView modelAndView = new ModelAndView();	
	    //判断充电宝情况
//	    String mId = (String) request.getAttribute("m_id");
//	    String userId = (String) request.getAttribute("openId");

	    M2Power powerInfo = analyzeMachine(mId);
	    if(powerInfo == null) {
	    	Map<String, String> map = new HashMap<String, String>();
	    	map.put("req", "-1");
	    	map.put("msg", "该机器暂不能使用");
			String json = JsonUtil.beanToJson(map);
		    return json;
	    }
	    // 接着判断用户
	    int userState = analyzeUser(userId);
	    if(userState ==1 && powerInfo !=null){
	    	//通知机器
	    	modelAndView.addObject("cId", powerInfo.getcId());
			String json = "";
			//修改机器关系表状态 (netty 收到回应信息后修改)
			//生成订单
			Order order = addOrderForUser(userId, powerInfo);
			int resp = orderService.insert(order);
			if(resp == 1){
				//返回订单
//				request.setAttribute("order", order);
				json = JsonUtil.beanToJson(order);
			}
		    return json;
		    
	    }else if(userState == -1){
	    	//交押金
	    	Map<String, String> map = new HashMap<String, String>();
	    	map.put("req", "0");
	    	map.put("recharge", "100");
	    	map.put("msg", "请缴纳押金");
			String json = JsonUtil.beanToJson(map);
		    return json;
		    
	    }else{
	    	//充值
	    	Map<String, String> map = new HashMap<String, String>();
	    	map.put("req", "0");
	    	map.put("msg", "余额不足请充值");
	    	map.put("recharge", String.valueOf(userState));
			String json = JsonUtil.beanToJson(map);
		    return json;
	    }
	}
	
	/**
	 * 更换充电宝
	 * @param request  http://127.0.0.1:8080/mobile/rent?m_id=1CSb5BSoG5SaiNKQIgKnWBjKR8TkEVdV&openId=o5UR3xFIif1N2qtNNc4HHsYxMohg&orderId=1111111111
	 * @return
	 */
	@RequestMapping(value="/mobile/change",  method = {RequestMethod.GET, RequestMethod.POST},produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getChangePowerSeril(@RequestParam(value="m_id") String mId, @RequestParam(value="openId") String userId, @RequestParam(value="orderId") String orderId){
		
		if(mId !=null&& mId.length()>0 || userId != null&& userId.length()>0  || orderId != null&& orderId.length()>0 ){
			
			Order order = orderService.selectByPrimaryKey(orderId);
	    	Map<String, String> respMap = new HashMap<String, String>();
	    	//判断机器是慢的还是空的
	    	int powerNum = getPowerNum(mId);
	    	if(powerNum == 0 || powerNum == 6){
	    		//无空仓还，没有可借的
	    	}
	    	//监测netty接收归还消息，powerId是否一致
	    	
			return null;
			
		}else{
			return null;
		}
	}
	/**
	 * 检测订单中充电宝能否更换
	 * @param request  http://127.0.0.1:8080/mobile/rent?m_id=1CSb5BSoG5SaiNKQIgKnWBjKR8TkEVdV&openId=o5UR3xFIif1N2qtNNc4HHsYxMohg&orderId=1111111111
	 * @return
	 */
	
	@RequestMapping(value="/mobile/check",  method = {RequestMethod.GET, RequestMethod.POST},produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String checkChangeState( @RequestParam(value="orderId") String orderId){
		if(orderId != null && orderId.length()>0){
			Order order = orderService.selectByPrimaryKey(orderId);
			int isChange = order.getIsChange();
			String outTime= order.getOutTime();
			int rentHours = getRentHour(outTime);
	    	Map<String, String> map = new HashMap<String, String>();
			if(rentHours < 48){
		    	map.put("req", "-1");
		    	map.put("msg", "租借未超过48小时，无法更换");
				String json = JsonUtil.beanToJson(map);
			    return json;
			}else if(rentHours > 8760){
				//超过一年无法更换
		    	map.put("req", "-1");
		    	map.put("msg", "租借已经超过一年，无法更换");
				String json = JsonUtil.beanToJson(map);
			    return json;
			}else{
		    	map.put("req", "1");
		    	map.put("msg", "更换");
				String json = JsonUtil.beanToJson(map);
			    return json;
			}
		}else{
			return null;
		}
	}
	private int getRentHour(String outTime){
//		Timestamp curStamp = new Timestamp(System.currentTimeMillis());
		Long curTime = System.currentTimeMillis();
//		Date curDate = new Date(curStamp.getTime());
//		Timestamp outStamp = new Timestamp(Long.parseLong(outTime));
//		Date outDate = new Date(outStamp.getTime());
		Long orderTime = Long.parseLong(outTime);
		long diff = curTime - orderTime;
		long diffHours = diff / (60 * 60 * 1000);
		
		return (int)diffHours;
	}
	
	/**
	 * 新建租赁订单
	 * @param 
	 * @return
	 */
	private Order addOrderForUser(String userId, M2Power mpower){
		Order order = new Order();
		String timeStr = Long.toString(System.currentTimeMillis());
		int num = (int)(Math.random()*1000);
		String orderId = timeStr + num;
		order.setOrderId(orderId);
		order.setUserid(userId);
		order.setmId(mpower.getmId());
		order.setPowerId(mpower.getPowerId());
		order.setmId(mpower.getmId());
		order.setOutTime(timeStr);
		order.setIsChange(0);
		order.setTotalFee(0);
		order.setOrderState(0);
		return order;
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
		List<M2Power> newpower = new ArrayList<M2Power>();
        for (M2Power mpower : powerList) {
            if(mpower.getPower().getpQuantity()> 50){
            	newpower.add(mpower);
            }
        }
		Collections.sort(newpower, new AscPowerComparator());
		M2Power p = powerList.get(0);
		return p;
	}
	private int analyzeUser(String userId){
		
		int state = -1;
		User usr = usrService.find(userId);
		//不存在用户
		if(usr == null){
			return -1;
		}
		//拉黑用户
		
		//余额判断
		int balance = usr.getBalance();
		if(balance > 80){
			return 1;//发送消息到机器
		}else{
			return (100 - balance);
		}
	}
	
	/**
	 * 三种情况
	 * 判断机器的情况
	 * @param mId  返回值0的时候没有可借，-1的时候，机器不可用，1-6的时候，机舱编号
	 * @return
	 */
	private M2Power analyzeMachine(String mId) {
		M2Power mpower = null;
		//1. 判断机器是否联网 根据nutty
//		Channel channel = NettyChannelMap.getSocketChannel(mId);
//		if(channel == null) {
//			state = -1;
//		}
		//数据库查询判断机器状态
		Machine machine = machineService.selectByPrimaryKey(mId);
		int mstate = 0;
		if(machine != null) {
			mstate = machine.getmState();
			if(mstate == 0){
				return null;
			}
		} else {
			return null;
		}

		
		//2. 判断机器充电宝数量 查询数据库或者缓存
	    List<M2Power> powerList = m2PowerService.selectByM_Id(mId);
	    int count = 0;
		if(powerList != null) {
			count = powerList.size();
			if(count < 1) {
				return null;
			}
		} else {
			return null;
		}
	    
		//3. 机器联网 充电宝数量大于1
//		if(channel!=null && count>=1) {
//			state = 1;
//		}
		if(mstate==1 && count >=1){
			mpower = getPower(powerList);
		}
		return mpower;
	}
	
	private int getPowerNum(String mId){
	    List<M2Power> powerList = m2PowerService.selectByM_Id(mId);
		int count = powerList.size();
		return count;
	}

}
