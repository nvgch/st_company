package com.situ.company.employee.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.situ.company.util.MD5;



public class EmployeeService {
	private static EmployeeDao dao = new EmployeeDao();
	/*
	 * public static void main(String[] args) { EmployeeModel model=new
	 * EmployeeModel("1","2","3"); System.out.print(insert(model)); }
	 */
	//返回类型的核心作用：为了在调用该方法之后，通过返回结果，知道该方法
	
public static String insert(EmployeeModel model) {
	//添加的逻辑主键不存在时允许添加，否则不允许添加
	EmployeeModel mdb = selectModel(model);
	//System.out.print(mdb);
	if(mdb==null)
	{
	Integer result = dao.insert(model);
	return String.valueOf(result);
	}
	return "E";
	
}
public String delete(EmployeeModel model) {
	Integer result = dao.delete(model);
	return String.valueOf(result);
}
/**
 * @param model
 * @return
 */
public String update(EmployeeModel model) {
	Integer result = dao.update(model);
	return String.valueOf(result);
}


/**
 * zhang'h
 * @param model
 */
public static String login(EmployeeModel model) {
	// TODO Auto-generated method stub

	
	String pass=MD5.encode(model.getPass());
EmployeeModel mdb = selectModel(model);

//System.out.print(mdb);
if(mdb == null)
	return "0";
else {
	 HttpServletRequest req = null;
	 req.getSession().setAttribute("user", model);
return mdb.getPass().equals(pass) ? "1" : "2";

}

}

/**
 * 
 *  多条件+模糊查询多条记录
 * @param model
 * @return List<EmployeeModel>
 */
public static List<EmployeeModel> selectList(EmployeeModel model) {
	String code = model.getCode();
	if(code!=null)
		model.setCode("%"+code+"%");
	String name = model.getName();
	if(name!=null)
		model.setName("%"+name+"%");
	
	//System.out.print(model);
	List<EmployeeModel> list = dao.selectList(model);
	return list;
}

	
/**
 * 根据(逻辑）主键查询出唯一记录,如果逻辑主键不存在返回null
 * @param model
 * @return
 */
public static EmployeeModel selectModel(EmployeeModel model) {
	EmployeeModel temp = new EmployeeModel();
	temp.setCode(model.getCode());
	List<EmployeeModel> list = dao.selectList(temp);
	//System.out.print(list.toString());
	if(FmtEmpty.isEmpty(list)) {
		return null;
	}
	
	return list.get(0);
	
	
	
	
}








public int selectCount(EmployeeModel model) {
	EmployeeModel mdb = new EmployeeModel();
	String code = model.getCode();
	mdb.setCode(code == null ? "%%" : "%" + code + "%");
    String name = model.getName();
    mdb.setName(name == null ? "%%" : "%" + name + "%");
    return dao.selectCount(mdb);

}

/**
 * 账户登录 ，返回0=账号不存在，返回1=登陆成功，返回2=密码错误
 * @param model
 */
	/*
	 * public static void login(EmployeeModel model) { // TODO Auto-generated method
	 * stub EmployeeModel mdb = selectModel(model); System.out.print(mdb);
	 * 
	 * }
	 */
	
	  public static void main(String[] args) { EmployeeModel model = new
	  EmployeeModel("A", "A","A"); // TODO Auto-generated method stub 

	  System.out.print(insert(model));
		/*
		 * if(!FmtEmpty.isEmpty(list)) { System.out.print("aa"); }
		 */ }
	 
}
