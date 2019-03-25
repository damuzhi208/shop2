package com.company.hxs.basedata.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
	public String updateCustomer(TBaseCustomer customer){
		JSONObject js = new JSONObject();
		try{
			tBaseCustomerService.updateTBaseCustomer(customer);
			js = createResult(true, "保存成功");
		}catch(Exception e){
			e.printStackTrace();
			js = createResult(false, "出错："+e.getMessage());
		}
		return js.toString();
	}
	
	@RequestMapping("select")
	@ResponseBody
	public String getSelectCustomer(){
		List<SelectVO> list = tBaseCustomerService.getSelectList();
		return JSONArray.fromObject(list).toString();
	}
	
}
