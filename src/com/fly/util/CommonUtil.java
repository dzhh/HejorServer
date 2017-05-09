package com.fly.util;
/**
 * @author 作者 fly
 * @version 创建时间：2016年3月21日 下午9:54:03
 * 类说明
 */
public class CommonUtil {

	public static int getInt(Object obj) {
		if(obj instanceof Integer) {
			return (Integer)obj;
		} else if(obj instanceof String) {
			return Integer.parseInt((String)obj);
		} 
		return -1;
	}
	public static int getRentHour(String outTime){
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
}
