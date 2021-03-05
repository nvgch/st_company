package com.situ.company.employee.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.json.JSONObject;

import com.situ.company.employee.model.EmployeeModel;
import com.situ.company.employee.service.EmployeeService;
import com.situ.company.util.FmtRequest;
import com.situ.company.util.UploadUtil;

/**
 * Servlet implementation class EmployeeUploadServlet
 */
@WebServlet("/EmployeeUpload")
public class EmployeeUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeService service = new EmployeeService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeUploadServlet() {
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

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, Object> map = null;
		try {
			map = UploadUtil.upload(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String code = request.getParameter("code");
		System.out.print("code:"+code);
		EmployeeModel model = new EmployeeModel(code);
		EmployeeModel mdb = service.selectModel(model);
		List<String> list = (List<String>) map.get("list");
		String image = list.get(0);
		model.setImage(image);
		String res = service.update(model);
		Map<String ,String> result=new HashMap<>();
		result.put("code", res);
		FmtRequest.out( response, new JSONObject(result).toString());
	}

}
