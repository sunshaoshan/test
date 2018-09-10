package com.aaa.entity;

import java.util.Date;

/**
 * 学生实体类
 * @author sunshaoshan
 *
 */
public class Student {
	/**
	 * 学生id
	 */
	private Integer id;
	
	/**
	 * 学生姓名
	 */
	private String name;
	/**
	 * 学生地址
	 */
	private String city;
	
	
	/**
	 * 性别
	 */
	private String gender;
	
	/**
	 * 年龄
	 */
	private Integer age;
	
	/**
	 * 外键  班级id
	 * 
	 */
	private Integer classid;
	
	/**
	 * 生日
	 */
	private Date brithday;

	public Student() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getClassid() {
		return classid;
	}

	public void setClassid(Integer classid) {
		this.classid = classid;
	}

	public Date getBrithday() {
		return brithday;
	}

	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", city=" + city
				+ ", gender=" + gender + ", age=" + age + ", classid="
				+ classid + ", brithday=" + brithday + "]";
	}
	
	
}
