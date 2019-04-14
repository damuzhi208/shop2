package com.company.hxs.login.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.hxs.common.controller.BaseController;
import com.company.hxs.common.sys.SysConstant;
import com.company.hxs.common.util.ResponseTool;
import com.company.hxs.login.entity.TSysUser;
import com.company.hxs.login.service.LoginService;
import com.company.hxs.login.service.TSysUserService;
import com.company.hxs.sys.menu.service.TSysMenuService;
import com.company.hxs.sys.menu.vo.TSysMenuVO;

/**
 * µÇÂ¼
 * @author luqi
 *
 */
@Controller
@RequestMapping("/")
public class LoginController extends BaseController{
	
	@Resource private LoginService loginService;
	
	@Resource private TSysMenuService tSysMenuService;
	
	@Resource private TSysUserService tSysUserService;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginGet(HttpServletRequest request){
		return "login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public String loginPost(HttpServletRequest request, TSysUser tSysUser){
		JSONObject js = new JSONObject();
		try{
			TSysUser user = loginService.getTSysUser(tSysUser);
			if(user == null){
				js = createResult(false, "ÓÃ»§Ãû»òÃÜÂë´íÎó");
				return js.toString();
			}
			HttpSession session = request.getSession(true);
			session.setAttribute(SysConstant.SESSION_USER, user);
			
			List<TSysMenuVO> menus = tSysMenuService.getMenuList(user);
			session.setAttribute(SysConstant.SYS_MENUS, JSONArray.fromObject(menus));
			
			js = createResult(true, null);
		}catch(Exception e){
			js = createResult(false, "µÇÂ¼´íÎó£º" + e.getMessage());
		}
		return js.toString();
	}
	
	@RequestMapping("index")
	public String index(HttpServletRequest request){
		return "index";
	}
	
	/**
	 * ÐÞ¸ÄÃÜÂë
	 * @param response
	 * @param user
	 */
	@RequestMapping("updatePass")
	@ResponseBody
	public void updatePass(HttpServletResponse response, TSysUser user){
		JSONObject js = new JSONObject();
		try{
			tSysUserService.updatePass(user);
			js = createResult(true, "ÐÞ¸Ä³É¹¦");
			js.put(SysConstant.SYS_DATA, user.getPassWord());
		}catch(Exception e){
			js = createResult(false, "ÐÞ¸ÄÊ§°Ü£º" + e.getMessage());
		}
		ResponseTool.write(response, js);
	}
	
}
