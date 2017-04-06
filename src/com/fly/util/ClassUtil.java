package com.fly.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 作者 fly
 * @version 创建时间：2016年2月10日 下午8:05:17
 * 类说明
 */
public class ClassUtil {

	public static Map<String, String> classMap;
	
	static {
		classMap = new HashMap<String, String>();
		classMap.put("AdminController", "com.fly.controller.AdminController");
		classMap.put("BaseController", "com.fly.controller.BaseController");
		classMap.put("CodeController", "com.fly.controller.CodeController");
		classMap.put("HomeController", "com.fly.controller.HomeController");
		classMap.put("KechengController", "com.fly.controller.KechengController");
		classMap.put("LessonController", "com.fly.controller.LessonController");
		classMap.put("MenuController", "com.fly.controller.MenuController");
		classMap.put("SystemController", "com.fly.controller.SystemController");
		classMap.put("UserController", "com.fly.controller.UserController");
	}
}
