package com.situ.company.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.situ.company.department.model.DepartmentModel;
import com.situ.company.employee.model.EmployeeModel;
import com.situ.company.util.FmtEmpty;
import com.situ.company.util.JDBCUtil;

public class EmployeeDao {
	private static String table = "employee";
	private static String fields = "code,pass,name,code_dept,image";

	private static Connection conn;
	private static PreparedStatement ps;

	
	
	/*
	 * public static void main(String[] args) { List<EmployeeModel> list = new
	 * ArrayList<>(); EmployeeModel model=new EmployeeModel("%0%");
	 * list=selectList(model); for(EmployeeModel u:list) { System.out.println(u); }
	 * }
	 */


//int/Integer/String
	public Integer insert(EmployeeModel model) {
		StringBuffer sql = new StringBuffer("insert into ");
		sql.append(table).append('(').append(fields).append(")value(?,?,?,?,?)");

		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, model.getCode());
			
			ps.setString(2, model.getPass());
			ps.setString(3, model.getName());
			
			ps.setString(4, model.getCodeDept());
			ps.setString(5, model.getImage());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, ps);
		}
		return null;
	}

	public List<EmployeeModel> selectList(EmployeeModel model) {
		StringBuffer sql = new StringBuffer("select ");
		sql.append(fields).append(" from ").append(table);
		List<Object> vals = appendWhere(sql, model);
		ResultSet rs = null;
		List<EmployeeModel> list = new ArrayList<>();
		
		conn = JDBCUtil.getConnection();
		try {
			ps = conn.prepareStatement(sql.toString());
			for (int i = 0; i < vals.size(); ++i) {
				
				ps.setObject(i + 1, vals.get(i));}
				rs = ps.executeQuery();
				while (rs.next()) {
					EmployeeModel mbd = new EmployeeModel();
					mbd.setCode(rs.getString("code"));
					mbd.setName(rs.getString("name"));
					mbd.setPass(rs.getString("pass"));
					mbd.setCodeDept(rs.getString("code_dept"));
					mbd.setImage(rs.getString("image"));
					list.add(mbd);
				
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, ps, rs);
		}

		return list;

	}

	public Integer delete(EmployeeModel model) {
		StringBuffer sql = new StringBuffer("delete from ");
		sql.append(table);// where code = ?
		List<Object> vals = appendWhere(sql, model);

		conn = JDBCUtil.getConnection();
		try {
			ps = conn.prepareStatement(sql.toString());
			for (int i = 0; i < vals.size(); ++i) {
				ps.setObject(i + 1, vals.get(i));
				return ps.executeUpdate();

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, ps);
		}

		return null;
	}

	public Integer update(EmployeeModel model) {
		StringBuffer sql = new StringBuffer("update ");
		sql.append(table);// set name=?,tel=? where code=?
		List<Object> vals = appendSet(sql, model);
System.out.println(sql);
		conn = JDBCUtil.getConnection();
		try {
			ps = conn.prepareStatement(sql.toString());
			for (int i = 0; i < vals.size(); ++i) {
				ps.setObject(i + 1, vals.get(i));}
			System.out.println("ps"+ps);
				return ps.executeUpdate();

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, ps);
		}
		return null;

	}

	private List<Object> appendSet(StringBuffer sql, EmployeeModel model) {
		sql.append(" set id=id");
		List<Object> objs = new ArrayList<>();

		String name = model.getName();
		if (!FmtEmpty.isEmpty(name)) {// !"".equals(name.trim())
			sql.append(" ,name = ?");
			objs.add(name);
		}
		String pass = model.getPass();
		if (!FmtEmpty.isEmpty(pass)) {// !"".equals(tel.trim())
			sql.append(" ,pass = ?");
			objs.add(pass);
		}
		String codeDept  = model.getCodeDept();
		
		if (!FmtEmpty.isEmpty(codeDept)) {// !"".equals(tel.trim())
			sql.append(" ,code_dept = ?");
			objs.add(codeDept);
		}
String image  = model.getImage();
		
		if (!FmtEmpty.isEmpty(image)) {// !"".equals(tel.trim())
			sql.append(" ,image = ?");
			objs.add(image);
		}
		String code = model.getCode();
		sql.append(" where code = ? and id>=0");
		objs.add(code);

		return objs;
	}

	private static List<Object> appendWhere(StringBuffer sql, EmployeeModel model) {
		sql.append(" where 1=1");
		List<Object> objs = new ArrayList<>();
		String code = model.getCode();
		if (!FmtEmpty.isEmpty(code)) {// !"".equals(code.trim())
			sql.append(" and code like ?");
			objs.add(code);
		}
		String name = model.getName();
		if (!FmtEmpty.isEmpty(name)) {// !"".equals(name.trim())
			sql.append(" and name like ?");
			objs.add(name);
		}
		String pass = model.getPass();
		if (!FmtEmpty.isEmpty(pass)) {// !"".equals(tel.trim())
			sql.append(" and pass like ?");
			objs.add(pass);
		}
		String codeDept = model.getCodeDept();
		if (!FmtEmpty.isEmpty(codeDept)) {// !"".equals(tel.trim())
			sql.append(" and code_dept like ?");
			objs.add(codeDept);
		}
		String order = model.getOrderby();
		if (!FmtEmpty.isEmpty(order)) {
			sql.append(" order by ").append(order);
		}
		if (model.isPageOn()) {
			sql.append(" limit ?,? ");
			objs.add(model.getRowStart());
			objs.add(model.getPageLimit());
		}
		
		return objs;
	}

	public static int selectCount(EmployeeModel model) {
		StringBuffer sql = new StringBuffer("select count(id) from ");
		sql.append(table);// set name=?,tel=? where code=?
		List<Object> vals = appendWhere(sql, model);
System.out.println("sql:"+sql);
		ResultSet rs = null;
		conn = JDBCUtil.getConnection();

		try {
			ps = conn.prepareStatement(sql.toString());
			System.out.print("ps:"+ps);
			for (int i = 0; i < vals.size(); i++) {

				ps.setObject(i + 1, vals.get(i));
			}
			// System.out.print(vals.get(i));
			rs = ps.executeQuery();
			if (rs.next())
				return rs.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, ps, rs);
		}
		return 0;
	}
	
	
	
}
