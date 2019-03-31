package com.company.hxs.basedata.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.hxs.basedata.dao.TBaseCustomerDao;
import com.company.hxs.basedata.entity.TBaseCustomer;
import com.company.hxs.common.Page;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.util.CTools;
import com.company.hxs.common.vo.SelectVO;

@Service
@Transactional(readOnly= false)
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

	@Transactional
	public void updateTBaseCustomer(TBaseCustomer customer){
		if(customer != null && customer.getId() != null){
			tBaseCustomerDao.update(customer);
		}else{
			tBaseCustomerDao.save(customer);
		}
	}

	/**
	 * 获取下拉顾客信息
	 * @return
	 */
	public List<SelectVO> getSelectList() {
		String sql = "select CAST(t.id AS CHAR) id,t.`name` from t_base_customer t";
		return sqlCommonDao.findListBySqlAsAliasToBean2(sql, SelectVO.class);
	}

	public List<SelectVO> getCusType() {
		SelectVO vo1 = new SelectVO();
		vo1.setId("1");
		vo1.setName("顾客");
		
		SelectVO vo2 = new SelectVO();
		vo2.setId("2");
		vo2.setName("同行");
		List<SelectVO> list = new ArrayList<>();
		list.add(vo1);
		list.add(vo2);
		return list;
	}

	/**
	 * 删除顾客信息
	 * @param id
	 */
	@Transactional
	public void delCustomer(Integer id) {
		TBaseCustomer customer = tBaseCustomerDao.get(TBaseCustomer.class, id);
		if(customer != null){
			tBaseCustomerDao.delete(customer);
		}
	}
}
