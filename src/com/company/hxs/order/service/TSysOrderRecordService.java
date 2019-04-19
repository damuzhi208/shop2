package com.company.hxs.order.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.hxs.common.Page;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.util.CTools;
import com.company.hxs.order.dao.TSysOrderRecordDao;
import com.company.hxs.order.entity.TSysOrderLine;
import com.company.hxs.order.entity.TSysOrderQiaojia;
import com.company.hxs.order.entity.TSysOrderRecord;
import com.company.hxs.order.vo.TSysRecordVo;

@Service
public class TSysOrderRecordService extends BaseService {

	@Resource private TSysOrderRecordDao tSysOrderRecordDao;
	
	/**
	 * 订单列表数据
	 * @param order
	 * @param page
	 * @param rows
	 * @return
	 */
	public Page<TSysOrderRecord> getPageResult(TSysOrderRecord order, Integer page, Integer rows) {
		StringBuffer sql = new StringBuffer("select t.orderId, b.id customerId,b.`name` customerName, b.companyName, t.orderDate,b.telephone, t.opTime,getLiushuiByOrderId(t.orderId) liushui,getProfitByOrderId(t.orderId) profit ");
			sql.append(" from t_sys_order_record t ,t_base_customer b where t.customerId = b.id");
		List<Object> params = new ArrayList<Object>();
		if(order != null){
			if(CTools.isNotEmpty(order.getCustomerName())){
				sql.append(" and (b.name like ? or b.companyName like ?)");
				params.add("%" + order.getCustomerName() + "%");
				params.add("%" + order.getCustomerName() + "%");
			}
			if(order.getBeginDate() != null){
				sql.append(" and t.orderDate >= ?");
				params.add(order.getBeginDate());
			}
			if(order.getEndDate() != null){
				sql.append(" and t.orderDate <= ?");
				params.add(order.getEndDate());
			}
			if(CTools.isNotEmpty(order.getTelephone())){
				sql.append(" and b.telephone like ?");
				params.add("%" + order.getTelephone() + "%");
			}
		}
		sql.append(" order by t.orderDate desc,b.name");
		List<TSysOrderRecord> list = sqlCommonDao.findListBySqlAsAliasToBean2(sql.toString(), TSysOrderRecord.class, params.toArray(), page, rows);
		Integer total = tSysOrderRecordDao.sqlGetCount("select count(1) from (" + sql.toString() + ")o", params.toArray());
		
		String sSql = "select '合计' customerName,sum(o.liushui) liushui,sum(o.profit) profit from (" + sql.toString() + ")o";
		List<TSysOrderRecord> footer = sqlCommonDao.findListBySqlAsAliasToBean2(sSql, TSysOrderRecord.class, params.toArray(), page, rows);
		return Page.create(list, footer, total);
	}

	/**
	 * 保存订单信息
	 * @param record
	 */
	@Transactional
	public TSysOrderRecord saveOrderRecord(TSysOrderRecord record) {
		record.setOpTime(new Date());
		tSysOrderRecordDao.saveOrUpdate(record);
		return record;
	}

	/**
	 * 获取订单记录
	 * @param orderId
	 * @return
	 */
	public TSysOrderRecord getRecord(Integer orderId) {
		return tSysOrderRecordDao.get(TSysOrderRecord.class, orderId);
	}

	/**
	 * 获取桥架订单数据
	 * @param orderQj
	 * @param page
	 * @param rows
	 * @return
	 */
	public Page<TSysOrderQiaojia> getPageResult(TSysOrderQiaojia orderQj, Integer page, Integer rows) {
		StringBuffer sql = new StringBuffer("select t.id,q.widths,q.heights,q.type,q.mType,t.danjia,t.houdu,t.xishu,t.dwj,t.orderNums,t.salePrice,(t.orderNums*t.salePrice) liushui,t.profit from t_sys_order_qiaojia t,t_base_qiaojia q where t.baseQjId = q.id");
		List<Object> params = new ArrayList<Object>();
		if(orderQj != null){
			if(orderQj.getOrderId() != null){
				sql.append(" and t.orderId = ?");
				params.add(orderQj.getOrderId());
			}else{
				sql.append(" and t.orderId = ?");
				params.add(0);
			}
			if(orderQj.getType() != null){
				sql.append(" and q.type = ?");
				params.add(orderQj.getType());
			}
			if(orderQj.getmType() != null){
				sql.append(" and q.mType = ?");
				params.add(orderQj.getmType());
			}
		}
		List<TSysOrderQiaojia> list = sqlCommonDao.findListBySqlAsAliasToBean2(sql.toString(), TSysOrderQiaojia.class, params.toArray(), page, rows);
		Integer total = tSysOrderRecordDao.sqlGetCount("select count(1) from (" + sql.toString() + ")o", params.toArray());
		
		String sSql = "select sum(o.liushui) liushui, sum(o.profit) profit from ("+sql.toString()+") o";
		List<TSysOrderQiaojia> footer = sqlCommonDao.findListBySqlAsAliasToBean2(sSql.toString(), TSysOrderQiaojia.class, params.toArray());
		return Page.create(list, footer, total);
	}

