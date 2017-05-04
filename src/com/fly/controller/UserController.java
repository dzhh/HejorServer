package com.fly.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fly.model.User;
import com.fly.service.UserService;
import com.fly.util.JsonUtil;

/**
 * @author 作者 fly
 * @version 创建时间：2015年11月17日 下午5:06:53
 * 类说明
 */

@Controller
public class UserController {

	@Autowired
	private UserService userService;

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
	
	
}
