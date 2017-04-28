package com.fly.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fly.model.User;


/**
 * @author 作者 fly
 * @version 创建时间：2015年11月19日 下午6:07:53
 * 类说明
 */
@Controller
public class AdminController {
	
	@RequestMapping(value = "/admin", produces = { "text/javascript;charset=UTF-8" })
	public ModelAndView admin(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin");
	    return modelAndView;
	}
	
	@RequestMapping(value = "/admin/login", produces = { "text/javascript;charset=UTF-8" })
	public ModelAndView submitUser(HttpServletRequest request, User user){
		ModelAndView modelAndView = new ModelAndView();
		String name = user.getUserId();
		String pwd = user.getPassword();
		modelAndView.addObject("name", name);
		modelAndView.addObject("pwd", pwd);
		modelAndView.setViewName("admin/login");
	    return modelAndView;
	}
	
	
	@RequestMapping(value = "/admin/admin", produces = { "text/javascript;charset=UTF-8" })
	public ModelAndView admin(HttpServletRequest request, User user){
		ModelAndView modelAndView = new ModelAndView();
		String a = request.getParameter("username");
		String name = user.getUserId();
		String pwd = user.getPassword();
		modelAndView.addObject("name", name);
		modelAndView.addObject("pwd", pwd);
		modelAndView.setViewName("admin/admin");
	    return modelAndView;
	}
}
