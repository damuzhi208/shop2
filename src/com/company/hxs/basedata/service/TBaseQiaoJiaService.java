package com.company.hxs.basedata.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.hxs.basedata.dao.TBaseQiaojiaDao;
import com.company.hxs.basedata.entity.TBaseQiaojia;
import com.company.hxs.common.service.BaseService;

@Service
public class TBaseQiaoJiaService extends BaseService {

	@Resource TBaseQiaojiaDao tBaseQiaojiaDao;
	
	public List<TBaseQiaojia> getQjListData(TBaseQiaojia qiaojia, Integer page, Integer rows) {
		return tBaseQiaojiaDao.findByHql("from TBaseQiaojia", null, page, rows);
	}

	public TBaseQiaojia getTBaseQiaoJiaById(String id) {
		return tBaseQiaojiaDao.get(TBaseQiaojia.class, id);
	}

	@Transactional
	public void updateTBaseQj(TBaseQiaojia qiaojia) {
		//tBaseQiaojiaDao.merge(qiaojia);
		tBaseQiaojiaDao.saveOrUpdate(qiaojia);
	}

}
