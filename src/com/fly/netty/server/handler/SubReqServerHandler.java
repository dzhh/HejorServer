package com.fly.netty.server.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fly.common.redis.RedisUtil;
import com.fly.model.M2Power;
import com.fly.model.Machine;
import com.fly.model.Order;
import com.fly.model.Power;
import com.fly.model.User;
import com.fly.netty.codec.protobuf.MessageType;
import com.fly.netty.codec.protobuf.MsgClient2Server;
import com.fly.netty.codec.protobuf.MsgClient2Server.Cabin;
import com.fly.netty.codec.protobuf.MsgClient2Server.Msg;
import com.fly.netty.codec.protobuf.MsgServer2Client;
import com.fly.netty.server.NettyChannelMap;
import com.fly.service.M2PowerService;
import com.fly.service.MachineService;
import com.fly.service.OrderService;
import com.fly.service.PowerService;
import com.fly.service.UserService;
import com.fly.util.AscPowerComparator;
import com.fly.util.CommonUtil;
import com.fly.util.SpringContextUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;

/**
 * 具体处理消息
 * @author fly
 *
 */

public class SubReqServerHandler extends SimpleChannelInboundHandler { 

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    	closeConnect(ctx);
    }
    
	@Override
	/**
	 * 接受请求 处理请求
	 */
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println(msg);
		MsgClient2Server.Msg msgReq = (MsgClient2Server.Msg) msg;
		// 消息类型
		MessageType.MsgType msgType =  msgReq.getMsgType();
		
		if(msgType.equals(MessageType.MsgType.AUTH)) {//验证机器
			handleAuthMsg(ctx, msgReq);
		} else if(msgType.equals(MessageType.MsgType.INIT)) {// 存储初始化 init
			
			initMsg(ctx, msgReq);
		} else if(msgType.equals(MessageType.MsgType.OPEN_BACK_OK)) { //打开充电舱成功
			
			openOkMsg(ctx, msgReq);
		} else if(msgType.equals(MessageType.MsgType.OPEN_BACK_ERROR)) { //打开充电舱失败
			
			openErrorMsg(ctx, msgReq);
		} else if(msgType.equals(MessageType.MsgType.RETURN)) { //归还充电宝
			
			returnPowerMsg(ctx, msgReq);
		} else if(msgType.equals(MessageType.MsgType.HEAT)) {
			
		} else if(msgType.equals(MessageType.MsgType.UPDATE)) {//机器自检更新
			//1、更新数据库
			updateMachineMsg(ctx, msgReq);
		} else if(msgType.equals(MessageType.MsgType.ERROR)) {
			
			errorMsg(ctx, msgReq);
			//机器自检报错
		}
		else if(msgType.equals(MessageType.MsgType.CHANGE)){//更换充电宝
			
			changeMsg(ctx, msgReq);
//			changeBackOkMsg(ctx, msgReq);
		} 
		else if(msgType.equals(MessageType.MsgType.CHANGE_OPEN_OK)){//更换充电宝弹出成功
			
			changeBackOkMsg(ctx, msgReq);
		} else if(msgType.equals(MessageType.MsgType.CHANGE_OPEN_ERROR)){//更换充电宝弹出失败
			
			changeBackErrorMsg(ctx, msgReq);
		}
	}
	
	/**
	 * 
	 * @param ctx
	 * @param msgReq
	 */
	private void errorMsg(ChannelHandlerContext ctx, Msg msgReq) {
		//故障处理
	}

	/**
	 * 更新机器 充电宝信息
	 * @param ctx
	 * @param msgReq
	 */
	private void updateMachineMsg(ChannelHandlerContext ctx, Msg msg) {
		//更新机器信息 根据机器m_id在m_power表插入六条数据
		//
		List<Cabin> cabinList = msg.getMachine().getCabinList();
		
//		List<M2Power> m2PowerList = new ArrayList<M2Power>();
		List<Power> powerList = new ArrayList<Power>();
		for(Cabin cabin : cabinList) {
//			M2Power m2Power = new M2Power();
//			m2Power.setmId(msg.getMachine().getMId());
//			m2Power.setcId(cabin.getCId());
//			m2Power.setPowerId(cabin.getPId());
//			m2Power.setpLock(cabin.getPLock());
//			//存储时间
//			m2Power.setUpdateTime(Long.toString(System.currentTimeMillis()));
//			m2PowerList.add(m2Power);
			
			Power power = new Power();
			power.setPowerId(cabin.getPId());
			power.setpQuantity(cabin.getPQuantity());
			power.setpCount(cabin.getPCount());
			powerList.add(power);
		}
		int m_result = 0;
		
		//充电宝power表里面更新数据
		PowerService powerService = SpringContextUtil.getBean("PowerService");
		m_result = powerService.updateByPowerList(powerList);
//		if(m_result == 1) {
			//更新机器信息
//			M2PowerService m2PowerService = SpringContextUtil.getBean("M2PowerService");
//			m_result = m2PowerService.updateRecordByMidAndCid(m2PowerList);
//		}
		
		//回应机器app ok表示存储成功
		//返回 ok
		MsgServer2Client.Msg.Builder builder = MsgServer2Client.Msg.newBuilder();
		if(m_result == 1) {
			builder.setMsgType(MessageType.MsgType.UPDATE_BACK_OK);
			builder.setMsgInfo("update data ok");
		} else {
			builder.setMsgType(MessageType.MsgType.UPDATE_BACK_ERROR);
			builder.setMsgInfo("update data error");
		}
		MsgServer2Client.Msg msgResp = builder.build();
		ctx.writeAndFlush(msgResp);
		
	}

	private void changeBackErrorMsg(ChannelHandlerContext ctx, Msg msgReq) {
		String powerId = msgReq.getPId();
		int cid = msgReq.getCId();
		//取出订单
		Order order = RedisUtil.getOrder(powerId);
		//4 代表订单失败
		order.setOrderState(4);
		
		//更新订单 order
		OrderService orderService = SpringContextUtil.getBean("OrderService");
		orderService.updateByPrimaryKey(order);
		
		//微信发消息


		//给app发送消息
		MsgServer2Client.Msg.Builder builder = MsgServer2Client.Msg.newBuilder();
		builder.setMsgType(MessageType.MsgType.CHANGE_BACK_ERROR);
		builder.setMsgInfo("change back error");
		MsgServer2Client.Msg msgResp = builder.build();
		ctx.writeAndFlush(msgResp);
		//清理 order缓存 
		RedisUtil.removeOrder(powerId);
	}

	/**
	 * 
	 * @param ctx
	 * @param msgReq
	 */
	private void changeBackOkMsg(ChannelHandlerContext ctx, Msg msgReq) {
		String powerId = msgReq.getPId();
		int cid = msgReq.getCId();
		//取出订单
		Order order = RedisUtil.getOrder(powerId);
		//订单成功  已开始
		order.setOrderState(0);
		//弹出时间
		order.setOutTime(Long.toString(System.currentTimeMillis()));
		//更新订单 order
		OrderService orderService = SpringContextUtil.getBean("OrderService");
		orderService.updateByPrimaryKey(order);
		
		//更新机器充电宝关系表mpower
		M2PowerService m2PowerService = SpringContextUtil.getBean("M2PowerService");
		m2PowerService.updateByPowerId(order.getPowerId());
		
		//微信发消息
		
		
		
		//给app发送消息
		MsgServer2Client.Msg.Builder builder = MsgServer2Client.Msg.newBuilder();
		builder.setMsgType(MessageType.MsgType.CHANGE_BACK_OK);
		builder.setMsgInfo("change_back_ok sucess");
		MsgServer2Client.Msg msgResp = builder.build();
		ctx.writeAndFlush(msgResp);
		//清理 order缓存 
		RedisUtil.removeOrder(powerId);
	}

	/**
	 * 弹出失败
	 * @param ctx
	 * @param msgReq
	 */
	private void openErrorMsg(ChannelHandlerContext ctx, Msg msgReq) {
		String powerId = msgReq.getPId();
		int cid = msgReq.getCId();
		//取出订单
		Order order = RedisUtil.getOrder(powerId);
		//4 代表订单失败
		order.setOrderState(4);
		
		//更新订单 order
		OrderService orderService = SpringContextUtil.getBean("OrderService");
		orderService.updateByPrimaryKey(order);
		
		//微信发消息


		//给app发送消息
//		MsgServer2Client.Msg.Builder builder = MsgServer2Client.Msg.newBuilder();
//		builder.setMsgType(MessageType.MsgType.open_error);
//		builder.setMsgInfo("open error");
//		MsgServer2Client.Msg msgResp = builder.build();
//		ctx.writeAndFlush(msgResp);
		//清理 order缓存 
		RedisUtil.removeOrder(powerId);
	}

	/**
	 * 弹出成功
	 * @param ctx
	 * @param msgReq
	 */
	private void openOkMsg(ChannelHandlerContext ctx, Msg msgReq) {
		String powerId = msgReq.getPId();
		int cid = msgReq.getCId();
		//取出订单
		Order order = RedisUtil.getOrder(powerId);
		//订单成功  已开始
		order.setOrderState(0);
		//弹出时间
		order.setOutTime(Long.toString(System.currentTimeMillis()));
		//更新订单 order
		OrderService orderService = SpringContextUtil.getBean("OrderService");
		orderService.updateByPrimaryKey(order);
		
		//更新机器充电宝关系表mpower
		M2PowerService m2PowerService = SpringContextUtil.getBean("M2PowerService");
		m2PowerService.updateByPowerId(order.getPowerId());
		
		//微信发消息
		
		
		
		//给app发送消息
//		MsgServer2Client.Msg.Builder builder = MsgServer2Client.Msg.newBuilder();
//		builder.setMsgType(MessageType.MsgType.open_ok);
//		builder.setMsgInfo("open sucess");
//		MsgServer2Client.Msg msgResp = builder.build();
//		ctx.writeAndFlush(msgResp);
		
		//清理 order缓存 
		RedisUtil.removeOrder(powerId);
	}

	private void changeMsg(ChannelHandlerContext ctx, Msg msgReq) {
		MsgServer2Client.Msg.Builder builder = MsgServer2Client.Msg.newBuilder();
		//根据读取redis缓存，根据powerId查找orderId
		String powerId = msgReq.getMachine().getCabin(0).getPId();
		int cid = msgReq.getMachine().getCabin(0).getCId();
		Order order = RedisUtil.getChangeOrder(powerId);
		
		//无缓存key，通知机器弹出
		if(order == null) {
			builder.setMsgType(MessageType.MsgType.CHANGE_BACK_ERROR);
			builder.setCId(cid);
			builder.setMsgInfo("change error");
			//向微信发更换失败消息
			
			
		} else {
			//有缓存，获取弹出的充电宝
			M2PowerService m2PowerService = SpringContextUtil.getBean("M2PowerService");
		    List<M2Power> powerList = m2PowerService.selectByM_Id(order.getmId());
		    M2Power newMpower = getPower(powerList);
		    
			//app回应开仓后生成新的订单，新orderId，新的充电宝，userId，借出时间
	    	Order newOrder = addOrderForUser(order.getUserid(), newMpower);
	    	newOrder.setIsPay(1);
	    	
			OrderService orderService = SpringContextUtil.getBean("OrderService");
	    	orderService.insert(newOrder);
	    	//更新之前订单
	    	order.setOrderState(1);
	    	orderService.updateByPrimaryKey(order);
		    //netty通知机器通知app充电舱编号
//	    	MsgServer2Client.Msg.Builder msgReqbuilder = MsgServer2Client.Msg.newBuilder();
	    	builder.setMsgType(MessageType.MsgType.CHANGE_OPEN);
	    	builder.setCId(newMpower.getcId());
			RedisUtil.putOrder(newOrder.getPowerId(), newOrder);

		}
		
		MsgServer2Client.Msg msgResp = builder.build();
		ctx.writeAndFlush(msgResp);
		//删除缓存
		RedisUtil.removeChangeOrder(powerId);
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
		order.setIsChange(1);
		order.setTotalFee(0);
		order.setOrderState(2);
		order.setIsPay(0);
		order.setcId(Integer.toString(mpower.getcId()));
		return order;
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
	
	private void returnPowerMsg(ChannelHandlerContext ctx, Msg msgReq) {
		//验证订单时间 72小时，一年后无法归还
		int cId = msgReq.getMachine().getCabin(0).getCId();
		String powerId = msgReq.getMachine().getCabin(0).getPId();
		int pCount = msgReq.getMachine().getCabin(0).getPCount();
		int pQuantity = msgReq.getMachine().getCabin(0).getPQuantity();
		String m_id = msgReq.getMachine().getMId();
		
		OrderService orderService = SpringContextUtil.getBean("OrderService");
		Order order = orderService.selectByPowerId(powerId);
		String outTime = order.getOutTime();
		int rentHours = CommonUtil.getRentHour(outTime);
		MsgServer2Client.Msg.Builder builder = MsgServer2Client.Msg.newBuilder();
		
		if(rentHours > 72 || order.getIsChange() ==1) {
			//不能归还只能更换，向机器发送弹出消息弹出cId
			builder.setMsgType(MessageType.MsgType.RETURN_BACK_ERROR);
			builder.setMsgInfo("return power fail");//机器弹出充电宝
			//给微信发消息
			
		}else{
			//1、能归还：更新订单，
			order.setOrderState(1);
			order.setTotalFee(rentHours);
			orderService.updateByPrimaryKey(order);
			
			//机器关系表，更新信息，填充充电宝，
			M2PowerService m2PowerService = SpringContextUtil.getBean("M2PowerService");
			PowerService powerService = SpringContextUtil.getBean("PowerService");
			M2Power mPower = new M2Power();
			mPower.setcId(cId);
			mPower.setmId(m_id);
			mPower.setIsempty(0);
			mPower.setUpdateTime(Long.toString(System.currentTimeMillis()));
			mPower.setpLock(1);
			mPower.setPowerId(powerId);
			int s = m2PowerService.updateByM_IdAndC_id(mPower);
			
			Power power = new Power();
			power.setIsBack(1);
			power.setpCount(pCount);
			power.setpQuantity(pQuantity);
			power.setPowerId(powerId);
			int ps = powerService.updateByPrimaryKey(power);
			//根据powerId跟新充电宝电量
//			mPower.setIsempty(1);
//			mPower.setPowerId(powerId);
//			mPower.getPower().setpCount(pCount);
//			mPower.getPower().setpQuantity(pQuantity);
			
			//更新
//			int s = m2PowerService.updateByM_IdAndC_id(mPower);
			//mpower  关系表更新成功 
			if(s == 1 || ps ==1) {
				//根据openId微信发送消息(归还成功消息)
				String openId = order.getUserid();
				String orderId = order.getOrderId();
				//更新用余额 rentHours * 1 = RMB
				UserService userService = SpringContextUtil.getBean("UserService");
				User usr = userService.find(openId);
				int balance = usr.getBalance();
				usr.setBalance(balance - rentHours);
				int sucess = userService.updateByPrimaryKey(usr);
				if(sucess == 1) {
					//调用微信模板消息接口
					
					//回应app "ok"
					builder.setMsgType(MessageType.MsgType.RETURN_BACK_OK);
					builder.setMsgInfo("return power sucess");
				} else {
					//回滚
					mPower.setIsempty(0);
					m2PowerService.updateByPowerId(powerId);
					//更新失败 弹出充电宝 发送失败消息
					builder.setMsgType(MessageType.MsgType.RETURN_BACK_ERROR);
					builder.setCId(cId);
					builder.setMsgInfo("return power error");//机器弹出充电宝
				}
			} else {
				//更新失败 弹出充电宝 发送失败消息
				builder.setMsgType(MessageType.MsgType.RETURN_BACK_ERROR);
				builder.setMsgInfo("lock error");//机器弹出充电宝
				
				//调用微信模板消息接口
				
			}
			
		}	
		MsgServer2Client.Msg msgResp = builder.build();
		ctx.writeAndFlush(msgResp);
	}

	/**
	 * 处理mid类型消息
	 * 验证机器是否存在
	 * 如果存在  存储长连接
	 * @param ctx
	 * @param msgReq
	 */
	private void handleAuthMsg(ChannelHandlerContext ctx, MsgClient2Server.Msg msgReq) {
		String mId = msgReq.getMachine().getMId();
		
		//验证过程   也可以在缓存中
		MachineService machineService = SpringContextUtil.getBean("MachineService");
		Machine machine = machineService.selectByPrimaryKey(mId);
		if(machine != null) {
			machine.setM4g(msgReq.getMachine().getMobile());
			machine.setmWifi(msgReq.getMachine().getWifi());
			machine.setmState(1);
			try {
				//返回 ok
				MsgServer2Client.Msg.Builder builder = MsgServer2Client.Msg.newBuilder();
				builder.setMsgType(MessageType.MsgType.AUTH_BACK_OK);
				builder.setMsgInfo("认证成功");
				builder.setConfingUrl("");
				MsgServer2Client.Msg msgResp = builder.build();
				ctx.writeAndFlush(msgResp);
				//存储连接  存储机器状态   这个地方需要考虑下
				NettyChannelMap.add(msgReq.getSessionID(), (SocketChannel)ctx.channel());
				machineService.updateByPrimaryKey(machine);
				System.out.println("机器认证成功");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			//返回 error
			MsgServer2Client.Msg.Builder builder = MsgServer2Client.Msg.newBuilder();
			builder.setMsgType(MessageType.MsgType.AUTH_BACK_ERROR);
			builder.setMsgInfo("认证失败");
			MsgServer2Client.Msg msgResp = builder.build();
			ctx.writeAndFlush(msgResp);
			System.out.println("机器认证失败");
		}
	}
	
	
	/**
	 * 初始化信息
	 * @param ctx
	 * @param msgReq
	 * @throws Exception
	 */
	private void initMsg(ChannelHandlerContext ctx, MsgClient2Server.Msg msg) throws Exception {
		//更新机器信息 根据机器m_id在m_power表插入六条数据
		//
		List<Cabin> cabinList = msg.getMachine().getCabinList();
		
		List<M2Power> m2PowerList = new ArrayList<M2Power>();
		List<Power> powerList = new ArrayList<Power>();
		for(Cabin cabin : cabinList) {
			M2Power m2Power = new M2Power();
			m2Power.setmId(msg.getMachine().getMId());
			m2Power.setcId(cabin.getCId());
			m2Power.setPowerId(cabin.getPId());
			m2Power.setpLock(cabin.getPLock());
			m2Power.setIsempty(0);
			//存储时间
			m2Power.setUpdateTime(Long.toString(System.currentTimeMillis()));
			m2PowerList.add(m2Power);
			
			Power power = new Power();
			power.setPowerId(cabin.getPId());
			power.setpQuantity(cabin.getPQuantity());
			power.setpCount(cabin.getPCount());
			powerList.add(power);
		}
		int m_result = 0;
		
		//充电宝power表里面更新数据
		PowerService powerService = SpringContextUtil.getBean("PowerService");
		m_result = powerService.insertByPowerList(powerList);
		if(m_result != 0) {
			//更新机器信息
			M2PowerService m2PowerService = SpringContextUtil.getBean("M2PowerService");
			m_result = m2PowerService.updateRecordByMidAndCid(m2PowerList);
		}
		
		//回应机器app ok表示存储成功
		//返回 ok
		MsgServer2Client.Msg.Builder builder = MsgServer2Client.Msg.newBuilder();
		if(m_result != 0) {
			builder.setMsgType(MessageType.MsgType.INIT_BACK_OK);
			builder.setMsgInfo("init data success");
		} else {
			builder.setMsgType(MessageType.MsgType.INIT_BACK_ERROR);
			builder.setMsgInfo("init data fail");
		}
		MsgServer2Client.Msg msgResp = builder.build();
		ctx.writeAndFlush(msgResp);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		System.out.println("exceptionCaught " + cause.getMessage());
		closeConnect(ctx);
	}
	
	/**
     * 关闭连接
     * 清除数据
     * @param ctx
     */
    public void closeConnect(ChannelHandlerContext ctx) {
    	//channel失效，从Map中移除
    	String clientId = NettyChannelMap.getClientId((SocketChannel)ctx.channel());
    	if(clientId != null) {
    		NettyChannelMap.remove(clientId);
            //关闭连接
        	ctx.close();
    		System.out.println("close clientId = " + clientId + " conn = " + ctx.channel().toString());
    	}
    }
	
	
}
