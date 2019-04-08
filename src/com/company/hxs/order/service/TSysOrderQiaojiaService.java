package com.company.hxs.order.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.hxs.common.service.BaseService;
import com.company.hxs.order.dao.TSysOrderQiaojiaDao;
import com.company.hxs.order.entity.TSysOrderQiaojia;

@Service
public class TSysOrderQiaojiaService extends BaseService{

	@Resource private TSysOrderQiaojiaDao tOrderQiaojiaDao;
	
	/**
	 * �����żܶ�����Ϣ
	 * @param qj
	 */
	@Transactional
	public void saveOrderQiaojia(TSysOrderQiaojia qj) {
		tOrderQiaojiaDao.saveOrUpdate(qj);
	}

	/**
	 * ��ȡ�żܶ�����Ϣ
	 * @param id
	 * @return
	 */
	public TSysOrderQiaojia getOrderQj(Integer id) {
		return tOrderQiaojiaDao.get(TSysOrderQiaojia.class, id);
	}

}
