package com.situ.company.employee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.situ.company.employee.model.EmployeeModel;
import com.situ.company.employee.service.EmployeeService;
import com.situ.company.util.AuthCodeServlet;
import com.situ.company.util.FmtEmpty;
import com.situ.company.util.MD5;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeService service = new EmployeeService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}
//控制层：接受请求  获取参数 封装对象 调用方法（传递参数） 返回结果（页面跳转）
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		System.out.println("7888");
		String action = req.getParameter("action");
		System.out.println("action:"+action);
		String result = null;
		switch (action) {
		case "reg":
			result = reg(req);
			break;
	
		case "login":
			result = login(req);
			break;
		case "add":
			result = add(req);
			//System.out.print(result + "" + "WW");
			break;
		case "sel":
			result = sel(req);
			//System.out.print(result + "" + "QQ");
			break;
		case "del":
			result = del(req);
			//System.out.print(result + "" + "QQ");
			break;
		case "upd":
			result = upd(req);
			//System.out.print(result + "" + "QQ");
			break;
		case "get":
			result = get(req);
			//System.out.print(result + "" + "QQ");
			break;
		case "list":
			result = list(req);
			break;
		case "resetPass":
			result = resetPass(req);
			break;
			
		case "updpass":
			result = updPass(req);
			break;
		case "upddept":
			result = upddept(req);
			break;
		default:
			break;
		}

		out(res, result);
	}

	private String upddept(HttpServletRequest req) {
		String code = req.getParameter("code");
		String codeDept = req.getParameter("codeDept");
		
		EmployeeModel model = new EmployeeModel(code,"","",codeDept);
		System.out.println("servlet"+model);
		return service.update(model);
}

	private String updPass(HttpServletRequest req) {
		// TODO Auto-generated method stub
		String code = req.getParameter("code");
		String pass = req.getParameter("pass");
		pass=MD5.encode(pass);
		EmployeeModel model = new EmployeeModel(code,pass);
		System.out.println("servlet"+model);
		return service.update(model);
	}

	private String resetPass(HttpServletRequest req) {
	// TODO Auto-generated method stub
		String code = req.getParameter("code");
		EmployeeModel model = new EmployeeModel(code);
		model.setPass(getDefaultPass());
		return service.update(model);
	
}
private String getDefaultPass() {
	return MD5.encode("123456");
}
	private String list(HttpServletRequest request) {
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		
		String pageIndex = request.getParameter("pageIndex");
		String pageLimit = request.getParameter("pageLimit");
		System.out.println("KK"+pageIndex+" PP"+pageLimit);
		EmployeeModel model = new EmployeeModel(code,name,null);
		
		model.setPageIndex(Integer.parseInt(pageIndex));
		model.setPageLimit(Integer.parseInt(pageLimit));
		model.setPageOn(true);
		
		List<EmployeeModel> list = service.selectList(model);
		int count = service.selectCount(model);
		Map<String , Object> map = new HashMap<>();
		map.put("data", list);
		map.put("count", count);
		//分页后的记录条数=带有查询条件的并带有limit的查询结果的记录条数(<10)
		//分页前的记录条数=带有查询条件的不带有limit的查询结果的记录条数(>10)
		return new JSONObject(map).toString();
	}

	private String get(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String code = request.getParameter("code");
		EmployeeModel model = new EmployeeModel(code);
		EmployeeModel mdb = service.selectModel(model);
		return new JSONObject(mdb).toString();
	}

	private String upd(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		// TODO Auto-generated method stub
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String codeDept=request.getParameter("codeDept");
		EmployeeModel model = new EmployeeModel(code,name,pass,codeDept);
		System.out.print("SERVLET"+model);
		return service.update(model);
	}

	private String del(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String code = request.getParameter("code");
		EmployeeModel model = new EmployeeModel(code);
		return service.delete(model);
		
	}

	private String sel(HttpServletRequest request) {
		// TODO Auto-generated method stub\
		String name = request.getParameter("name");
		String code = request.getParameter("code");
		
		/*
		 * System.out.println(name.isEmpty()); System.out.println(code.isEmpty());
		 */
		EmployeeModel model = new EmployeeModel(code,name,null);
		
	
		List<EmployeeModel> list = service.selectList(model);
		return new JSONArray(list).toString();
		
	}

	private void out(HttpServletResponse res, String result) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = res.getWriter();
		out.print(result);
		out.flush();
		out.close();
		out = null;
	}

	

	private String add(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String code = request.getParameter("code");
		String pass = request.getParameter("pass");
		String pass2;
		if(FmtEmpty.isEmpty(pass)) {
			pass2 = MD5.encode("123456");
		}else {
            pass2=MD5.encode(pass);
		}
		EmployeeModel model = new EmployeeModel(code, name, pass2);
		return service.insert(model);

	}

	private String login(HttpServletRequest req) {
		// TODO Auto-generated method stub
		String code = req.getParameter("code");
		String pass = req.getParameter("pass");

		EmployeeModel model = new EmployeeModel(code, pass);

		String authCode = req.getParameter("authCode");

		// result=service. insert(model);
		String authCodeSession = (String) req.getSession().getAttribute(AuthCodeServlet.name);
		if (!authCodeSession.toString().equals(authCode)) {
			return "no";
		}

		String result = EmployeeService.login(model);
		System.out.print(result);
		if (result == "1") {
			req.getSession().setAttribute("user", model);
			// System.out.print(model);
			return "1";
		} else if (result == "0") {
			System.out.print("jj");
			return "0";
		}
		return "2";
	}

	private String reg(HttpServletRequest req) {
		String code = req.getParameter("code");
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
	
		EmployeeModel model = new EmployeeModel(code, name, pass);
System.out.println("reg"+model);
		String authCode = req.getParameter("authCode");

		// result=service. insert(model);
		String authCodeSession = (String) req.getSession().getAttribute(AuthCodeServlet.name);
		if (!authCodeSession.toString().equals(authCode)) {
			return "no";
		}

		return service.insert(model);

		// System.out.print(code+" "+name+" "+pass);

	}
}
