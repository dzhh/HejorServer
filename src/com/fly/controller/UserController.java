package com.fly.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fly.common.web.util.CmsUtils;
import com.fly.common.web.util.SessionUtils;
import com.fly.common.web.util.URLHelper;
import com.fly.model.User;
import com.fly.service.UserService;
import com.fly.util.DateUtil;
import com.fly.util.JsonUtil;
import com.fly.util.MD5Util;

/**
 * @author 作者 fly
 * @version 创建时间：2015年11月17日 下午5:06:53
 * 类说明
 */

@Controller
public class UserController {

	@Autowired
	private UserService userService;
//	
//	@RequestMapping(value = "user", method = {RequestMethod.GET, RequestMethod.POST}, produces = { "text/javascript;charset=UTF-8" })
//	public String loginUser_jsp(HttpServletRequest request) {
//		return "/home/login";
//	}
//	
//	@RequestMapping(value = "/user/login", method = {RequestMethod.GET, RequestMethod.POST}, produces = { "text/javascript;charset=UTF-8" })
//	public String login_jsp(HttpServletRequest request) {
//		return "/login";
//	}
//	
//	@RequestMapping(value = "/user/login_mobile", method = {RequestMethod.GET, RequestMethod.POST})
//	@ResponseBody
//	public String login_mobile(HttpServletRequest request) {
//		return "{login:null}";
//	}

	/**
	 * 获取用户列表 参数userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/{userId}", method = {RequestMethod.GET, RequestMethod.POST} ,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getUser(@PathVariable("userId") String userId){
		User user = userService.find(userId);
		String json = JsonUtil.beanToJson(user);
		return json;
	}
	
	/**
	 * 获取所有用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/user/getAllUser.do")
	public String getAllUser(HttpServletRequest request){
		List<User> findAll = userService.findAll();
		request.setAttribute("userList", findAll);
		return "/home/bangding";
	}
//	
//	@RequestMapping("/user/getAllUser.json")
//	@ResponseBody
//	public String getAllUserJson(HttpServletRequest request){
//		List<User> findAll = userService.findAll();
//		request.setAttribute("userList", findAll);
//		String json = JsonUtil.beanListToJson(findAll);
//		return json;
//	}
	
	
	
}
