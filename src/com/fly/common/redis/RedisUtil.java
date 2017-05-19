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

	private static Map<String, Order> map = new HashMap<String, Order>();
	
	public static Order getOrder(String key) {
		return map.get(key);
	}
	
	public static void putOrder(String key, Order order) {
		map.put(key, order);
	}
	
	public static void removeOrder(String key) {
		map.remove(key);
	}
	
}
