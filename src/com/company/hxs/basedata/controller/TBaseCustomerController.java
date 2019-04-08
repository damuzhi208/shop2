package com.company.hxs.basedata.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.hxs.basedata.entity.TBaseCustomer;
import com.company.hxs.basedata.service.TBaseCustomerService;
import com.company.hxs.common.Page;
import com.company.hxs.common.controller.BaseController;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.util.ResponseTool;
import com.company.hxs.common.vo.SelectVO;

@Controller
@RequestMapping("customer")
public class TBaseCustomerController extends BaseController{
	
	@Resource private TBaseCustomerService tBaseCustomerService;
	
	@RequestMapping("list")
	public String listCustomer(){
		return "basedata/listCustomer";
	}
	
	/**
	 * 查询客户信息
	 * @return
	 */
	@RequestMapping("listData")
	@ResponseBody
	public String listData(TBaseCustomer customer, Integer page, Integer rows){
		Page<TBaseCustomer> result = tBaseCustomerService.getPageResult(customer, page, rows);
		return BaseService.List2Json(result.getRows(), result.getTotal());
	}
	
	/**
	 * 修改客户资料
	 * @return
	 */
	@RequestMapping("modCustomer")
	public String modCustomer(HttpServletRequest request, Integer id){
		if(id != null){
			TBaseCustomer customer = tBaseCustomerService.getTBaseCustomer(id);
			request.setAttribute("customer", customer);
		}
		return "basedata/modCustomer";
	}
	
	/**
	 * 更新客户资料
	 * @return
	 */
	@RequestMapping("updateCustomer")
	@ResponseBody
	public void updateCustomer(HttpServletResponse response, TBaseCustomer customer){
		JSONObject js = new JSONObject();
		try{
			tBaseCustomerService.updateTBaseCustomer(customer);
			js = createResult(true, "保存成功");
		}catch(Exception e){
			e.printStackTrace();
			js = createResult(false, "出错："+e.getMessage());
		}
		ResponseTool.write(response, js);
	}
	
	/**
	 * 删除顾客
	 * @param response
	 * @param id
	 */
	@RequestMapping("delCustomer")
	@ResponseBody
	public void delCustomer(HttpServletResponse response, Integer id){
		JSONObject js = new JSONObject();
		try{
			tBaseCustomerService.delCustomer(id);
			js = createResult(true, "删除成功");
		}catch(Exception e){
			e.printStackTrace();
			js = createResult(false, "删除失败："+e.getMessage());
		}
		ResponseTool.write(response, js);
	}
	
	/**
	 * 顾客列表
	 * @return
	 */
	@RequestMapping("select")
	@ResponseBody
	public String getSelectCustomer(){
		List<SelectVO> list = tBaseCustomerService.getSelectList();
		return JSONArray.fromObject(list).toString();
	}
	
	/**
	 * 顾客类别
	 * @return
	 */
	@RequestMapping("cusType")
	@ResponseBody
	public String getSelectCusType(){
		List<SelectVO> list = tBaseCustomerService.getCusType();
		return JSONArray.fromObject(list).toString();
	}
	
	@RequestMapping("getCustomer")
	@ResponseBody
	public String getCustomer(Integer id){
		TBaseCustomer cus = tBaseCustomerService.getTBaseCustomer(id);
		return JSONObject.fromObject(cus).toString();
	}
}
