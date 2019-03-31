package com.company.hxs.common.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * 工具类
 * @author luqi
 *
 */
public class ResponseTool {

	/**
	 * 输出
	 * @param response
	 * @param o
	 */
	public static void write(HttpServletResponse response, Object o) {
		PrintWriter out;
		response.setContentType("text/html;charset=utf-8");
		try {
			out = response.getWriter();
			out.println(o.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
