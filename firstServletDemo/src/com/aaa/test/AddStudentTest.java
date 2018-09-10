package com.aaa.test;

import com.aaa.dao.StudentDao;
import com.aaa.dao.impl.StudentDaoImpl;
import com.aaa.entity.Student;

public class AddStudentTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Student stu = new Student();
		stu.setName("战三");
		stu.setCity("山寨");
		stu.setGender("女");
		
		//调用学生类接口
		
		StudentDao studentDao = new StudentDaoImpl();
		int row = studentDao.addStudent(stu);
		
		System.out.println(row);
	}

}
