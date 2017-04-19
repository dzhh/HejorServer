package com.fly.netty.server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 初始化servlet nettyServer
 * @author fly
 *
 */
public class NettyServerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		String port = config.getInitParameter("port");

		//初始化netty
		NettyServer nettyServer = new NettyServer();
		try {
			nettyServer.bind("0.0.0.0", Integer.parseInt(port));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
