package com.aaa.dao.impl;

import java.util.List;

import com.aaa.dao.StudentDao;
import com.aaa.entity.Student;
import com.aaa.util.BaseDao;
/**
 * 学生实现类
 * @author sunshaoshan
 *
 */
public class StudentDaoImpl implements StudentDao {
	/**
	 * 添加学生信息
	 */
	public int addStudent(Student stu) {
		String sql = "insert into student(name,city,gender) values(?,?,?)";
		Object[] params = {stu.getName(),stu.getCity(),stu.getGender()};
		int  row = BaseDao.addAndUpdateAndDelete(sql, params);
		return row;
	}

	public List selectStudentList() {
		String sql = "select * from student";
		List list =BaseDao.selectList(sql, null);
		return list;
	}

	public int updateStudent(Student stu) {
		
		return 0;
	}

}
