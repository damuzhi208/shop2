package com.company.hxs.sys.menu.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.hxs.common.Page;
import com.company.hxs.common.controller.BaseController;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.util.ResponseTool;
import com.company.hxs.common.vo.SelectVO;
import com.company.hxs.sys.menu.entity.TSysRole;
import com.company.hxs.sys.menu.service.TSysRoleService;

@Controller
@RequestMapping("role")
public class TSysRoleController extends BaseController{
	
	@Resource private TSysRoleService tSysRoleService;
	
	@RequestMapping("list")
	public String list(){
		return "sys/role/listRole";
	}
	
	/**
	 * ��ɫ�б�����
	 * @return
	 */
	@RequestMapping("listData")
	@ResponseBody
	public String listData(TSysRole role, Integer page, Integer rows){
		Page<TSysRole> result = tSysRoleService.getPageResult(role, page, rows);
		return BaseService.List2Json(result.getRows(), result.getTotal());
	}
	
	/**
	 * �޸Ľ�ɫ
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("modRole")
	public String modRole(HttpServletRequest request, Integer id){
		if(id != null){
			TSysRole role = tSysRoleService.getTSysRole(id);
			request.setAttribute("role", role);
		}
		return "sys/role/modRole";
	}
	
	/**
	 * ���½�ɫ
	 * @param response
	 * @param role
	 * @return
	 */
	@RequestMapping("updateRole")
	public void updateRole(HttpServletResponse response, TSysRole role){
		JSONObject js = new JSONObject();
		try{
			tSysRoleService.updateRole(role);
			js = createResult(true, "����ɹ�");
		}catch(Exception e){
			js = createResult(false, "����ʧ�ܣ�"+e.getMessage());
		}
		ResponseTool.write(response, js);
	}
	
	/**
	 * ɾ��
	 * @param response
	 * @param id
	 */
	@RequestMapping("deleRole")
	public void delRole(HttpServletResponse response, Integer id){
		JSONObject js = new JSONObject();
		try{
			tSysRoleService.deleteRole(id);
			js = createResult(true, "ɾ���ɹ�");
		}catch(Exception e){
			js = createResult(false, "ɾ��ʧ�ܣ�"+e.getMessage());
		}
		ResponseTool.write(response, js);
	}
	
	@RequestMapping("select")
	@ResponseBody
	public String select(){
		List<SelectVO> voList = tSysRoleService.getSelectVO();
		return JSONArray.fromObject(voList).toString();
	}
	
	/**
	 * Ȩ�޹���
	 * @return
	 */
	@RequestMapping("manage")
	public String manage(){
		return "sys/role/manage";
	}
	
}
