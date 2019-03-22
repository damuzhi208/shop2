package com.company.hxs.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.hxs.base.dao.SqlCommonDao;
import com.company.hxs.common.controller.BaseController;
import com.company.hxs.login.entity.TSysUser;
import com.company.hxs.login.service.LoginService;

/**
 * µÇÂ¼
 * @author luqi
 *
 */
@Controller
@RequestMapping("/")
public class LoginController extends BaseController{
	
	@Resource private SqlCommonDao dao;
	
	@Resource private LoginService loginService;
	
	@RequestMapping("login")
	public String index(HttpServletRequest request, TSysUser tSysUser){
		TSysUser user = loginService.getTSysUser(tSysUser);
		System.out.println(user.getUserName());
		return "login";
	}
	
}
