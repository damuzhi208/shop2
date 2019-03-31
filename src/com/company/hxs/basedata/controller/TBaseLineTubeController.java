package com.company.hxs.basedata.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public String modLineTube(HttpServletRequest request, Integer id){
		if(id != null){
			TBaseLineTube lineTube = tBaseLineTubeService.getLineTube(id);
			request.setAttribute("line", lineTube);
		}
		return "basedata/modLineTube";
	}
	
	/**
	 * ����
	 * @param lineTube
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public void update(HttpServletResponse response, TBaseLineTube lineTube){
		JSONObject js = new JSONObject();
		try{
			tBaseLineTubeService.saveLineTube(lineTube);
			js = createResult(true, "����ɹ�");
		}catch(Exception e){
			js = createResult(false, "����ʧ�ܣ�"+e.getMessage());
		}
		ResponseTool.write(response, js);
	}
	
	/**
	 * ɾ�����
	 * @param response
	 * @param id
	 */
	@RequestMapping("delLineTube")
	@ResponseBody
	public void delLineTube(HttpServletResponse response, Integer id){
		JSONObject js = new JSONObject();
		try{
			tBaseLineTubeService.delLineTube(id);
			js = createResult(true, "ɾ���ɹ�");
		}catch(Exception e){
			js = createResult(false, "ɾ��ʧ�ܣ�"+e.getMessage());
		}
		ResponseTool.write(response, js);
	}
	
}
