package com.company.hxs.sys.menu.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.hxs.common.Page;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.vo.SelectVO;
import com.company.hxs.sys.menu.dao.TSysRoleDao;
import com.company.hxs.sys.menu.entity.TSysRole;

@Service
public class TSysRoleService extends BaseService{

	@Resource private TSysRoleDao tSysRoleDao;
	
	/**
	 * 查询角色列表
	 * @param role
	 * @param page
	 * @param rows
	 * @return
	 */
	public Page<TSysRole> getPageResult(TSysRole role, Integer page, Integer rows) {
		StringBuffer sql = new StringBuffer("select * from t_sys_role t where 1 = 1 ");
		List<Object> params = new ArrayList<Object>();
		if(role != null){
			if(role.getState() != null){
				sql.append(" and t.state = ?");
				params.add(role.getState());
			}
		}
		List<TSysRole> list = tSysRoleDao.findListBySqlAsAliasToBean2(sql.toString(), TSysRole.class, params.toArray(), page, rows);
		Integer total = tSysRoleDao.sqlGetCount("select count(1) from (" + sql.toString() + ")o", params.toArray());
		return Page.create(list, total);
	}

	/**
	 * 获取角色记录
	 * @param id
	 * @return
	 */
	public TSysRole getTSysRole(Integer id) {
		return tSysRoleDao.get(TSysRole.class, id);
	}

	/**
	 * 更新角色
	 * @param role
	 */
	@Transactional
	public void updateRole(TSysRole role) {
		tSysRoleDao.saveOrUpdate(role);
	}

	public List<SelectVO> getSelectVO() {
		List<SelectVO> list = new ArrayList<SelectVO>();
		SelectVO v1 = new SelectVO();
		v1.setId("1");
		v1.setName("有效");
		SelectVO v2 = new SelectVO();
		v2.setId("0");
		v2.setName("无效");
		list.add(v1);
		list.add(v2);
		return list;
	}

	/**
	 * 刪除角色
	 * @param id
	 */
	@Transactional
	public void deleteRole(Integer id) {
		TSysRole role = tSysRoleDao.get(TSysRole.class, id);
		if(role != null){
			tSysRoleDao.delete(role);
		}
	}

}
