package com.fly.common.redis;

import java.util.HashMap;
import java.util.Map;

import com.fly.model.Order;

/**
 * 临时采用内存
 * @author fly
 *
 */
public class RedisUtil {

	/**
	 * 新订单
	 * key powerId
	 * value Order
	 */
	private static Map<String, Order> orderMap = new HashMap<String, Order>();

	public static Order getOrder(String key) {
		return orderMap.get(key);
	}
	
	public static void putOrder(String key, Order order) {
		orderMap.put(key, order);
	}
	
	public static void removeOrder(String key) {
		orderMap.remove(key);
	}
	
	/**
	 * 更换订单
	 * key powerId
	 * value Order
	 */
	private static Map<String, Order> changeOrderMap = new HashMap<String, Order>();
	
	public static Order getChangeOrder(String key) {
		return changeOrderMap.get(key);
	}
	
	public static void putChangeOrder(String key, Order order) {
		changeOrderMap.put(key, order);
	}
	
	public static void removeChangeOrder(String key) {
		changeOrderMap.remove(key);
	}
	
}
