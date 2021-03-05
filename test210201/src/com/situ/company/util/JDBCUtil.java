package com.situ.company.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

//实体类
//业务功能类
//自定义工具类
public class JDBCUtil {
	static ResourceBundle rb=ResourceBundle.getBundle("com.situ.company.util.test");
	static String driver = rb.getString("driver");
	static String url    = rb.getString("url");
	static String user   =rb.getString("user");
	static String pass   =rb.getString("pass");
	static {
		try {
			Class.forName(driver);
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static  Connection getConnection()
	{
		try {
			return DriverManager.getConnection(url,user,pass);
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public static void close(Connection conn,Statement st) {
		try {
			if(st!=null) {
				st.close();
				st=null;
			}
		
		if(conn!=null) {
			conn.close();
			conn=null;
		}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(Connection conn,Statement st, ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
				rs=null;
			}
		
		
			if(st!=null) {
				st.close();
				st=null;
			}
		
		if(conn!=null) {
			conn.close();
			conn=null;
		}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
public static void main(String[] args) {
	Connection conn=JDBCUtil.getConnection();
	System.out.print(conn);
	JDBCUtil.close(conn, null);
}
}
