package com.company.hxs.basedata.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.hxs.basedata.dao.TBaseQiaojiaDao;
import com.company.hxs.basedata.entity.TBaseQiaojia;
import com.company.hxs.common.Page;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.util.CTools;
import com.company.hxs.common.vo.SelectVO;

@Service
public class TBaseQiaoJiaService extends BaseService {

	@Resource TBaseQiaojiaDao tBaseQiaojiaDao;
	
	public List<TBaseQiaojia> getQjListData(TBaseQiaojia qiaojia, Integer page, Integer rows) {
		StringBuffer hql = new StringBuffer("from TBaseQiaojia where 1 = 1 ");
		List<Object> params = new ArrayList<Object>();
		if(qiaojia != null){
			if(CTools.isNotEmpty(qiaojia.getGuige())){
				hql.append(" and guige like ? ");
				params.add("%" + qiaojia.getGuige() + "%");
			}
			if(qiaojia.getmType() != null){
				hql.append(" and mType = ? ");
				params.add(qiaojia.getmType());
			}
			if(qiaojia.getType() != null){
				hql.append(" and type = ? ");
				params.add(qiaojia.getType());
			}
		}
		return tBaseQiaojiaDao.findByHql(hql.toString(), params.toArray(), page, rows);
	}

	public Page<TBaseQiaojia> getPageInfoList(TBaseQiaojia qiaojia, Integer page, Integer rows) {
		StringBuffer hql = new StringBuffer("from TBaseQiaojia where 1 = 1 ");
		List<Object> params = new ArrayList<Object>();
		if(qiaojia != null){
			if(CTools.isNotEmpty(qiaojia.getGuige())){
				hql.append(" and guige like ? ");
				params.add("%" + qiaojia.getGuige() + "%");
			}
			if(qiaojia.getmType() != null){
				hql.append(" and mType = ? ");
				params.add(qiaojia.getmType());
			}
			if(qiaojia.getType() != null){
				hql.append(" and type = ? ");
				params.add(qiaojia.getType());
			}
		}
		List<TBaseQiaojia> list = tBaseQiaojiaDao.findByHql(hql.toString(), params.toArray(), page, rows);
		Integer total = tBaseQiaojiaDao.findCountByHql(hql.toString(), params.toArray());
		return Page.create(list, total);
	}
	
	public TBaseQiaojia getTBaseQiaoJiaById(Integer id) {
		return tBaseQiaojiaDao.get(TBaseQiaojia.class, id);
	}

	@Transactional
	public void updateTBaseQj(TBaseQiaojia qiaojia) {
		tBaseQiaojiaDao.saveOrUpdate(qiaojia);
	}

	public List<SelectVO> getQjSelect() {
		String sql = "select cast(t.id as char) id,CONCAT('【',t.guige,'】厚度[',IFNULL(t.houdu,0),']系数[',IFNULL(t.xishu,0),']吨位价[',IFNULL(t.dwj,0),']单价[',IFNULL(t.danjia, 0),']') name from t_base_qiaojia t where t.type = 1";
		return tBaseQiaojiaDao.findListBySqlAsAliasToBean2(sql, SelectVO.class);
	}

	/**
	 * 删除桥架基础信息
	 * @param id
	 */
	@Transactional
	public void delBaseQj(Integer id) {
		TBaseQiaojia bqj = tBaseQiaojiaDao.get(TBaseQiaojia.class, id);
		if(bqj != null){
			tBaseQiaojiaDao.delete(bqj);
		}
	}

}
