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
import com.company.hxs.common.vo.SelectVO;

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
	 * �����������
	 * @param lineTube
	 */
	@Transactional
	public void saveLineTube(TBaseLineTube lineTube) {
		tBaseLineTubeDao.saveOrUpdate(lineTube);
	}

	/**
	 * ɾ�����
	 * @param id
	 */
	@Transactional
	public void delLineTube(Integer id) {
		TBaseLineTube line = tBaseLineTubeDao.get(TBaseLineTube.class, id);
		if(line != null){
			tBaseLineTubeDao.delete(line);
		}
	}

	/**
	 * ��ѯ��������
	 * @return
	 */
	public List<SelectVO> getSelectList() {
		String sql = "select cast(l.id as char) id,CONCAT('��',case when l.mType = '1' then '�������' when l.mType = '2' then '�߹�' else 'δ֪' end,'����',l.guige,'����λ[',b.`name`,']����[',l.danjia,']') name "
				+ " from t_base_linetube l,t_base_unit b where l.danwei = b.id";
		return sqlCommonDao.findListBySqlAsAliasToBean2(sql, SelectVO.class);
	}

}
