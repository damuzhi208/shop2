package com.company.hxs.sys.transfer.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.company.hxs.common.Page;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.util.CTools;
import com.company.hxs.sys.transfer.dao.TTransferRecordsDao;
import com.company.hxs.sys.transfer.entity.TTransferRecords;
import com.company.hxs.sys.transfer.vo.TTransferRecordsVO;

@Service
public class TTransferRecordsService extends BaseService{

	@Resource private TTransferRecordsDao tTransferRecordsDao;
	
	/**
	 * 查询调动记录
	 * @param vo
	 * @param page
	 * @param rows
	 * @return
	 */
	public Page<TTransferRecordsVO> getPageResult(TTransferRecordsVO vo, Integer page, Integer rows) {
		StringBuffer sql = new StringBuffer("select r.id,r.shopName,r.nums,r.unit,r.cost,r.salePrice,r.transType,r.transDate,r.createTime,c.`name` customerName,c.companyName,c.telephone ");
			sql.append(" from t_transfer_records r,t_base_customer c where r.customerId = c.id");
		List<Object> params = new ArrayList<Object>();
		if(vo != null){
			if(CTools.isNotEmpty(vo.getCustomerName())){
				sql.append(" and c.name like ?");
				params.add("%" + vo.getCustomerName() + "%");
			}
			if(vo.getTransType() != null){
				sql.append(" and r.transType = ?");
				params.add(vo.getTransType());
			}
		}
		List<TTransferRecordsVO> voList = sqlCommonDao.findListBySqlAsAliasToBean2(sql.toString(), TTransferRecordsVO.class, params.toArray(), page, rows);
		Integer total = tTransferRecordsDao.sqlGetCount("select count(1) from ("+sql.toString()+") o", params.toArray());
		return Page.create(voList, total);
	}

	/**
	 * 根据主键id获取记录信息
	 * @param id
	 * @return
	 */
	public TTransferRecords getTransRecord(Integer id) {
		return tTransferRecordsDao.get(TTransferRecords.class, id);
	}

}
