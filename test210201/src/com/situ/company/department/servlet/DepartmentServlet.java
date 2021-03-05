package com.situ.company.department.servlet;

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

import com.situ.company.department.model.DepartmentModel;
import com.situ.company.department.service.DepartmentService;

/**
 * Servlet implementation class DepartmentServlet
 */
@WebServlet("/department")
public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DepartmentService service = new DepartmentService();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DepartmentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		System.out.println(action);
		String result = "";
		System.out.println(action);
		switch (action) {
		case "add":
			result = add(request);
			//System.out.print(result + "" + "WW");
			break;
		case "sel":
			result = sel(request);
			//System.out.print(result + "" + "QQ");
			break;
		case "del":
			result = del(request);
			//System.out.print(result + "" + "QQ");
			break;
		case "upd":
			result = upd(request);
			//System.out.print(result + "" + "QQ");
			break;
		case "get":
			result = get(request);
			//System.out.print(result + "" + "QQ");
			break;
		case "list":
			result = list(request);
		default:
			break;
		}out(response, result);
	}

	/**
	 * @param request
	 * @return
	 */
	private String list(HttpServletRequest request) {
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		
		String pageIndex = request.getParameter("pageIndex");
		String pageLimit = request.getParameter("pageLimit");
		
		DepartmentModel model = new DepartmentModel(code,name);
		
		model.setPageIndex(Integer.parseInt(pageIndex));
		model.setPageLimit(Integer.parseInt(pageLimit));
		model.setPageOn(true);
		
		List<DepartmentModel> list = service.selectList(model);
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
		DepartmentModel model = new DepartmentModel(code);
		DepartmentModel mdb = service.selectModel(model);
		return new JSONObject(mdb).toString();
	}

	private String upd(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		// TODO Auto-generated method stub
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		DepartmentModel model = new DepartmentModel(code,name,tel);
		System.out.print("SERVLET"+model);
		return service.update(model);
	}

	private String del(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String code = request.getParameter("code");
		DepartmentModel model = new DepartmentModel(code);
		return service.delete(model);
		
	}

	private String sel(HttpServletRequest request) {
		// TODO Auto-generated method stub\
		System.out.println("sss");
		String name = request.getParameter("name");
		String code = request.getParameter("code");
		//System.out.println("spps"+name);
		//System.out.println(name.isEmpty());
		//System.out.println(code.isEmpty());
		DepartmentModel model = new DepartmentModel(code,name);
		System.out.println(model);
	
		List<DepartmentModel> list = service.selectList(model);
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
		String tel = request.getParameter("tel");
		DepartmentModel model = new DepartmentModel(code, name, tel);
		return service.insert(model);

	}

}
