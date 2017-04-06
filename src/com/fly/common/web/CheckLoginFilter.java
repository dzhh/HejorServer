package com.fly.common.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.fly.common.web.util.CmsUtils;
import com.fly.common.web.util.Const;
import com.fly.common.web.util.SessionUtils;
import com.fly.model.User;

/**
 * @author 作者 fly
 * @version 创建时间：2016年1月16日 上午11:32:50
 * 类说明
 */
public class CheckLoginFilter implements Filter {
	
	private static Map<String, String> loginMapUrl = new HashMap<String, String>();
	
	static{
		loginMapUrl.put("/user/login.do", "1");
		loginMapUrl.put("/user/login.json", "1");
		loginMapUrl.put("/user/submit.do", "1");
		loginMapUrl.put("/user/submit.json", "1");
		loginMapUrl.put("/kecehng/getBykechengId.json", "1");
		loginMapUrl.put("/kecheng/getAll.json", "1");
		loginMapUrl.put("/explorer/videoRemoteService", "1");
	}
	
	@Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        
        // 获得用户请求的URI
        String path = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = path.substring(contextPath.length());
        
        //放过登录执行的操作
        if(loginMapUrl.get(url) != null) {
        	filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        
        User user = null;
        
        //移动端
        if(url.endsWith("json")) {
        	user = SessionUtils.getUser(request, Const.MOBILE);
        	filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else {
        	//Web
        	user = SessionUtils.getUser(request, Const.WEB);
        }
        
        //判断是否登录
        if(user == null) {
        	String token = request.getParameter("iosSessionId");
        	//移动端
        	if(token!=null && !"".equals(token)) {
        		response.sendRedirect(contextPath+"/user/login_mobile");
        	} else {
        		response.sendRedirect(contextPath+"/user/login");
        	}
        	
        	return;
        }
        
        if (user.getUserId()!=null && user.getPassword()!=null) {
        	//统一处理  将需要常用的变量放入到ModelAndView
        	CmsUtils.setUser(request, user);
        	ModelAndView modelAndView = new ModelAndView();
        	modelAndView.addObject("user", user);
        	request.setAttribute("modelAndView", modelAndView);
        	
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
    }

    
   
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }
}
