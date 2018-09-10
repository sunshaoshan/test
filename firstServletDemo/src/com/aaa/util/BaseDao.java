package com.aaa.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 连接数据库  增删改查
 * @author susnhaoshan 2017-11-25 15:30 2017-11-28 15:30
 *
 */
public class BaseDao {
	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mytestdb?user=root&password=sunshaoshan&useUnicode=true&characterEncoding=utf8";
	public static Connection con = null;
	public static PreparedStatement ps = null;
	public static ResultSet rs = null;
	//1:自动加载 mysql jdbc驱动
	static{
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.out.println("加载驱动失败");
		}
	}
	
	
	//2：连接数据库
	
	public static Connection getConnection(){
		try {
			con = DriverManager.getConnection(DATABASE_URL);
			System.out.println("数据库连接成功");
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
		}
		return con;
	}
	
	//3:增删改的方法
	public static int addAndUpdateAndDelete(String sql,Object[] params){
		int row = 0;
		con = getConnection();
		
		try {
			ps = con.prepareStatement(sql);
			setParams(ps,params);
			row = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql语句有误");
		}finally{
			closeAll(rs,ps,con);
		}
		return row;
	}
	/**
	 * 4设置参数
	 * @param ps2
	 * @param params
	 */
	private static void setParams(PreparedStatement ps, Object[] params) {
		if(params==null) return;
		for(int i=0;i<params.length;i++){
			try {
				ps.setObject(i+1, params[i]);
			} catch (SQLException e) {
				System.out.println("参数异常");
			}
		}
		
	}
	/**
	 * 通用查询方法
	 * @param sql
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List selectList(String sql,Object[] params){
		List tableList = new ArrayList();
		con = getConnection();
		try {
			ps = con.prepareStatement(sql);
			setParams(ps, params);
			rs = ps.executeQuery();
			//获取总列数
			int count = rs.getMetaData().getColumnCount();
			while(rs.next()){
				List rowList = new ArrayList();//存储一行的数据
				for(int i=1;i<=count;i++){
					rowList.add(rs.getObject(i));
				}
				tableList.add(rowList);//所有结果集
			}
		} catch (SQLException e) {
			System.out.println("查询sql有误");
		}finally{
			closeAll(rs, ps, con);
		}
		return tableList;
	}
	/**
	 * 查询 通用
	 * @param sql
	 * @param params
	 * @param clazz  实体对象 Student.class
	 * @return
	 */
	public static List selectList(String sql,Object[] params,Class clazz){ 
		List list = new ArrayList();
		con = getConnection();
		
		try {
			ps = con.prepareStatement(sql);
			setParams(ps, params);
			rs = ps.executeQuery();
			//获取列数
			int count = rs.getMetaData().getColumnCount();
			while(rs.next()){
				Object obj = clazz.newInstance();//实例化一个对象
				for(int i=1;i<=count;i++){
					//获取列名
					String columnName = rs.getMetaData().getColumnName(i).toLowerCase().replace("_", "");
					//获取列值
					Object columnVal = rs.getObject(i);
					//获取对象的成员变量（字段名）
					Field field = clazz.getDeclaredField(columnName); 
					//设置可访问
					field.setAccessible(true);
					if(columnVal!=null){
						field.set(obj, convert(columnVal.toString(),field.getType()));
					}
				}
				list.add(obj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 判断类型的
	 * @param param 每一列的值
	 * @param clas 每一列的类型
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T extends Serializable> T convert(String param, Class clas) {
		if (param == null || param == "" || clas == null)
			return null;
		String type = clas.getName();// 获得要转换的数据类型名称
		
		if (type.equals("java.lang.String"))
			return (T) param;
		try {// 根据不同类型的属性，返回不同的结果，如果出现异常，则返回null
			if (type.equals("java.util.Date")) {
				param +=" 0:00:00";
				return (T) new java.util.Date(java.sql.Timestamp.valueOf(param).getTime());
			}
			if (type.equals("java.sql.Date")) {
				return (T) new java.sql.Date(java.sql.Timestamp.valueOf(param).getTime());
			}
			if (type.equals("java.sql.Timestamp")) {
				return (T) java.sql.Timestamp.valueOf(param);
			}
			if (type.equals("java.lang.Char")) {
				return (T) Character.valueOf(param.charAt(0));
			}
			if (type.equals("java.lang.Integer") || type.equals("int")) {
				return (T) Integer.valueOf(param);
			}
			if (type.equals("java.lang.Double") || type.equals("double")) {
				return (T) Double.valueOf(param);
			}
			if (type.equals("java.lang.Float") || type.equals("float")) {
				return (T) Float.valueOf(param);
			}
			if (type.equals("java.lang.Byte") || type.equals("byte")) {
				return (T) Byte.valueOf(param);
			}
			if (type.equals("java.lang.Short") || type.equals("short")) {
				return (T) Short.valueOf(param);
			}
			if (type.equals("java.lang.Long") || type.equals("long")) {
				return (T) Long.valueOf(param);
			}
			if (type.equals("java.lang.Boolean") || type.equals("boolean")) {
				return (T) Boolean.valueOf(param);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 6关闭方法
	 * @throws SQLException 
	 */
	public static void closeAll(ResultSet rs, PreparedStatement ps,Connection con){
		try {
			if(rs!=null){
				rs.close();
			}
			
			if(ps!=null){
				ps.close();
			}
			
			if(con!=null){
				con.close();
			}
		} catch (Exception e) {
			System.out.println("关闭异常");
		}
	}
}
