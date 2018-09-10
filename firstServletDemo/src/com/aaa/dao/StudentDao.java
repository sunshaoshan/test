package com.aaa.dao;

import java.util.List;

import com.aaa.entity.Student;

/**
 * 学生
 * @author 接口
 *
 */
public interface StudentDao {
	/**
	 * 添加学生
	 */
	public int addStudent(Student stu);
	/**
	 * 查询所有学生
	 */
	public List selectStudentList();
	/**
	 * 更新学生
	 * @param stu
	 * @return
	 */
	public int updateStudent(Student stu);
}
