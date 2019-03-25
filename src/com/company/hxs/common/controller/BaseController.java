package com.company.hxs.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.company.hxs.common.sys.SysConstant;

public class BaseController {

	public static HttpServletRequest getCurRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		return request;
	}

	public static HttpSession getCurSession() {
		return getCurRequest().getSession();
	}
	
	/**
	 * 鑾峰彇褰撳墠椤�
	 * @return
	 */
	public static Integer getCurPage() {
		String p = getCurRequest().getParameter("page");
		if (p != null) {
			return Integer.valueOf(p);
		}
		return Integer.valueOf(0);
	}

	/**
	 * 鑾峰彇褰撳墠琛�
	 * @return
	 */
	public static Integer getCurRows() {
		String rows = getCurRequest().getParameter("rows");
		if (rows != null) {
			return Integer.valueOf(rows);
		}
		return Integer.valueOf(0);
	}
	
	/**
	 * 瀵煎嚭excel
	 * @param beans
	 * @param templateFile
	 * @param downName
	 * @param response
	 * @throws Exception
	 */
	protected void export_report(Map<String, Object> beans, String templateFile, String downName, HttpServletResponse response) throws Exception {
		XLSTransformer transformer = new XLSTransformer();
		InputStream fis = new FileInputStream(templateFile);
		HSSFWorkbook wb = (HSSFWorkbook) transformer.transformXLS(fis, beans);
		response.reset();
		response.setContentType("application/octet-stream; charset=utf-8");
		response.setHeader("content-disposition", "attachment;filename=" + downName + ".xls");
		OutputStream os = response.getOutputStream();
		wb.write(os);
	}
	
	/**
	 * 涓嬭浇鏂囦欢
	 * @param file
	 * @param fileName
	 * @param res
	 * @return
	 * @throws IOException
	 */
	protected String downFile(File file, String fileName, HttpServletResponse res) throws IOException {
		OutputStream os = null;
		if ((file == null) || (!file.exists())) {
			return "文件为空";
		}
		try {
			os = res.getOutputStream();
			fileName = new String(fileName.getBytes(), "ISO8859-1");
			res.reset();
			res.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			res.setContentType("application/octet-stream; charset=utf-8");
			os.write(FileUtils.readFileToByteArray(file));
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return "文件下载报错";
		} finally {
			if (os != null) {
				os.close();
			}
		}
		return null;
	}
	
	/**
	 * 创建返回结果
	 * @param flag
	 * @param msg
	 * @return
	 */
	public JSONObject createResult(boolean flag, String msg){
		JSONObject js = new JSONObject();
		js.put(SysConstant.AJAX_SUCCESS, flag);
		js.put(SysConstant.AJAX_MSG, msg);
		return js;
	}
	
}
