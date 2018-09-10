package com.aaa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aaa.dao.StudentDao;
import com.aaa.dao.impl.StudentDaoImpl;
import com.aaa.entity.Student;
/**
 * 添加学生
 * @author sunshaoshan
 *
 */
public class AddStudentServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StudentDao studetnDao = new StudentDaoImpl();
		req.setCharacterEncoding("utf-8");
		Student stu = new Student();
		String name = req.getParameter("name");
		String city = req.getParameter("city");
		stu.setName(name);
		stu.setCity(city);
		System.out.println(req);
		studetnDao.addStudent(stu);
		
		//this.doGet(req,resp);
		req.setAttribute("msg", "保存成功");
		req.getRequestDispatcher("showStudent").forward(req, resp);
	}
	
}
