package com.company.hxs.order.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.hxs.common.Page;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.order.dao.TSysOrderOtherDao;
import com.company.hxs.order.entity.TSysOrderOther;

@Service
public class TSysOrderOtherService extends BaseService{

	@Resource private TSysOrderOtherDao tSysOrderOtherDao;
	
	/**
	 * ��ѯ������������
	 * @param other
	 * @param page
	 * @param rows
	 * @return
	 */
	public Page<TSysOrderOther> getPageResult(TSysOrderOther other, Integer page, Integer rows) {
		StringBuffer sql = new StringBuffer("select t.id,t.orderId,t.guige,t.unit,u.`name` danweis,t.orderNums,t.costPrice,t.salePrice,t.profit,t.orderNums*t.salePrice liushui ");
			sql.append(" from t_sys_order_other t,t_base_unit u where t.unit = u.id");
		List<Object> params = new ArrayList<Object>();
		if(other != null){
			sql.append(" and t.orderId = ?");
			if(other.getOrderId() != null){
				params.add(other.getOrderId());
			}else{
				params.add(0);
			}
		}
		Page<TSysOrderOther> result = sqlCommonDao.sqlFindPage(sql.toString(), TSysOrderOther.class, params.toArray(), page, rows);
		return result;
	}

	/**
	 * ����������ȡ����������¼
	 * @param id
	 * @return
	 */
	public TSysOrderOther getOther(Integer id) {
		return tSysOrderOtherDao.get(TSysOrderOther.class, id);
	}

	/**
	 * ������������
	 * @param other
	 */
	@Transactional
	public void updateOther(TSysOrderOther other){
		tSysOrderOtherDao.saveOrUpdate(other);
	}

	/**
	 * ɾ��
	 * @param id
	 */
	@Transactional
	public void delete(Integer id) {
		TSysOrderOther other = tSysOrderOtherDao.get(TSysOrderOther.class, id);
		if(other != null){
			tSysOrderOtherDao.delete(other);
		}
	}
}
