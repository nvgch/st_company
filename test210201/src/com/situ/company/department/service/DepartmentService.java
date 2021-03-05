package com.situ.company.department.service;

import java.util.List;

import com.situ.company.department.dao.DepartmentDao;
import com.situ.company.department.model.DepartmentModel;
import com.situ.company.employee.dao.EmployeeDao;
import com.situ.company.employee.model.EmployeeModel;
//业务层/逻辑层--业务逻辑
//目的-在某个功能操作发生后，最终能保证数据库中的数据是正确的（不能脏数据）
//业务逻辑
//增
//删
//改
//查多个--模糊查询
//查一个--逻辑主键查询
import com.situ.company.util.FmtEmpty;

public class DepartmentService {
	private static DepartmentDao dao = new DepartmentDao();
	private static EmployeeDao edao = new EmployeeDao();

	public static String insert(DepartmentModel model) {
		// 添加的逻辑主键不存在时允许添加，否则不允许添加
		DepartmentModel mdb = selectModel(model);

		if (mdb == null) {
			Integer result = dao.insert(model);
			return String.valueOf(result);
		}
		return "E";

	}

	public String delete(DepartmentModel model) {
		EmployeeModel m0=new EmployeeModel();
		m0.setCodeDept(model.getCode());
		int count = EmployeeDao.selectCount(m0);
		if(count>0)
			return "E ";
		Integer result = dao.delete(model);
		return String.valueOf(result);
	}

	/**
	 * @param model
	 * @return
	 */
	public String update(DepartmentModel model) {
		Integer result = dao.update(model);
		return String.valueOf(result);
	}

	/**
	 * zhang'h
	 * 
	 * @param model
	 */

	/**
	 * 
	 * 多条件+模糊查询多条记录
	 * 
	 * @param model
	 * @return List<DepartmentModel>
	 */
	public static List<DepartmentModel> selectList(DepartmentModel model) {
		String code = model.getCode();
		if (!FmtEmpty.isEmpty(code))
			model.setCode('%' + code + '%');
		
		String name = model.getName();
        if (!FmtEmpty.isEmpty(name))
			model.setName('%' + name + '%');

		// System.out.print(model);
		List<DepartmentModel> list = dao.selectList(model);
		return list;
	}

	

	/*
	 * public static void main(String[] args) { DepartmentModel model=new
	 * DepartmentModel("1","1"); //System.out.print(model); List<DepartmentModel>
	 * objs = new ArrayList<DepartmentModel>(); System.out.print(selectList(model));
	 * 
	 * }
	 */

	/**
	 * 根据(逻辑）主键查询出唯一记录,如果逻辑主键不存在返回null
	 * 
	 * @param model
	 * @return
	 */
	public static DepartmentModel selectModel(DepartmentModel model) {
		DepartmentModel temp = new DepartmentModel();
		temp.setCode(model.getCode());
		List<DepartmentModel> list = dao.selectList(temp);
		// System.out.print(list.toString());
		if (FmtEmpty.isEmpty(list)) {
			return null;
		}

		return list.get(0);

	}

	public int selectCount(DepartmentModel model) {
		DepartmentModel mdb = new DepartmentModel();
		String code = model.getCode();
		mdb.setCode(code == null ? "%%" : "%" + code + "%");
	    String name = model.getName();
	    mdb.setName(name == null ? "%%" : "%" + name + "%");
	    return dao.selectCount(mdb);
	
	}
	/**
	 * 账户登录 ，返回0=账号不存在，返回1=登陆成功，返回2=密码错误
	 * 
	 * @param model
	 */
	/*
	 * public static void login(DepartmentModel model) { // TODO Auto-generated
	 * method stub DepartmentModel mdb = selectModel(model); System.out.print(mdb);
	 * 
	 * }
	 */

	/*
	 * public static void main(String[] args) { DepartmentModel model = new
	 * DepartmentModel("1"); // TODO Auto-generated method stub
	 * List<DepartmentModel> list=selectList(model); System.out.print(list);
	 * 
	 * if(!FmtEmpty.isEmpty(list)) { System.out.print("aa"); } }
	 */

}
