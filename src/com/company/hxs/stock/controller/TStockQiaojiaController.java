package com.company.hxs.stock.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.hxs.common.Page;
import com.company.hxs.common.controller.BaseController;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.stock.service.TStockQiaojiaService;
import com.company.hxs.stock.vo.TStockQiaojiaVO;

@Controller
@RequestMapping("stock")
public class TStockQiaojiaController extends BaseController{
	
	@Resource private TStockQiaojiaService tStockQiaojiaService;
	
	@RequestMapping("stockQiaojiaList")
	public String stockQiaojiaList(HttpServletRequest request){
		return "stock/qiaojia/stockQiaojiaList";
	}
	
	@RequestMapping("qiaojiaData")
	@ResponseBody
	public String stockQiaojiaData(TStockQiaojiaVO stock, Integer page, Integer rows){
		Page<TStockQiaojiaVO> list = tStockQiaojiaService.getPageResult(stock, page, rows);
		return BaseService.List2Json4FullDate(list.getRows(), list.getTotal());
	}
	
}
