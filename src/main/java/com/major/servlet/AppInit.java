package com.major.servlet;
 

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.major.util.DBUtil;

/**
 * Servlet implementation class AppInit
 */
@WebServlet("/AppInit")
public class AppInit extends HttpServlet { 
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(AppInit.class);
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {  
		log.info("Starting..... project-major-web");  
		DBUtil.init();
	}

}
