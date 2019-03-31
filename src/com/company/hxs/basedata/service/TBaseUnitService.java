package com.company.hxs.basedata.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.hxs.basedata.dao.TBaseUnitDao;
import com.company.hxs.basedata.entity.TBaseUnit;
import com.company.hxs.common.Page;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.util.CTools;
import com.company.hxs.common.vo.SelectVO;

@Service
public class TBaseUnitService extends BaseService{

	@Resource private TBaseUnitDao tBaseUnitDao;
	
	public Page<TBaseUnit> getPageResult(TBaseUnit unit, Integer page, Integer rows) {
		StringBuffer sql = new StringBuffer("from TBaseUnit where 1 = 1 ");
		List<Object> params = new ArrayList<Object>();
		if(unit != null){
			if(CTools.isNotEmpty(unit.getName())){
				sql.append(" and name like ?");
				params.add("%" + unit.getName() + "%");
			}
		}
		List<TBaseUnit> list = tBaseUnitDao.findByHql(sql.toString(), params.toArray());
		Integer total = tBaseUnitDao.findCountByHql(sql.toString(), params.toArray());
		return Page.create(list, total);
	}

	/**
	 * 获取下拉数据
	 * @return
	 */
	public List<SelectVO> getSelectList() {
		String sql = "select cast(t.id as char) id,t.name from t_base_unit t";
		List<SelectVO> list = sqlCommonDao.findListBySqlAsAliasToBean2(sql, SelectVO.class);
		return list;
	}

	/**
	 * 删除Unit
	 * @param id
	 */
	@Transactional
	public void delUnit(Integer id) {
		TBaseUnit unit = tBaseUnitDao.get(TBaseUnit.class, id);
		if(unit != null){
			tBaseUnitDao.delete(unit);
		}
		
	}

}
