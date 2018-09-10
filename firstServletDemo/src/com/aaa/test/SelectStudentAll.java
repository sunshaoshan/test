package com.aaa.test;

import java.util.List;

import com.aaa.entity.Student;
import com.aaa.util.BaseDao;

/**
 * 获取所有学生信息
 * @author Administrator
 *
 */
public class SelectStudentAll {
	public static void main(String[] args) {
		String sql = "select * from student";
//		List  list = BaseDao.selectList(sql, null);
//		for (Object obj : list) {
//			System.out.println(obj);
//		}
		List list = BaseDao.selectList(sql, null, Student.class);
		for (Object obj : list) {
			Student stu = (Student)obj;
			System.out.println(stu);
		}
	}

}
