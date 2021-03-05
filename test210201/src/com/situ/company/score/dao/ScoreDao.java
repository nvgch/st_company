package com.situ.company.score.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.situ.company.score.model.ScoreModel;
import com.situ.company.util.FmtEmpty;
import com.situ.company.util.JDBCUtil;

public class ScoreDao {
	private static String table = "code";
	private static String cols = "code_emp,code_pro,score";

	private Connection conn;
	private PreparedStatement ps;

//int/Integer/String
	public Integer insert(ScoreModel model) {
		StringBuffer sql = new StringBuffer("insert into ");
		sql.append(table).append('(').append(cols).append(")value(?,?,?)");

		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, model.getCodeEmp());
			ps.setString(2, model.getCodePro());
			ps.setString(3, model.getScore());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, ps);
		}
		return null;
	}

	public List<ScoreModel> selectList(ScoreModel model) {
		StringBuffer sql = new StringBuffer("select ");
		sql.append(cols).append(" from ").append(table);
		List<Object> vals = appendWhere(sql, model);
		ResultSet rs = null;
		List<ScoreModel> list = new ArrayList<>();

		conn = JDBCUtil.getConnection();
		try {
			ps = conn.prepareStatement(sql.toString());
			for (int i = 0; i < vals.size(); ++i) {
				ps.setObject(i + 1, vals.get(i));
				rs = ps.executeQuery();
				while (rs.next()) {
					ScoreModel mbd = new ScoreModel();
					mbd.setCodeEmp(rs.getString("emp_code"));
					mbd.setCodePro(rs.getString("pro_code"));
					mbd.setScore(rs.getString("score"));
					list.add(mbd);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, ps, rs);
		}

		return list;

	}

	public Integer delete(ScoreModel model) {
		StringBuffer sql = new StringBuffer("delete from ");
		sql.append(table);
		// where code_emp = ? and code_pro = ?
		// where id = ?
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

	public Integer update(ScoreModel model) {
		StringBuffer sql = new StringBuffer("update ");
		sql.append(table);// set name=?,tel=? where code=?
		List<Object> vals = appendSet(sql, model);

		conn = JDBCUtil.getConnection();
		try {
			ps = conn.prepareStatement(sql.toString());
			for (int i = 0; i < vals.size(); i++) {
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

	private List<Object> appendSet2(StringBuffer sql, ScoreModel model) {
		List<Object> list = new ArrayList<>();
		sql.append(" set score = ? where code_emp=? and code_pro=?");
		String score = model.getScore();

		list.add(score);

		String ce = model.getCodeEmp();

		list.add(ce);

		String cp = model.getCodePro();

		list.add(cp);

		return list;
	}

	private List<Object> appendSet(StringBuffer sql, ScoreModel model) {
		List<Object> list = new ArrayList<>();
		sql.append(" set id=id");

		String ce = model.getCodeEmp();
		if (ce != null) {// !"".equals(name.trim())
			sql.append(" ,code_emp = ?");
			list.add(ce);
		}
		String cp = model.getCodePro();
		if (cp != null) {// !"".equals(tel.trim())
			sql.append(" ,code_pro = ?");
			list.add(cp);
		}
		String score = model.getScore();
		if (score != null) {// !"".equals(tel.trim())
			sql.append(" ,score = ?");
			list.add(score);
		}
		sql.append(" where id = ?");
		list.add(model.getId());

		return list;
	}

	private List<Object> appendWhere(StringBuffer sql, ScoreModel model) {
		sql.append(" where 1=1");
		List<Object> objs = new ArrayList<>();
		Integer id = model.getId();
		if (id != null) {
			sql.append(" and id = ?");
			objs.add(id);
		}
		String ce = model.getCodeEmp();
		if (!FmtEmpty.isEmpty(ce)) {// !"".equals(name.trim())
			sql.append(" and code_emp like ?");
			objs.add(ce);
		}
		String cp = model.getCodePro();
		if (!FmtEmpty.isEmpty(cp)) {// !"".equals(name.trim())
			sql.append(" and code_emp like ?");
			objs.add(cp);
		}
		String score = model.getScore();
		if (!FmtEmpty.isEmpty(score)) {// !"".equals(tel.trim())
			sql.append(" and score like ?");
			objs.add(score);
		}
		return objs;
	}
}
