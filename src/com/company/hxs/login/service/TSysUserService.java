package com.company.hxs.login.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.hxs.common.service.BaseService;
import com.company.hxs.login.dao.TSysUserDao;
import com.company.hxs.login.entity.TSysUser;

@Service
public class TSysUserService extends BaseService{
	
	@Resource private TSysUserDao tSysUserDao;

	/**
	 * ÐÞ¸ÄÃÜÂë
	 * @param user
	 */
	@Transactional
	public void updatePass(TSysUser user) {
		TSysUser u = tSysUserDao.get(TSysUser.class, user.getId());
		if(u != null){
			u.setPassWord(user.getPassWord());
			tSysUserDao.merge(u);
		}
	}

}
