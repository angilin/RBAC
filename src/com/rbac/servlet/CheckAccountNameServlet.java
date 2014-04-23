package com.rbac.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbac.common.BaseServlet;
import com.rbac.service.AccountService;
import com.rbac.util.CommonUtils;

public class CheckAccountNameServlet extends BaseServlet {

	/**
	 * Constructor of the object.
	 */
	public CheckAccountNameServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AccountService accountService = (AccountService) getBean("accountService");
		String username = URLDecoder.decode(request.getParameter("username"),"UTF-8");
		String ignoreId = request.getParameter("ignoreId");
		if(CommonUtils.isNotBlank(username)){
			Boolean isExist = accountService.checkExistAccountName(username, CommonUtils.parseLong(ignoreId));
			response.setContentType("text/html;charset=UTF-8");
			response.setBufferSize(1024);
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			if (isExist) {
				out.print("1");
			} else {
				out.print("0");
			}	
			out.flush();
			out.close();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		
	}

}
