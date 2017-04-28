package com.fly.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	@RequestMapping(value = "user", method = {RequestMethod.GET, RequestMethod.POST}, produces = { "text/javascript;charset=UTF-8" })
	public String loginUser_jsp(HttpServletRequest request) {
		return "/home/login";
	}
	
	@RequestMapping(value = "/user/login", method = {RequestMethod.GET, RequestMethod.POST}, produces = { "text/javascript;charset=UTF-8" })
	public String login_jsp(HttpServletRequest request) {
		return "/login";
	}
	
	@RequestMapping(value = "/user/login_mobile", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String login_mobile(HttpServletRequest request) {
		return "{login:null}";
	}
	
//	@RequestMapping(value = "/user/login.do", method = {RequestMethod.POST}, produces = { "text/javascript;charset=UTF-8" })
//	public ModelAndView login_do(HttpServletRequest request){
//		ModelAndView modelAndView = new ModelAndView();
//		User user = login(request, "web");
//		modelAndView.addObject("user", user);
//		if(user!=null) {
//			modelAndView.addObject("message", "登录成功");
//			afterLoginSucess(modelAndView, "web", request);
//			
//			
//			modelAndView.setViewName("/index");
//		} else {
//			modelAndView.addObject("message", "用户名密码不对");
//			modelAndView.setViewName("/login");
//		}
//	    return modelAndView;
//	}
	
	/**
	 * 登录成功后的处理
	 * @param modelAndView
	 */
	private void afterLoginSucess(ModelAndView modelAndView, String os, HttpServletRequest request) {
	}
	
	
	
//	@RequestMapping(value = "/user/login.json", method = RequestMethod.POST)
//	@ResponseBody
//	public String login_Json(HttpServletRequest request){
//		User user = login(request, "mobile");
//		if(user != null) {
//			String json = JsonUtil.beanToJson(user);
//			return json;
//		} else {
//			return "{login:false}";
//		}
//	}
	
	/**
	 * 登录验证
	 * @param request
	 * @return
	 */
//	private User login(HttpServletRequest request, String os) {
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		User user = userService.selectByUserName(username);
//		
//		//登录成功   设置session
//		if(user!=null && user.getPassword().equals(MD5Util.getMD5String(password))) {
//			if(os.equals("web")) {
//				request.getSession().setAttribute(com.fly.common.web.util.Const.SESSION_USER, user);
//			} else {
//				String key = request.getSession().getId() + DateUtil.getNowDay();
//				SessionUtils.setLoginSessionMapUser(key, user);
////				user.setIosSessionId(key);
//			}
//			
//			//登录次数加1
////			user.setLoginCount(user.getLoginCount()+1);
//			userService.updateByPrimaryKey(user);
//			
//			return user;
//		} else {
//			return null;
//		}
//	}
	
	
	/**
	 * 跳转到注册
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/register", method = {RequestMethod.GET, RequestMethod.POST}, produces = { "text/javascript;charset=UTF-8" })
	public ModelAndView register(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/home/register");
	    return modelAndView;
	}
	
	/**
	 * 注册
	 * @param request
	 * @return
	 */
//	@RequestMapping(value = "/user/submit.do", method = {RequestMethod.GET, RequestMethod.POST}, produces = { "text/javascript;charset=UTF-8" })
//	public ModelAndView submit_do(HttpServletRequest request){
//		ModelAndView modelAndView = new ModelAndView();
//		String username = request.getParameter("username");
//		String password = MD5Util.getMD5String(request.getParameter("password"));
//		String email = request.getParameter("email");
//		int age = Integer.parseInt(request.getParameter("age"));
//		
//		User user = new User();
//		user.setAge(age);
//		user.setEmail(email);
//		user.setUsername(username);
//		user.setPassword(password);
//		user.setRegisterIp(URLHelper.getRemoteHost(request));
//		user.setRegisterTime(DateUtil.getNowDate());
//		
//		User ifuser = userService.selectByUserName(username);
//		if(ifuser == null) {
//			int insert = userService.insert(user);
//			if(insert == 1) {
//				modelAndView.addObject("message", "注册成功");
//				modelAndView.setViewName("/home/sucess");
//			} else {
//				modelAndView.addObject("message", "注册失败");
//				modelAndView.setViewName("/home/register");
//			}
//		} else {
//			modelAndView.addObject("message", "用户名已存在");
//			modelAndView.setViewName("/home/register");
//		}
//	    return modelAndView;
//	}
	
	
	@RequestMapping(value = "/user/logout.do", method = {RequestMethod.GET, RequestMethod.POST}, produces = { "text/javascript;charset=UTF-8" })
	@ResponseBody
	public String logout_do(HttpServletRequest request){
		User user = CmsUtils.getUser(request);
//		request.getSession().removeAttribute(user.getIosSessionId());
		return "/home/index";
	}
	
	@RequestMapping(value = "/user/logout.json", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout_Json(HttpServletRequest request){
		User user = CmsUtils.getUser(request);
//		request.getSession().removeAttribute(user.getIosSessionId());
		return "{logout:sucess}";
	}
	
	
	/**
	 * 获取所有用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/getUser.json", method = {RequestMethod.GET, RequestMethod.POST} )
	@ResponseBody
	public String getUser(HttpServletRequest request){
		User user = userService.find("o5UR3xFIif1N2qtNNc4HHsYxMohg");
		request.setAttribute("user", user);
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
	
	@RequestMapping("/user/getAllUser.json")
	@ResponseBody
	public String getAllUserJson(HttpServletRequest request){
		List<User> findAll = userService.findAll();
		request.setAttribute("userList", findAll);
		String json = JsonUtil.beanListToJson(findAll);
		return json;
	}
	
	
	
}
