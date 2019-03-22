package com.company.hxs.login.service;

import javax.annotation.Resource;

import com.company.hxs.base.dao.SqlCommonDao;
import com.company.hxs.login.dao.TSysUserDao;
import com.company.hxs.login.entity.TSysUser;

public class LoginService {

	@Resource private SqlCommonDao dao;
	
	@Resource private TSysUserDao tSysUserDao;
	
	public TSysUser getTSysUser(TSysUser tSysUser) {
		return null;
	}

}
