package com.fly.common.web.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.fly.netty.server.NettyServer;
import com.fly.util.WebUtil;

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
		WebUtil.PATH = this.getServletConfig().getServletContext().getRealPath("/");
		
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
