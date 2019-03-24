package com.company.hxs.basedata.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.company.hxs.basedata.dao.TBaseCustomerDao;
import com.company.hxs.basedata.entity.TBaseCustomer;
import com.company.hxs.common.Page;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.util.CTools;

@Service
public class TBaseCustomerService extends BaseService{

	@Resource private TBaseCustomerDao tBaseCustomerDao;
	
	public Page<TBaseCustomer> getPageResult(TBaseCustomer customer, Integer page, Integer rows) {
		StringBuffer sql = new StringBuffer("from TBaseCustomer where 1 = 1 ");
		List<Object> params = new ArrayList<Object>();
		if(customer != null){
			if(CTools.isNotEmpty(customer.getName())){
				sql.append(" and name like ?");
				params.add("%" + customer.getName() + "%");
			}
			if(CTools.isNotEmpty(customer.getCompanyName())){
				sql.append(" and companyName like ? ");
				params.add("%" + customer.getCompanyName() + "%");
			}
		}
		List<TBaseCustomer> list = tBaseCustomerDao.findByHql(sql.toString(), params.toArray(), page, rows);
		Integer total = tBaseCustomerDao.findCountByHql(sql.toString(), params.toArray());
		return Page.create(list, total);
	}

	public TBaseCustomer getTBaseCustomer(Integer id) {
		return tBaseCustomerDao.get(TBaseCustomer.class, id);
	}

}
