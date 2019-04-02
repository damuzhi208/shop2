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
import com.company.hxs.stock.entity.TStockLineTube;
import com.company.hxs.stock.service.TStockLineTubeService;
import com.company.hxs.stock.vo.TStockLineTubeVO;

@Controller
@RequestMapping("stockLine")
public class TStockLineTubeController extends BaseController{
	
	@Resource private TStockLineTubeService tStockTubeService;
	
	@RequestMapping("lineList")
	public String lineList(HttpServletRequest request, Integer mType){
		request.setAttribute("mType", mType);
		return "stock/line/lineList";
	}
	
	@RequestMapping("lineData")
	@ResponseBody
	public String lineData(TStockLineTubeVO stock, Integer page, Integer rows){
		try{
			
			Page<TStockLineTubeVO> list = tStockTubeService.getPageResult(stock, page, rows);
			return BaseService.List2Json4FullDate(list.getRows(), list.getFooter(), list.getTotal());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 修改线管库存
	 * @return
	 */
	@RequestMapping("modStockLine")
	public String modStockLine(HttpServletRequest request, Integer id){
		if(id != null){
			TStockLineTube line = tStockTubeService.getTStockLineTube(id);
			request.setAttribute("line", line);
		}
		return "stock/line/modStockLine";
	}
	
	/**
	 * 更新库存信息
	 * @param response
	 * @param stock
	 */
	@RequestMapping("updateStockLine")
	@ResponseBody
	public void updateStockLine(HttpServletResponse response,@Validated TStockLineTube line){
		JSONObject js = new JSONObject();
		try{
			tStockTubeService.saveOrUpdateTStockLineTube(line);
			js = createResult(true, "保存成功");
		}catch(Exception e){
			js = createResult(false, "操作失败：" + e.getMessage());
			e.printStackTrace();
		}
		ResponseTool.write(response, js);
	}
	
}
