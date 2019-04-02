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

import com.company.hxs.basedata.entity.TBaseQiaojia;
import com.company.hxs.basedata.service.TBaseQiaoJiaService;
import com.company.hxs.common.Page;
import com.company.hxs.common.controller.BaseController;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.util.ResponseTool;
import com.company.hxs.common.vo.SelectVO;

@Controller
@RequestMapping("baseqj")
public class TBaseQiaojiaController extends BaseController{
	
	@Resource TBaseQiaoJiaService tBaseQiaoJiaService;
	
	/**
	 * �ż��б�ҳ��
	 * @return
	 */
	@RequestMapping("list")
	public String getQjPage(HttpServletRequest request){
		
		return "basedata/list";
	}
	
	/**
	 * ��ȡ�ż������б�
	 * @return
	 */
	@RequestMapping("listData")
	@ResponseBody
	public String getQjListData(HttpServletRequest request, TBaseQiaojia qiaojia, Integer page, Integer rows){
		Page<TBaseQiaojia> pages = tBaseQiaoJiaService.getPageInfoList(qiaojia, page , rows);
		return BaseService.List2Json(pages.getRows(), pages.getTotal()).toString();
	}
	
	/**
	 * �޸��ż�
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("modBaseQj")
	public String modBaseQj(HttpServletRequest request, Integer id){
		if(id != null){
			TBaseQiaojia qj = tBaseQiaoJiaService.getTBaseQiaoJiaById(id);
			request.setAttribute("qj", qj);
		}
		return "basedata/modBaseQj";
	}
	
	@RequestMapping("updateBaseQj")
	@ResponseBody
	public void updateBaseQj(HttpServletResponse response, TBaseQiaojia qiaojia){
		JSONObject js = new JSONObject();
		try{
			tBaseQiaoJiaService.updateTBaseQj(qiaojia);
			js = createResult(true, "�����ɹ�");
		}catch(Exception e){
			e.printStackTrace();
			js = createResult(false, "������Ϣ��" + e.getMessage());
		}
		ResponseTool.write(response, js);
	}
	
	/**
	 * �żܻ�������
	 * @return
	 */
	@RequestMapping("getQjSelect")
	@ResponseBody
	public String getQjSelect(Integer type, Integer mType){
		List<SelectVO> list = tBaseQiaoJiaService.getQjSelect(type, mType);
		return JSONArray.fromObject(list).toString();
	}
	
	/**
	 * ɾ���ż�
	 * @param response
	 * @param id
	 */
	@RequestMapping("delBaseQj")
	@ResponseBody
	public void delBaseQj(HttpServletResponse response, Integer id){
		JSONObject js = new JSONObject();
		try{
			tBaseQiaoJiaService.delBaseQj(id);
			js = createResult(true, "ɾ���ɹ�");
		}catch(Exception e){
			e.printStackTrace();
			js = createResult(false, "ɾ��ʧ�ܣ�" + e.getMessage());
		}
		ResponseTool.write(response, js);
	}
}
