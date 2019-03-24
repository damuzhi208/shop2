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
		String pSql = "select t.menuId menuid,t.menuName menuname,t.icon,t.url from t_sys_menu t where t.parentId is null order by t.order";
		List<TSysMenuVO> parentList = sqlCommonDao.findListBySqlAsAliasToBean2(pSql, TSysMenuVO.class);
		for(TSysMenuVO vo : parentList){
			getChildrenMenu(vo);
		}
		return parentList;
	}

	private void getChildrenMenu(TSysMenuVO vo) {
		String cSql = "select t.menuId menuid,t.menuName menuname,t.icon,t.url from t_sys_menu t where t.parentId = ? order by t.order";
		List<TSysMenuVO> childrenList = sqlCommonDao.findListBySqlAsAliasToBean2(cSql, TSysMenuVO.class, new Object[]{vo.getMenuid()});
		vo.setMenus(childrenList);
		for(TSysMenuVO co : childrenList){
			getChildrenMenu(co);
		}
	}

}
