package com.fly.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fly.common.web.util.Const;
import com.fly.common.web.util.SessionUtils;
import com.fly.model.User;

/**
 * @author 作者 fly //weixin
 * @version 创建时间：2015年11月18日 下午2:11:42 类说明
 */
@Controller
public class HomeController {

	@RequestMapping("/home/index")
	public ModelAndView homeIndex(HttpServletRequest request) {
		ModelAndView modelAndView = null;
		User user = (User) request.getSession().getAttribute(Const.SESSION_USER);

		if(user != null ) {
			modelAndView = (ModelAndView) request.getAttribute("modelAndView");
			modelAndView.setViewName("/index");
			return modelAndView;
		}
		modelAndView = new ModelAndView();
		modelAndView.setViewName("/login");
		return modelAndView;
	}

	@RequestMapping(value="/home/detail/{id}", method = {RequestMethod.GET})
	public ModelAndView getDetail(@PathVariable(value="id") Integer id){
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("id", id);
	    modelAndView.setViewName("/home/detail");
	    return modelAndView;
	}

}
