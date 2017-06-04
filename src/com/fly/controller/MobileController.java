package com.fly.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fly.common.redis.RedisUtil;
import com.fly.model.M2Power;
import com.fly.model.Machine;
import com.fly.model.Order;
import com.fly.model.Paylist;
import com.fly.model.User;
import com.fly.netty.codec.protobuf.MessageType;
import com.fly.netty.codec.protobuf.MsgServer2Client;
import com.fly.netty.server.NettyChannelMap;
import com.fly.service.M2PowerService;
import com.fly.service.MachineService;
import com.fly.service.NettyService;
import com.fly.service.OrderService;
import com.fly.service.PaylistService;
import com.fly.service.UserService;
import com.fly.util.AscPowerComparator;
import com.fly.util.CommonUtil;
import com.fly.util.JsonUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import net.sf.json.JSONObject;


/**
 *
 * @author fly
 * @version 创建时间：2017年4月6日 类说明
 *
 */
@Controller
public class MobileController {
	
	
	@Autowired
	private M2PowerService m2PowerService;
	
	@Autowired
	private UserService usrService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private NettyService nettyService;
	
	@Autowired
	private PaylistService payService;
	
	/**
	 * 判断充电宝 租用
	 * openId 用户id  
	 * m_id 机器编码
	 * @param request  http://127.0.0.1:8080/mobile/rent?m_id=1CSb5BSoG5SaiNKQIgKnWBjKR8TkEVdV&openId=o5UR3xFIif1N2qtNNc4HHsYxMohg
	 * @return
	 */
	@RequestMapping(value="/mobile/rent",  method = {RequestMethod.GET, RequestMethod.POST},produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getPowerSeril(@RequestParam(value="m_id") String mId, @RequestParam(value="openId") String userId){
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
	    	//通知机器app
	    	MsgServer2Client.Msg.Builder msgReqbuilder = MsgServer2Client.Msg.newBuilder();
	    	msgReqbuilder.setMsgType(MessageType.MsgType.OPEN);
	    	msgReqbuilder.setCId(powerInfo.getcId());
	    	
			//发送消息
	    	ChannelFutureListener channelFutureListener = new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					if(future.isSuccess()) {
						System.out.println("打开充电舱消息发送成功");
						//处理记录等
						
					} else {
						//发送失败 处理
						System.out.println("打开充电舱消息发送失败");
					}
				}
			};
			//发送消息
	    	nettyService.sendMsg(mId, msgReqbuilder.build(), channelFutureListener);
	    	
