package com.company.hxs.order.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.hxs.common.Page;
import com.company.hxs.common.controller.BaseController;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.order.service.TKucunQiaojiaService;
import com.company.hxs.order.vo.TKucunLineVo;
import com.company.hxs.order.vo.TKucunQiaojiaVo;

/**
 * ���
 * @author luqi
 *
 */
@Controller
@RequestMapping("kucun")
public class TSysKuCunController extends BaseController{
	
	@Resource private TKucunQiaojiaService tKucunQiaojiaService;
	
	/**
	 * �żܿ��
	 * @return
	 */
	@RequestMapping("qiaojia")
	public String qiaojia(HttpServletRequest request, Integer type, Integer mType){
		request.setAttribute("type", type);
		request.setAttribute("mType", mType);
		return "kucun/qiaojia";
	}
	
	/**
	 * �żܿ������
	 * @param vo
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("qjData")
	@ResponseBody
	public String getQiaojia(TKucunQiaojiaVo vo, Integer page, Integer rows){
		Page<TKucunQiaojiaVo> result = tKucunQiaojiaService.getKucunQiaojia(vo, page, rows);
		return BaseService.List2Json(result.getRows(), result.getTotal());
	}
	
	/**
	 * �żܿ��
	 * @return
	 */
	@RequestMapping("line")
	public String line(HttpServletRequest request, Integer mType){
		request.setAttribute("mType", mType);
		return "kucun/line";
	}
	
	/**
	 * �żܿ������
	 * @param vo
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("lineData")
	@ResponseBody
	public String lineData(TKucunLineVo vo, Integer page, Integer rows){
		Page<TKucunLineVo> result = tKucunQiaojiaService.getKucunLine(vo, page, rows);
		return BaseService.List2Json(result.getRows(), result.getTotal());
	}
}
