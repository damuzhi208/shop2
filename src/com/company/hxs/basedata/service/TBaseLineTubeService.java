package com.company.hxs.basedata.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.hxs.basedata.dao.TBaseLineTubeDao;
import com.company.hxs.basedata.entity.TBaseLineTube;
import com.company.hxs.common.Page;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.util.CTools;

@Service
public class TBaseLineTubeService extends BaseService{

	@Resource private TBaseLineTubeDao tBaseLineTubeDao;
	
	public Page<TBaseLineTube> getPageResult(TBaseLineTube lineTube, Integer page, Integer rows) {
		StringBuffer sql = new StringBuffer("select t.id,t.guige,t.danjia,t.mType,b.`name` danweiStr from t_base_linetube t,t_base_unit b where t.danwei = b.id");
		List<Object> params = new ArrayList<Object>();
		if(lineTube != null){
			if(CTools.isNotEmpty(lineTube.getmType())){
				sql.append(" and t.mType = ? ");
				params.add(lineTube.getmType());
			}
		}
		sql.append(" order by t.id");
		List<TBaseLineTube> list = sqlCommonDao.findListBySqlAsAliasToBean2(sql.toString(), TBaseLineTube.class, params.toArray(), page, rows);
		Integer total = tBaseLineTubeDao.sqlGetCount("select count(1) from ("+ sql.toString() + ") o", params.toArray());
		return Page.create(list, total);
	}

	public TBaseLineTube getLineTube(Integer id) {
		return tBaseLineTubeDao.get(TBaseLineTube.class, id);
	}

	/**
	 * 保存或更新软管
	 * @param lineTube
	 */
	@Transactional
	public void saveLineTube(TBaseLineTube lineTube) {
		tBaseLineTubeDao.saveOrUpdate(lineTube);
	}

	/**
	 * 删除软管
	 * @param id
	 */
	@Transactional
	public void delLineTube(Integer id) {
		TBaseLineTube line = tBaseLineTubeDao.get(TBaseLineTube.class, id);
		if(line != null){
			tBaseLineTubeDao.delete(line);
		}
	}

}
