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

import com.company.hxs.basedata.entity.TBaseUnit;
import com.company.hxs.basedata.service.TBaseUnitService;
import com.company.hxs.common.Page;
import com.company.hxs.common.controller.BaseController;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.util.ResponseTool;
import com.company.hxs.common.vo.SelectVO;

@Controller
@RequestMapping("baseUnit")
public class TBaseUnitController extends BaseController{
	
	@Resource private TBaseUnitService tBaseUnitService;
	
	/**
	 * 单位页面
	 * @return
	 */
	@RequestMapping("list")
	public String listUnit(){
		return "basedata/listUnit";
	}
	
	/**
	 * 单位数据
	 * @return
	 */
	@RequestMapping("listData")
	@ResponseBody
	public String listData(TBaseUnit unit, Integer page, Integer rows){
		Page<TBaseUnit> list = tBaseUnitService.getPageResult(unit, page, rows);
		return BaseService.List2Json(list.getRows(), list.getTotal());
	}
	
	/**
	 * 修改单位
	 * @return
	 */
	@RequestMapping("modUnit")
	public String modUnit(HttpServletRequest request, Integer id){
		if(id != null){
			TBaseUnit unit = tBaseUnitService.getObject(TBaseUnit.class, id);
			request.setAttribute("unit", unit);
		}
		return "basedata/modUnit";
	}
	
	/**
	 * 更新单位
	 * @return
	 */
	@RequestMapping("updateUnit")
	@ResponseBody
	public void updateUnit(HttpServletResponse response, TBaseUnit unit){
		JSONObject js = new JSONObject();
		try{
			tBaseUnitService.saveOrUpdate(unit);
			js = createResult(true, "保存成功");
		}catch(Exception e){
			js = createResult(false, "保存失败：" + e.getMessage());
		}
		ResponseTool.write(response, js);
	}
	
	@RequestMapping("delUnit")
	@ResponseBody
	public void delUnit(HttpServletResponse response, Integer id){
		JSONObject js = new JSONObject();
		try{
			tBaseUnitService.delUnit(id);
			js = createResult(true, "删除成功");
		}catch(Exception e){
			js = createResult(false, "删除失败：" + e.getMessage());
		}
		ResponseTool.write(response, js);
	}
	
	/**
	 * 获取下拉数据
	 * @return
	 */
	@RequestMapping("select")
	@ResponseBody
	public String getSelect(){
		List<SelectVO> list = tBaseUnitService.getSelectList();
		return JSONArray.fromObject(list).toString();
	}
	
}
