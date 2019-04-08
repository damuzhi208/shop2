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

import com.company.hxs.basedata.entity.TBaseLineTube;
import com.company.hxs.basedata.service.TBaseLineTubeService;
import com.company.hxs.common.Page;
import com.company.hxs.common.controller.BaseController;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.util.ResponseTool;
import com.company.hxs.common.vo.SelectVO;

@Controller
@RequestMapping("lineTube")
public class TBaseLineTubeController extends BaseController{
	
	@Resource private TBaseLineTubeService tBaseLineTubeService;
	
	@RequestMapping("page")
	public String listLineTube(HttpServletRequest request){
		return "basedata/lineTube";
	}
	
	/**
	 * 查询线管数据
	 * @return
	 */
	@RequestMapping("listData")
	@ResponseBody
	public String lineTubeData(TBaseLineTube lineTube, Integer page, Integer rows){
		Page<TBaseLineTube> list = tBaseLineTubeService.getPageResult(lineTube, page, rows);
		return BaseService.List2Json(list.getRows(), list.getTotal()).toString();
	}
	
	/**
	 * 修改
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("modLineTube")
	public String modLineTube(HttpServletRequest request, Integer id){
		if(id != null){
			TBaseLineTube lineTube = tBaseLineTubeService.getLineTube(id);
			request.setAttribute("line", lineTube);
		}
		return "basedata/modLineTube";
	}
	
	/**
	 * 更新
	 * @param lineTube
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public void update(HttpServletResponse response, TBaseLineTube lineTube){
		JSONObject js = new JSONObject();
		try{
			tBaseLineTubeService.saveLineTube(lineTube);
			js = createResult(true, "保存成功");
		}catch(Exception e){
			js = createResult(false, "保存失败："+e.getMessage());
		}
		ResponseTool.write(response, js);
	}
	
	/**
	 * 删除软管
	 * @param response
	 * @param id
	 */
	@RequestMapping("delLineTube")
	@ResponseBody
	public void delLineTube(HttpServletResponse response, Integer id){
		JSONObject js = new JSONObject();
		try{
			tBaseLineTubeService.delLineTube(id);
			js = createResult(true, "删除成功");
		}catch(Exception e){
			js = createResult(false, "删除失败："+e.getMessage());
		}
		ResponseTool.write(response, js);
	}
	
	@RequestMapping("select")
	@ResponseBody
	public String getSelect(Integer mType){
		List<SelectVO> voList = tBaseLineTubeService.getSelectList(mType);
		return JSONArray.fromObject(voList).toString();
	}
	
	/**
	 * 获取线管基础信息
	 * @param id
	 * @return
	 */
	@RequestMapping("getBaseLine")
	@ResponseBody
	public String getBaseLine(Integer id){
		TBaseLineTube line = tBaseLineTubeService.getLineTube(id);
		return JSONObject.fromObject(line).toString();
	}
	
}
