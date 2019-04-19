package com.company.hxs.sys.menu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.company.hxs.common.service.BaseService;
import com.company.hxs.login.entity.TSysUser;
import com.company.hxs.sys.menu.dao.TSysMenuDao;
import com.company.hxs.sys.menu.vo.TSysMenuVO;

@Service
public class TSysMenuService extends BaseService{

	@Resource private TSysMenuDao tSysMenuDao; 
	
	public List<TSysMenuVO> getMenuList(TSysUser user) {
		StringBuffer pSql = new StringBuffer("select t.menuId menuid,t.menuName menuname,t.icon,t.url from t_sys_menu t where t.parentId is null ");
			pSql.append("and EXISTS (select 1 from t_sys_role_menu rm, t_sys_role r,t_sys_role_ref ref where rm.roleId = r.id and ref.roleId = r.id and find_in_set(t.menuId,rm.menuId) and ref.userId = ?)");
			pSql.append("  order by t.orders");
		List<TSysMenuVO> parentList = sqlCommonDao.findListBySqlAsAliasToBean2(pSql.toString(), TSysMenuVO.class,new Object[]{user.getId()});
		for(TSysMenuVO vo : parentList){
			getChildrenMenu(vo, user.getId());
		}
		return parentList;
	}

	private void getChildrenMenu(TSysMenuVO vo, Integer userId) {
		StringBuffer cSql = new StringBuffer("select t.menuId menuid,t.menuName menuname,t.icon,t.url from t_sys_menu t where t.parentId = ? ");
			cSql.append(" and EXISTS (select 1 from t_sys_role_menu rm, t_sys_role r,t_sys_role_ref ref where rm.roleId = r.id and ref.roleId = r.id and find_in_set(t.menuId,rm.menuId) and ref.userId = ?)");
			cSql.append(" order by t.orders");
		List<TSysMenuVO> childrenList = sqlCommonDao.findListBySqlAsAliasToBean2(cSql.toString(), TSysMenuVO.class, new Object[]{vo.getMenuid(), userId});
		vo.setMenus(childrenList);
		for(TSysMenuVO co : childrenList){
			getChildrenMenu(co, userId);
		}
	}

}
