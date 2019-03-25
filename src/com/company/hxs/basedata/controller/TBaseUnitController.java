package com.company.hxs.basedata.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.company.hxs.common.vo.SelectVO;

@Controller
@RequestMapping("baseUnit")
public class TBaseUnitController extends BaseController{
	
	@Resource private TBaseUnitService tBaseUnitService;
	
	/**
	 * ��λҳ��
	 * @return
	 */
	@RequestMapping("list")
	public String listUnit(){
		return "basedata/listUnit";
	}
	
	/**
	 * ��λ����
	 * @return
	 */
	@RequestMapping("listData")
	@ResponseBody
	public String listData(TBaseUnit unit, Integer page, Integer rows){
		Page<TBaseUnit> list = tBaseUnitService.getPageResult(unit, page, rows);
		return BaseService.List2Json(list.getRows(), list.getTotal());
	}
	
	/**
	 * �޸ĵ�λ
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
	 * ���µ�λ
	 * @return
	 */
	@RequestMapping("updateUnit")
	@ResponseBody
	public String updateUnit(TBaseUnit unit){
		JSONObject js = new JSONObject();
		try{
			tBaseUnitService.saveOrUpdate(unit);
			js = createResult(true, "����ɹ�");
		}catch(Exception e){
			js = createResult(false, "����ʧ�ܣ�" + e.getMessage());
		}
		return js.toString();
	}
	
	/**
	 * ��ȡ��������
	 * @return
	 */
	@RequestMapping("select")
	@ResponseBody
	public String getSelect(){
		List<SelectVO> list = tBaseUnitService.getSelectList();
		return JSONArray.fromObject(list).toString();
	}
	
}
