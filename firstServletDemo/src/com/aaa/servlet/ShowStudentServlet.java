package com.aaa.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aaa.dao.StudentDao;
import com.aaa.dao.impl.StudentDaoImpl;

public class ShowStudentServlet extends HttpServlet {

	@SuppressWarnings("rawtypes")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置响应方式和编码格式
		resp.setContentType("text/html;charset=utf-8");
		//获取格式化输出
		PrintWriter out = resp.getWriter();
		//调用查询方法 获取数据
		StudentDao studetnDao = new StudentDaoImpl();
		List lis = studetnDao.selectStudentList();
		String msg = (String)req.getAttribute("msg");
		//返回给页面html代码
		out.print(msg);
		out.print("<table>");
		for (Object object : lis) {
			out.print("<tr>");
				List zlist = (List)object;
				for (Object obj : zlist) {
					out.print("<td>"+obj+"</td>");
				}
			out.print("</tr>");
		}
		out.print("</table>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
		

	}
	
}