	/**
	 * 获取线管订单数据
	 * @param line
	 * @param page
	 * @param rows
	 * @return
	 */
	public Page<TSysOrderLine> getLinePageResult(TSysOrderLine line, Integer page, Integer rows) {
		StringBuffer sql = new StringBuffer("select b.guige,o.danjia,b.mType,u.`name` danweis,o.id,o.orderNums,o.salePrice,o.profit,o.orderId,(o.orderNums*o.salePrice) liushui ");
			sql.append("from t_sys_order_line o,t_base_linetube b,t_base_unit u where o.baseLineId = b.id and b.danwei = u.id");
		List<Object> params = new ArrayList<Object>();
		if(line != null){
			if(line.getmType() != null){
				sql.append(" and b.mType = ?");
				params.add(line.getmType());
			}
			if(line.getOrderId() != null){
				sql.append(" and o.orderId = ?");
				params.add(line.getOrderId());
			}else{
				sql.append(" and o.orderId = ?");
				params.add(0);
			}
		}
		List<TSysOrderLine> list = sqlCommonDao.findListBySqlAsAliasToBean2(sql.toString(), TSysOrderLine.class, params.toArray(), page, rows);
		Integer total = sqlCommonDao.sqlGetCount("select count(1) from ("+sql.toString()+") o", params.toArray());
		
		String sSql = "select '合计' guige,sum(o.liushui) liushui, sum(o.profit) profit from (" + sql.toString() + ") o";
		List<TSysOrderLine> footer = sqlCommonDao.findListBySqlAsAliasToBean2(sSql, TSysOrderLine.class, params.toArray());
		return Page.create(list, footer, total);
	}

	/**
	 * 刪除
	 * @param id
	 */
	@Transactional
	public void delete(Integer id) {
		TSysOrderRecord record = tSysOrderRecordDao.get(TSysOrderRecord.class, id);
		if(record != null){
			tSysOrderRecordDao.delete(record);
		}
	}

	public Page<TSysRecordVo> getPageResult(TSysRecordVo vo, Integer page, Integer rows) {
		StringBuffer sql = new StringBuffer("select * from v_shop_order v where 1 = 1 ");
		List<Object> params = new ArrayList<Object>();
		if(vo != null){
			if(vo.getOrderDate() != null){
				sql.append(" and v.orderDate >= ?");
				params.add(vo.getOrderDate());
			}
			if(vo.getEndDate() != null){
				sql.append(" and v.orderDate <= ?");
				params.add(vo.getEndDate());
			}
			if(CTools.isNotEmpty(vo.getShopName())){
				sql.append(" and v.shopName like ?");
				params.add("%" + vo.getShopName() + "%");
			}
			if(CTools.isNotEmpty(vo.getCustomerId())){
				sql.append(" and v.customerId = ?");
				params.add(vo.getCustomerId());
			}
		}
		sql.append(" order by v.orderDate desc");
		List<TSysRecordVo> list = sqlCommonDao.findListBySqlAsAliasToBean2(sql.toString(), TSysRecordVo.class, params.toArray(), page, rows);
		Integer total = sqlCommonDao.sqlGetCount("select count(1) from ("+sql.toString()+")o ", params.toArray());
		//合计
		String sSql = "select '合计' shopName,sum(o.liushui) liushui,sum(o.profit) profit from (" + sql.toString() + ")o";
		List<TSysRecordVo> footer = sqlCommonDao.findListBySqlAsAliasToBean2(sSql, TSysRecordVo.class, params.toArray());
		return Page.create(list, footer, total);
	}

}
