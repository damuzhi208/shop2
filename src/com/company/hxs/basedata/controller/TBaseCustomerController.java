package com.company.hxs.basedata.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.hxs.basedata.entity.TBaseCustomer;
import com.company.hxs.basedata.service.TBaseCustomerService;
import com.company.hxs.common.Page;
import com.company.hxs.common.service.BaseService;

@Controller
@RequestMapping("customer")
public class TBaseCustomerController {
	
	@Resource private TBaseCustomerService tBaseCustomerService;
	
	@RequestMapping("list")
	public String listCustomer(){
		return "basedata/listCustomer";
	}
	
	/**
	 * ��ѯ�ͻ���Ϣ
	 * @return
	 */
	@RequestMapping("listData")
	@ResponseBody
	public String listData(TBaseCustomer customer, Integer page, Integer rows){
		Page<TBaseCustomer> result = tBaseCustomerService.getPageResult(customer, page, rows);
		return BaseService.List2Json(result.getRows(), result.getTotal());
	}
	
	/**
	 * �޸Ŀͻ�����
	 * @return
	 */
	@RequestMapping("modCustomer")
	public String modCustomer(HttpServletRequest request, Integer id){
		TBaseCustomer customer = tBaseCustomerService.getTBaseCustomer(id);
		request.setAttribute("customer", customer);
		return "basedata/modCustomer";
	}
	
	/**
	 * ���¿ͻ�����
	 * @return
	 */
	@RequestMapping("updateCustomer")
	@ResponseBody
	public String updateCustomer(){
		return null;
	}
	
}
