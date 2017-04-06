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
}