			String json = "";			
			//生成订单
			Order order = addOrderForUser(userId, powerInfo);
			order.setIsPay(1);
			int resp = orderService.insert(order);
			if(resp == 1){
				//保存下orderid 和 powerid的关系
				RedisUtil.putOrder(order.getPowerId(), order);
				
		    	Map<String, String> respMap = new HashMap<String, String>();
	    		respMap.put("orderId", order.getOrderId());
	    		
	    		String outTime = order.getOutTime();
	            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            long lt = new Long(outTime);
	            Date date = new Date(lt);
	            outTime = simpleDateFormat.format(date);
	            respMap.put("req", "1");
	    		respMap.put("outTime", outTime);
	    		respMap.put("powerId", order.getPowerId());
	    		json = JsonUtil.beanToJson(respMap);
			}
		    return json;
		    
	    }else if(userState == -1){
			//生成订单
			Order order = addOrderForUser(userId, powerInfo);
			order.setIsPay(0);
			int resp = orderService.insert(order);
	    	//交押金,用户有未结束订单
	    	Map<String, String> map = new HashMap<String, String>();
	    	map.put("req", "0");
	    	map.put("recharge", "100");
	    	map.put("msg", "请缴纳押金");
	    	map.put("btnName", "缴纳押金");
			String json = JsonUtil.beanToJson(map);
		    return json;
		    
	    }else if(userState == 0){
	    	//新用户
			//生成订单
			Order order = addOrderForUser(userId, powerInfo);
			order.setIsPay(0);
			int resp = orderService.insert(order);
			
	    	Map<String, String> map = new HashMap<String, String>();
	    	map.put("req", "0");
	    	map.put("recharge", "100");
	    	map.put("msg", "押金随心退，安全速到账");
	    	map.put("name", "缴纳押金");
			String json = JsonUtil.beanToJson(map);
		    return json;
	  
	    }else{
			//生成订单
			Order order = addOrderForUser(userId, powerInfo);
			order.setIsPay(0);
			int resp = orderService.insert(order);
	    	//充值
	    	Map<String, String> map = new HashMap<String, String>();
	    	map.put("req", "0");
	    	map.put("msg", "余额不足请充值");
	    	map.put("recharge", String.valueOf(userState));
	    	map.put("name", "充值");
			String json = JsonUtil.beanToJson(map);
		    return json;
	    }
	}
	
	/**
	 * 更换充电宝
	 * @param request  127.0.0.1:8080/mobile/change?openId=o5UR3xFIif1N2qtNNc4HHsYxMohg&m_id=1CSb5BSoG5SaiNKQIgKnWBjKR8TkEVdV&orderId=1493659527477152
	 * @return
	 */
	@RequestMapping(value="/mobile/change",  method = {RequestMethod.GET, RequestMethod.POST},produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getChangePowerSeril(@RequestParam(value="m_id") String mId, @RequestParam(value="orderId") String orderId){
		
		if(mId !=null&& mId.length()>0 || orderId != null&& orderId.length()>0 ){
			
			Order order = orderService.selectByPrimaryKey(orderId);
	    	Map<String, String> respMap = new HashMap<String, String>();
	    	//判断机器是满的还是空的
	    	int powerNum = getPowerNum(mId);
	    	if(powerNum == 0 || powerNum == 6){
	    		//无空仓还，没有可借的
	    		respMap.put("req", "0");
	    		respMap.put("msg", "该机器没有可更换的充电宝");
				String json = JsonUtil.beanToJson(respMap);
			    return json;
	    	}else{
		    	// 未完成  通知app进入更换状态，redis缓存orderId与powerId，powerId作为key
				MsgServer2Client.Msg.Builder msgReqbuilder = MsgServer2Client.Msg.newBuilder();
				msgReqbuilder.setMsgType(MessageType.MsgType.CHANGE_MODE);
				msgReqbuilder.setMsgInfo(orderId);
				
		    	ChannelFutureListener channelFutureListener = new ChannelFutureListener() {
					@Override
					public void operationComplete(ChannelFuture future) throws Exception {
						if(future.isSuccess()) {
							System.out.println("打开充电舱消息发送成功");
							//处理记录等
							
						} else {
							//发送失败 处理
							System.out.println("打开充电舱消息发送失败");
						}
					}
				};
				
				RedisUtil.putChangeOrder(order.getPowerId(), order);
	    		nettyService.sendMsg(mId, msgReqbuilder.build(), channelFutureListener);
	    		return "geng huan zhong";
	    	}
		}else{
			return "error";
		}
	}
	/**
	 * 支付成功后存储用户信息
	 * @param request  http://127.0.0.1:8080/mobile/paySuccess?openId=&
	 * @return
	 */
	
	@RequestMapping(value="/mobile/paySuccess",  method = RequestMethod.POST)
	@ResponseBody
	public String paySuccess(@RequestBody String param){
		//查找用户 增加余额
		JSONObject jsStr = JSONObject.fromObject(param); 
		String openId = jsStr.getString("userid");
		Paylist pay = (Paylist)JSONObject.toBean(jsStr, Paylist.class);
		User usr = usrService.find(openId);
		//存储支付订单信息
		int success = payService.insertSelective(pay);
		if(usr != null){
			usr.setBalance(usr.getBalance() + pay.getTotalFee());
			//更新用户余额
			int usrUpdate = usrService.updateByPrimaryKey(usr);
			//查找未付款订单
			Order order = orderService.selectUnPayByUserId(openId);
//			order.setIsPay(1);
			String m_id = order.getmId();
			
			//通知app，netty发送消息
	    	MsgServer2Client.Msg.Builder msgReqbuilder = MsgServer2Client.Msg.newBuilder();
	    	msgReqbuilder.setMsgType(MessageType.MsgType.OPEN);
	    	msgReqbuilder.setCId(order.getcId());
	    	
			//发送消息
	    	ChannelFutureListener channelFutureListener = new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					if(future.isSuccess()) {
						System.out.println("打开充电舱消息发送成功");
						//处理记录等
					
					} else {
						//发送失败 处理
						System.out.println("打开充电舱消息发送失败");
					}
				}
			};
			//发送消息
	    	nettyService.sendMsg(m_id, msgReqbuilder.build(), channelFutureListener);
			//订单消息
	    	Map<String, String> respMap = new HashMap<String, String>();
    		respMap.put("orderId", order.getOrderId());
    		
    		String outTime = order.getOutTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long lt = new Long(outTime);
            Date date = new Date(lt);
            outTime = simpleDateFormat.format(date);
    		respMap.put("outTime", outTime);

			String json = JsonUtil.beanToJson(respMap);
			return json;
		}else{
			return "error";
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
		Order order = orderService.selectByPrimaryKey(orderId);
		int isChange = order.getIsChange();
		String outTime= order.getOutTime();
		int rentHours = CommonUtil.getRentHour(outTime);
		
		if(order.getOrderState() == 0){//进行中的

	    	Map<String, String> map = new HashMap<String, String>();
	    	if(isChange ==1) {
	    		map.put("req", "1");
		    	map.put("msg", "更换");
				String json = JsonUtil.beanToJson(map);
			    return json;
	    	} else {
	    		if(rentHours < 72){
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
					//扣费
					String openId = order.getUserid();
					User usr = usrService.selectByPrimaryKey(openId);
					int balance = usr.getBalance() - 100;
					usr.setBalance(balance);
					int sucess = usrService.updateByPrimaryKey(usr);
			    	map.put("req", "1");
			    	map.put("msg", "更换");
					String json = JsonUtil.beanToJson(map);
				    return json;
				}
	    	}
		}else{
			return null;
		}
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
		order.setOrderState(2);
		order.setIsPay(1);
		order.setcId(mpower.getcId());
		return order;
	}
	
	/**
	 * 获取所有用户订单列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/mobile/getOrders", method = {RequestMethod.GET, RequestMethod.POST},produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAllUser( @RequestParam(value="openId") String openId){
			List<Order> orders = orderService.getOrdersUserId(openId);
			if(orders == null || orders.size() == 0){
				return "error";
			}
	    	Map<String, List> map = new HashMap<String, List>();
			map.put("orderList", orders);
			String json = JsonUtil.beanToJson(map);
			return json;
//		}
	}

	/**
	 * 判断充电宝的情况 循环次数最少的
	 * @param 
	 * @return
	 */
	private M2Power getPower(List<M2Power> powerList){
		List<M2Power> newpower = new ArrayList<M2Power>();
        for (M2Power mpower : powerList) {
            if(mpower.getPower().getpQuantity()> 50){
            	newpower.add(mpower);
            }
        }
		Collections.sort(newpower, new AscPowerComparator());
		M2Power p = newpower.get(0);
		return p;
	}
	
	/**
	 * 判断用户是否可借
	 * @param userId
	 * @return
	 */
	private int analyzeUser(String userId){
		
		int state = -1;
		User usr = usrService.find(userId);

		//不存在用户 
		if(usr == null){
			//数据库添加用户
			User newUsr = new User();
			newUsr.setUserid(userId);
			usrService.insert(newUsr);
			return 0;
		}
		//拉黑用户

		//查询未结束租借订单，缴纳100元
		List<Order> order = orderService.selectUnfinishedByUserId(userId);
		if( order.size() > 0){
			
			return -1;
	
		}else{
			//余额判断
			int balance = usr.getBalance();
			if(balance > 80){
				return 1;//发送消息到机器
			}else{
				return (100 - balance);
			}
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
		int mstate = 0;
		//1. 判断机器是否联网 根据nutty
		Channel channel = NettyChannelMap.getSocketChannel(mId);
		if(channel == null) {
			mstate = -1;
		} else {
			mstate = 1;
		}
		//数据库查询判断机器状态
//		Machine machine = machineService.selectByPrimaryKey(mId);
//		int mstate = 0;
//		if(machine != null) {
//			mstate = machine.getmState();
//			if(mstate == 0){
//				return null;
//			}
//		} else {
//			return null;
//		}

		
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
