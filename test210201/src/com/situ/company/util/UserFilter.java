package com.situ.company.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.situ.company.employee.model.EmployeeModel;

public class UserFilter implements Filter {
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8"); 
        resp.setCharacterEncoding("utf-8");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		EmployeeModel user = (EmployeeModel) session.getAttribute("user");
		if (user == null) {
			req.getRequestDispatcher("/web/login.jsp").forward(request, resp);
		} else
			chain.doFilter(req, resp);
	}
}
