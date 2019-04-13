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
	 * »ñÈ¡ÇÅ¼Ü¶©µ¥¼ÇÂ¼
	 * @param id
	 * @return
	 */
	public TSysOrderLine getOrderLine(Integer id) {
		return tsysSysOrderLineDao.get(TSysOrderLine.class, id);
	}

	/**
	 * ±£´æÇÅ¼Ü¶©µ¥
	 * @param line
	 */
	@Transactional
	public void saveUpdate(TSysOrderLine line) {
		tsysSysOrderLineDao.saveOrUpdate(line);
	}

	/**
	 * É¾³ý
	 * @param id
	 */
	@Transactional
	public void delete(Integer id) {
		TSysOrderLine line = tsysSysOrderLineDao.get(TSysOrderLine.class, id);
		if(line != null){
			tsysSysOrderLineDao.delete(line);
		}
	}

}
