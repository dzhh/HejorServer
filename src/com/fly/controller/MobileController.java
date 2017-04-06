package com.fly.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author fly
 * @version 创建时间：2017年4月6日 类说明
 *
 */
@Controller
public class MobileController {

	/**
	 * 二维码扫描
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/mobile/saomiao",  method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getDetail(HttpServletRequest request){
	    ModelAndView modelAndView = new ModelAndView();  
	    
	    String agent = request.getHeader("User-Agent").toLowerCase();
	    int way = 0;
	    
	    modelAndView.addObject("agent", agent);
	    modelAndView.setViewName("/chongzhi");  
	    return modelAndView;
	}
}
