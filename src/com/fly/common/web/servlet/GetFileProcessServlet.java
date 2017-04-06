package com.fly.common.web.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 文件上传进度获取Servlet
 * 
 * @author chengqi
 *
 */
public class GetFileProcessServlet extends HttpServlet {



	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileUploadPercent = (String)request.getSession().getAttribute("fileUploadProcess");
		Writer writer = null;
		try {
			writer = response.getWriter();
			IOUtils.write(fileUploadPercent == null ? "0%" : fileUploadPercent, writer);
			writer.flush();
			writer.close();
		} catch(Exception e) {
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}

}
