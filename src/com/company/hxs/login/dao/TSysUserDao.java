package com.company.hxs.login.dao;

import org.springframework.stereotype.Repository;

import com.company.hxs.common.base.BaseDao;
import com.company.hxs.common.base.IBaseDao;
import com.company.hxs.login.entity.TSysUser;

@Repository
public class TSysUserDao extends BaseDao<TSysUser, String> implements IBaseDao<TSysUser, String>{

}
