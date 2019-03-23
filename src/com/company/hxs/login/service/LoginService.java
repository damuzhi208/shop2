package com.company.hxs.login.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.company.hxs.common.service.BaseService;
import com.company.hxs.login.dao.TSysUserDao;
import com.company.hxs.login.entity.TSysUser;

@Service
public class LoginService extends BaseService{

	@Resource private TSysUserDao tSysUserDao;
	
	public TSysUser getTSysUser(TSysUser tSysUser) {
		return tSysUserDao.findUniqueBeanBySql("select * from t_sys_user where account = ? and passWord = ?",new Object[]{tSysUser.getAccount(), tSysUser.getPassWord()});
	}

	public List<TSysUser> getTSysUserList(){
		return tSysUserDao.findByHql("from TSysUser");
	}
	
}
