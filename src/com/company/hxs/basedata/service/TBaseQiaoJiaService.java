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
import com.company.hxs.common.vo.SelectVO;

@Service
public class TBaseQiaoJiaService extends BaseService {

	@Resource TBaseQiaojiaDao tBaseQiaojiaDao;
	
	public List<TBaseQiaojia> getQjListData(TBaseQiaojia qiaojia, Integer page, Integer rows) {
		StringBuffer hql = new StringBuffer("from TBaseQiaojia where 1 = 1 ");
		List<Object> params = new ArrayList<Object>();
		if(qiaojia != null){
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

	public List<SelectVO> getQjSelect(Integer type, Integer mType) {
		StringBuffer sql = new StringBuffer("");
		String mterial = mType == 1 ? "����" : "��п";
		if(type == 1){//�ż�
			sql.append("select cast(t.id as char) id,CONCAT('"+mterial+"','�żܡ���*��(',t.widths,'*',t.heights,')�����[',IFNULL(t.houdu,0),']ϵ��[',IFNULL(t.xishu,0),']��λ��[',IFNULL(t.dwj,0),']����[',IFNULL(t.danjia, 0),']') name from t_base_qiaojia t where 1 = 1");
		}else if(type == 2){
			sql.append("select cast(t.id as char) id,CONCAT('"+mterial+"','�ǰ塾��(',t.widths,')�����[',IFNULL(t.houdu,0),']ϵ��[',IFNULL(t.xishu,0),']��λ��[',IFNULL(t.dwj,0),']����[',IFNULL(t.danjia, 0),']') name from t_base_qiaojia t where 1 = 1");
		}
		sql.append(" and t.type = ? and t.mType = ?");
		return tBaseQiaojiaDao.findListBySqlAsAliasToBean2(sql.toString(), SelectVO.class, new Object[]{type, mType});
	}

	/**
	 * ɾ���żܻ�����Ϣ
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
