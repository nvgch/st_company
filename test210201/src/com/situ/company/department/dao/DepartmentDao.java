package com.situ.company.department.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.situ.company.department.model.DepartmentModel;
import com.situ.company.util.FmtEmpty;
import com.situ.company.util.JDBCUtil;

public class DepartmentDao {
	private static String table = "department";
	private static String cols = "code,name,tel";

	private static Connection conn;
	private static PreparedStatement ps;

	/*
	 * public static void main(String[] args) { DepartmentModel model = new
	 * DepartmentModel("dc03", "name", "tel"); int u = update(model);
	 * System.out.print(u); }
	 */

//int/Integer/String
	public Integer insert(DepartmentModel model) {
		StringBuffer sql = new StringBuffer("insert into ");
		sql.append(table).append('(').append(cols).append(")value(?,?,?)");

		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, model.getCode());
			ps.setString(2, model.getName());
			ps.setString(3, model.getTel());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, ps);
		}
		return null;
	}

	public static List<DepartmentModel> selectList(DepartmentModel model) {

		StringBuffer sql = new StringBuffer("select ");
		sql.append(cols).append(" from ").append(table);
		List<Object> vals = appendWhere(sql, model);
	
		ResultSet rs = null;
		List<DepartmentModel> list = new ArrayList<>();
        System.out.println(sql.toString());
		conn = JDBCUtil.getConnection();
		System.out.print(sql.toString());
		try {
			ps = conn.prepareStatement(sql.toString());

			for (int i = 0; i < vals.size(); ++i) {
				{
					ps.setObject(i + 1, vals.get(i));

					// System.out.println(vals.get(i)+"9");
				}
				System.out.print(ps);
			}
			rs = ps.executeQuery();

			while (rs.next()) {
				DepartmentModel mbd = new DepartmentModel();
				mbd.setCode(rs.getString("code"));
				mbd.setName(rs.getString("name"));
				mbd.setTel(rs.getString("tel"));

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

	public Integer delete(DepartmentModel model) {
		StringBuffer sql = new StringBuffer("delete from ");
		sql.append(table);// where code = ?
		List<Object> vals = appendWhere(sql, model);

		conn = JDBCUtil.getConnection();
		try {
			ps = conn.prepareStatement(sql.toString());
			for (int i = 0; i < vals.size(); ++i) {
				ps.setObject(i + 1, vals.get(i));

			}
			return ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, ps);
		}

		return null;
	}

	public static Integer update(DepartmentModel model) {
		StringBuffer sql = new StringBuffer("update ");
		sql.append(table);// set name=?,tel=? where code=?
		List<Object> vals = appendSet(sql, model);

		conn = JDBCUtil.getConnection();
		System.out.println(vals.size());
		try {
			ps = conn.prepareStatement(sql.toString());
			System.out.print(ps);
			for (int i = 0; i < vals.size(); i++) {
				System.out.println("A");
				ps.setObject(i + 1, vals.get(i));
			}
			// System.out.print(vals.get(i));
			return ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, ps);
		}
		return null;

	}

	private static List<Object> appendSet(StringBuffer sql, DepartmentModel model) {
		sql.append(" set id=id");
		List<Object> objs = new ArrayList<>();

		String name = model.getName();
		if (name != null) {// !"".equals(name.trim())
			sql.append(" ,name = ?");
			objs.add(name);
		}
		String tel = model.getTel();
		if (tel != null) {// !"".equals(tel.trim())
			sql.append(" ,tel = ?");
			objs.add(tel);
		}
		String code = model.getCode();
		sql.append(" where code = ? and id>0");
		objs.add(code);
		System.out.print(objs);
		return objs;
	}

	private static List<Object> appendWhere(StringBuffer sql, DepartmentModel model) {
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
		String tel = model.getTel();
		if (!FmtEmpty.isEmpty(tel)) {// !"".equals(tel.trim())
			sql.append(" and tel like ?");
			objs.add(tel);
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
		System.out.println(objs);
		return objs;
	}

	public static int selectCount(DepartmentModel model) {
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
public static void main(String[] args) {
	DepartmentModel model=new DepartmentModel("%k%","%4%",null);
	//model.setPageIndex(1);
	//model.setPageLimit(10);
	System.out.println(selectList(model));
}
}
