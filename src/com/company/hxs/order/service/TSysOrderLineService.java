package com.company.hxs.order.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.hxs.common.service.BaseService;
import com.company.hxs.order.dao.TSysOrderLineDao;
import com.company.hxs.order.entity.TSysOrderLine;

@Service
public class TSysOrderLineService extends BaseService{

	@Resource private TSysOrderLineDao tsysSysOrderLineDao;
	
	/**
	 * 获取桥架订单记录
	 * @param id
	 * @return
	 */
	public TSysOrderLine getOrderLine(Integer id) {
		return tsysSysOrderLineDao.get(TSysOrderLine.class, id);
	}

	/**
	 * 保存桥架订单
	 * @param line
	 */
	@Transactional
	public void saveUpdate(TSysOrderLine line) {
		tsysSysOrderLineDao.saveOrUpdate(line);
	}

}
