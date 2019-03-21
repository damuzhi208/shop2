package com.company.hxs.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.hxs.common.controller.BaseController;

@Controller
@RequestMapping("login")
public class LoginController extends BaseController{
	
	@RequestMapping("index")
	public String index(){
		System.out.println("aa");
		return "test";
	}
	
}
