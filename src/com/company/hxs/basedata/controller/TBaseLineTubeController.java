package com.company.hxs.basedata.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.hxs.basedata.entity.TBaseLineTube;
import com.company.hxs.basedata.service.TBaseLineTubeService;
import com.company.hxs.common.Page;
import com.company.hxs.common.controller.BaseController;
import com.company.hxs.common.service.BaseService;

@Controller
@RequestMapping("lineTube")
public class TBaseLineTubeController extends BaseController{
	
	@Resource private TBaseLineTubeService tBaseLineTubeService;
	
	@RequestMapping("page")
	public String listLineTube(HttpServletRequest request){
		return "basedata/lineTube";
	}
	
	/**
	 * ��ѯ�߹�����
	 * @return
	 */
	@RequestMapping("listData")
	@ResponseBody
	public String lineTubeData(TBaseLineTube lineTube, Integer page, Integer rows){
		Page<TBaseLineTube> list = tBaseLineTubeService.getPageResult(lineTube, page, rows);
		return BaseService.List2Json(list.getRows(), list.getTotal()).toString();
	}
	
	/**
	 * �޸�
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("modLineTube")
	public String modLineTube(HttpServletRequest request, String id){
		return "basedata/modLineTube";
	}
	
	/**
	 * ����
	 * @param lineTube
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public String update(TBaseLineTube lineTube){
		return null;
	}
	
}
