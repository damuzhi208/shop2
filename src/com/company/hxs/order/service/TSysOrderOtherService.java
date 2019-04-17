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
	 * 查询其他订单数据
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
		String sSql = "select '合计' guige,sum(o.liushui) liushui,sum(o.profit) profit from (" + sql.toString() + ") o";
		List<TSysOrderOther> footer = sqlCommonDao.findListBySqlAsAliasToBean2(sSql, TSysOrderOther.class, params.toArray());
		Page<TSysOrderOther> result = sqlCommonDao.sqlFindPage(sql.toString(), TSysOrderOther.class, params.toArray(), page, rows);
		result.setFooter(footer);
		return result;
	}

	/**
	 * 根据主键获取其他订单记录
	 * @param id
	 * @return
	 */
	public TSysOrderOther getOther(Integer id) {
		return tSysOrderOtherDao.get(TSysOrderOther.class, id);
	}

	/**
	 * 更新其他订单
	 * @param other
	 */
	@Transactional
	public void updateOther(TSysOrderOther other){
		tSysOrderOtherDao.saveOrUpdate(other);
	}

	/**
	 * 删除
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
