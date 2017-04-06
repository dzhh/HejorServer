package com.fly.common.web.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.fly.model.User;

/**
 * @author 作者 fly
 * @version 创建时间：2016年1月16日 上午11:02:51
 * 类说明
 */
public class SessionUtils {

	public static Map<String, User> loginSessionMap = new HashMap<String, User>();

	public static void setLoginSessionMapUser(String key, User user) {
		loginSessionMap.put(key, user);
	}
	
	public static User getLoginSessionMapUser(String key) {
		User user = loginSessionMap.get(key);
		return user;
	}
	
	public static User getUser(HttpServletRequest request, String os) {
		User user = null;
		if(os.equals(Const.WEB)) {
			user = (User) request.getSession().getAttribute(Const.SESSION_USER);
		} else if(os.equals(Const.MOBILE)) {
			String key = request.getParameter(Const.MOBILE_KEY);
			user = loginSessionMap.get(key);
		}
		
		return user;
	}
	
	public static void removeUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String sessonid = session.getId();
		if(sessonid != null) {
			session.removeAttribute(sessonid);
		}
		
		String key = request.getParameter("iosSessionId");
		if(key != null) {
			session.removeAttribute(key);
		}
		
	}
	
}
