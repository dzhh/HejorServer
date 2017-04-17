package com.fly.netty.server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class NettyServerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		//初始化netty
		NettyServer nettyServer = new NettyServer();
		try {
			nettyServer.bind();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
