package com.company.hxs.basedata.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.hxs.basedata.entity.TBaseQiaojia;
import com.company.hxs.basedata.service.TBaseQiaoJiaService;
import com.company.hxs.common.Page;
import com.company.hxs.common.controller.BaseController;
import com.company.hxs.common.service.BaseService;

@Controller
@RequestMapping("baseqj")
public class TBaseQiaojiaController extends BaseController{
	
	@Resource TBaseQiaoJiaService tBaseQiaoJiaService;
	
	/**
	 * 桥架列表页面
	 * @return
	 */
	@RequestMapping("list")
	public String getQjPage(HttpServletRequest request){
		
		return "basedata/list";
	}
	
	/**
	 * 获取桥架数据列表
	 * @return
	 */
	@RequestMapping("listData")
	@ResponseBody
	public String getQjListData(HttpServletRequest request, TBaseQiaojia qiaojia, Integer page, Integer rows){
		Page<TBaseQiaojia> pages = tBaseQiaoJiaService.getPageInfoList(qiaojia, page , rows);
		return BaseService.List2Json(pages.getRows(), pages.getTotal()).toString();
	}
	
	/**
	 * 修改桥架
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("modBaseQj")
	public String modBaseQj(HttpServletRequest request, String id){
		TBaseQiaojia qj = tBaseQiaoJiaService.getTBaseQiaoJiaById(id);
		request.setAttribute("qj", qj);
		return "basedata/modBaseQj";
	}
	
	@RequestMapping("updateBaseQj")
	@ResponseBody
	public String updateBaseQj(TBaseQiaojia qiaojia){
		JSONObject js = new JSONObject();
		try{
			tBaseQiaoJiaService.updateTBaseQj(qiaojia);
			js = createResult(true, "操作成功");
		}catch(Exception e){
			e.printStackTrace();
			js = createResult(false, "错误信息：" + e.getMessage());
		}
		return js.toString();
	}
	
}
