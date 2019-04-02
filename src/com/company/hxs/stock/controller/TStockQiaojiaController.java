package com.company.hxs.stock.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.hxs.common.Page;
import com.company.hxs.common.controller.BaseController;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.util.ResponseTool;
import com.company.hxs.stock.entity.TStockQiaojia;
import com.company.hxs.stock.service.TStockQiaojiaService;
import com.company.hxs.stock.vo.TStockQiaojiaVO;

@Controller
@RequestMapping("stock")
public class TStockQiaojiaController extends BaseController{
	
	@Resource private TStockQiaojiaService tStockQiaojiaService;
	
	@RequestMapping("stockQiaojiaList")
	public String stockQiaojiaList(HttpServletRequest request, Integer type, Integer mType){
		request.setAttribute("type", type);
		request.setAttribute("mType", mType);
		return "stock/qiaojia/stockQiaojiaList";
	}
	
	@RequestMapping("qiaojiaData")
	@ResponseBody
	public String stockQiaojiaData(TStockQiaojiaVO stock, Integer page, Integer rows){
		Page<TStockQiaojiaVO> list = tStockQiaojiaService.getPageResult(stock, page, rows);
		return BaseService.List2Json4FullDate(list.getRows(), list.getTotal());
	}
	
	/**
	 * 修改桥架库存
	 * @return
	 */
	@RequestMapping("modstockQj")
	public String modstockQj(HttpServletRequest request, Integer id, Integer type, Integer mType){
		if(id != null){
			TStockQiaojia stock = tStockQiaojiaService.getTStockQj(id);
			request.setAttribute("stock", stock);
		}
		request.setAttribute("type", type);
		request.setAttribute("mType", mType);
		return "stock/qiaojia/modStockQiaojia";
	}
	
	/**
	 * 更新库存信息
	 * @param response
	 * @param stock
	 */
	@RequestMapping("updateStockQj")
	@ResponseBody
	public void updateStockQj(HttpServletResponse response,@Validated TStockQiaojia stock){
		JSONObject js = new JSONObject();
		try{
			tStockQiaojiaService.saveOrUpdateTStockQiaojia(stock);
			js = createResult(true, "保存成功");
		}catch(Exception e){
			js = createResult(false, "操作失败：" + e.getMessage());
			e.printStackTrace();
		}
		ResponseTool.write(response, js);
	}
}
