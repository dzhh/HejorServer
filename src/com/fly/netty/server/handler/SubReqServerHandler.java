package com.fly.netty.server.handler;

import java.util.ArrayList;
import java.util.List;

import com.fly.common.redis.RedisUtil;
import com.fly.model.M2Power;
import com.fly.model.Machine;
import com.fly.model.Order;
import com.fly.model.User;
import com.fly.netty.codec.protobuf.MsgClient2Server;
import com.fly.netty.codec.protobuf.MsgClient2Server.Cabin;
import com.fly.netty.codec.protobuf.MsgClient2Server.Msg;
import com.fly.netty.codec.protobuf.MsgServer2Client;
import com.fly.netty.server.NettyChannelMap;
import com.fly.service.M2PowerService;
import com.fly.service.MachineService;
import com.fly.service.OrderService;
import com.fly.service.UserService;
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
		MsgClient2Server.MsgType msgType =  msgReq.getMsgType();
		
		//存储连接  存储机器状态   这个地方需要考虑下
		NettyChannelMap.add(msgReq.getSessionID(), (SocketChannel)ctx.channel());
		
		if(msgType.equals(MsgClient2Server.MsgType.mid)) {//验证机器
			handleMidMsg(ctx, msgReq);
		} else if(msgType.equals(MsgClient2Server.MsgType.init)) {// 初始化 init
			initMsg(ctx, msgReq);
		} else if(msgType.equals(MsgClient2Server.MsgType.open)) {
			
		} else if(msgType.equals(MsgClient2Server.MsgType.lock)) {
			lockMsg(ctx, msgReq);
					
			
		} else if(msgType.equals(MsgClient2Server.MsgType.heat)) {
			
		} else if(msgType.equals(MsgClient2Server.MsgType.update)) {//机器自检更新
			//1、更新数据库
			
			
		} else if(msgType.equals(MsgClient2Server.MsgType.error)) {
			
			//机器自检报错
		}
		else if(msgType.equals(MsgClient2Server.MsgType.change)){//更换充电宝
			changeMsg(ctx, msgReq);
			
		}
	}
	
	private void changeMsg(ChannelHandlerContext ctx, Msg msgReq) {
		MsgServer2Client.Msg.Builder builder = MsgServer2Client.Msg.newBuilder();
		//根据读取redis缓存，根据powerId查找orderId
		String orderId = msgReq.getMachine().getCabin(0).getCId();
		Order order = RedisUtil.getOrder(orderId);
		
		//无缓存key，通知机器弹出
		if(order == null) {
			
		} else {
		//有缓存，获取弹出的充电宝
		
			
		}
		//通知app充电舱编号
		
		//app回应开仓后生成新的订单，新orderId，新的充电宝，userId，借出时间
		
		//删除缓存
		RedisUtil.removeOrder(orderId);
		//通知微信
	}

	private void lockMsg(ChannelHandlerContext ctx, Msg msgReq) {
		//验证订单时间 72小时，一年后无法归还
		String cId = msgReq.getMachine().getCabin(0).getCId();
		String powerId = msgReq.getMachine().getCabin(0).getPId();
		int pCount = msgReq.getMachine().getCabin(0).getPCount();
		int pQuantity = msgReq.getMachine().getCabin(0).getPQuantity();

		OrderService orderService = SpringContextUtil.getBean("OrderServiceImpl");
		Order order =  orderService.selectByPowerId(powerId);
		String outTime = order.getOutTime();
		int rentHours = CommonUtil.getRentHour(outTime);
		if(rentHours > 72){
			
			//不能归还只能更换，向机器发送弹出消息弹出cId
		
		}else{
			//1、能归还：更新订单，
			order.setOrderState(1);
			orderService.updateByPrimaryKey(order);
			
			//机器关系表，更新信息，填充充电宝，
			M2PowerService m2PowerService = SpringContextUtil.getBean("M2PowerServiceImpl");
			String m_id = msgReq.getMachine().getMId();

			M2Power mPower = m2PowerService.selectByM_IdAndC_Id(Integer.parseInt(cId), m_id);
			//根据powerId跟新充电宝电量
			mPower.setIsempty(0);
			mPower.setPowerId(powerId);
			mPower.getPower().setpCount(pCount);
			mPower.getPower().setpQuantity(pQuantity);
			
			//更新
			int s = m2PowerService.updateByPrimaryKey(mPower);
			//根据openId微信发送消息(归还成功消息)
			String openId = order.getUserid();
			String orderId = order.getOrderId();

			//更新用余额 rentHours * 1 = RMB
			UserService userService = SpringContextUtil.getBean("userServiceImpl");
			User usr = userService.selectByPrimaryKey(openId);
			int balance = usr.getBalance() - rentHours;
			usr.setBalance(balance);
			int sucess = userService.updateByPrimaryKey(usr);
			if(s>0 && sucess>0){
				//调用微信模板消息接口
				
				//回应app "ok"
			}else{
				//更新失败 弹出充电宝 发送失败消息
				
			}
			
		}	
	}

	/**
	 * 处理mid类型消息
	 * 验证机器是否存在
	 * 		如果存在  存储长连接
	 * @param ctx
	 * @param msgReq
	 */
	private void handleMidMsg(ChannelHandlerContext ctx, MsgClient2Server.Msg msgReq) {
		String mId = msgReq.getMachine().getMId();
		//验证过程   也可以在缓存中
		MachineService machineService = SpringContextUtil.getBean("machineServiceImpl");
		Machine machine = machineService.selectByPrimaryKey(mId);
		if(machine != null) {
			try {
				
				//返回 ok
				MsgServer2Client.Msg.Builder builder = MsgServer2Client.Msg.newBuilder();
				builder.setMsgType(MsgServer2Client.MsgType.resp);
				builder.setMsgInfo("midSucess");
				MsgServer2Client.Msg msgResp = builder.build();
				ctx.writeAndFlush(msgResp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			//返回 error
			MsgServer2Client.Msg.Builder builder = MsgServer2Client.Msg.newBuilder();
			builder.setMsgType(MsgServer2Client.MsgType.resp);
			builder.setMsgInfo("midError");
			MsgServer2Client.Msg msgResp = builder.build();
			ctx.writeAndFlush(msgResp);
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
		M2PowerService m2PowerService = SpringContextUtil.getBean("M2PowerService");
		List<M2Power> recordList = new ArrayList<M2Power>();
		List<Cabin> cabinList = msg.getMachine().getCabinList();
		for(Cabin cabin : cabinList) {
			M2Power record = new M2Power();
			record.setmId(msg.getMachine().getMId());
//			record.setcId(cId);
			record.setPowerId(cabin.getCId());
			m2PowerService.insert(record);
		}
		
		
		//充电宝power表里面插入五条数据(初始化时一个机器五个电池)
		
		//回应机器app ok表示存储成功
		//返回 ok
		MsgServer2Client.Msg.Builder builder = MsgServer2Client.Msg.newBuilder();
		builder.setMsgType(MsgServer2Client.MsgType.resp);
		builder.setMsgInfo("ok");
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
