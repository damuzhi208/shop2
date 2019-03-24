package com.company.hxs.basedata.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.company.hxs.basedata.dao.TBaseLineTubeDao;
import com.company.hxs.basedata.entity.TBaseLineTube;
import com.company.hxs.common.Page;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.util.CTools;

@Service
public class TBaseLineTubeService extends BaseService{

	@Resource private TBaseLineTubeDao tBaseLineTubeDao;
	
	public Page<TBaseLineTube> getPageResult(TBaseLineTube lineTube, Integer page, Integer rows) {
		StringBuffer sql = new StringBuffer("select * from t_base_linetube t where 1 = 1");
		List<Object> params = new ArrayList<Object>();
		if(lineTube != null){
			if(CTools.isNotEmpty(lineTube.getmType())){
				sql.append(" and t.mType = ? ");
				params.add(lineTube.getmType());
			}
		}
		List<TBaseLineTube> list = sqlCommonDao.findListBySqlAsAliasToBean2(sql.toString(), TBaseLineTube.class, params.toArray(), page, rows);
		Integer total = tBaseLineTubeDao.sqlGetCount("select count(1) from ("+ sql.toString() + ") o", params.toArray());
		return Page.create(list, total);
	}

}
