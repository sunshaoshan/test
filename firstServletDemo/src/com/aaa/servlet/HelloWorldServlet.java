package com.aaa.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {
	/**
	 * 初始化
	 */
	@Override
	public void init() throws ServletException {
		System.out.println("出生了");
	}
	/**
	 * HttpServletRequest 服务器端   从客户端请求中 获取参数
	 * 
	 * HttpServletResponse 从服务器端 返回响应
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置输出格式  html 编码格式utf-8
		resp.setContentType("text/html;charset=utf-8");
		//输出一个格式化的信息
		PrintWriter out = resp.getWriter();
		//服务器对客户端响应
		out.print("<html>");
		out.print("<head>");
		out.print("<title>这是一个服务器对客户端的响应</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("爱老虎油  tomcat1");
		out.print("</body>");
		out.print("</html>");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	/**
	 * 销毁servlet对象
	 */
	@Override
	public void destroy() {
		System.out.println("十八年后又是一条好汉");
	}

	
	
	
}
