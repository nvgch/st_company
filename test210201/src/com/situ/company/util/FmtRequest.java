package com.situ.company.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class FmtRequest {
public static void out(HttpServletResponse resp,String result) throws IOException {
	PrintWriter out = resp.getWriter();
	out.append(result);
	out.flush();
	out.close();
	out = null;
	
}
}
