package com.company.hxs.login.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@Resource private LoginService loginService;
	
	@RequestMapping("login")
	public String index(HttpServletRequest request, TSysUser tSysUser){
		List<TSysUser> userList = loginService.getTSysUserList();
		System.out.println(userList.size());
		TSysUser user = loginService.getTSysUser(tSysUser);
		System.out.println(user.getUserName()+"=================================="+user.getPassWord());
		return "login";
	}
	
}
