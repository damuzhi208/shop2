package com.company.hxs.sys.transfer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.hxs.common.Page;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.util.CTools;
import com.company.hxs.common.vo.SelectVO;
import com.company.hxs.sys.transfer.dao.TTransferRecordsDao;
import com.company.hxs.sys.transfer.entity.TTransferRecords;
import com.company.hxs.sys.transfer.vo.TTransferRecordsVO;

@Service
public class TTransferRecordsService extends BaseService{

	@Resource private TTransferRecordsDao tTransferRecordsDao;
	
	/**
	 * ��ѯ������¼
	 * @param vo
	 * @param page
	 * @param rows
	 * @return
	 */
	public Page<TTransferRecordsVO> getPageResult(TTransferRecordsVO vo, Integer page, Integer rows) {
		StringBuffer sql = new StringBuffer("select r.id,r.shopName,r.nums,r.unit,r.cost,r.salePrice,r.transType,r.transDate,r.createTime,r.profit,c.`name` customerName,c.companyName,c.telephone,b.name unitStr ");
			sql.append(" from t_transfer_records r,t_base_customer c,t_base_unit b where r.customerId = c.id and b.id = r.unit ");
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
		
		String sumSql = "select '�ϼ�' shopName,sum(o.nums) nums,sum(o.profit) profit from (" + sql.toString() + ") o where 1 = 1";
		List<TTransferRecordsVO> footer = sqlCommonDao.findListBySqlAsAliasToBean2(sumSql, TTransferRecordsVO.class, params.toArray());
		Integer total = tTransferRecordsDao.sqlGetCount("select count(1) from ("+sql.toString()+") o", params.toArray());
		return Page.create(voList, footer, total);
	}

	/**
	 * ��������id��ȡ��¼��Ϣ
	 * @param id
	 * @return
	 */
	public TTransferRecords getTransRecord(Integer id) {
		return tTransferRecordsDao.get(TTransferRecords.class, id);
	}

	@Transactional
	public void saveOrUpdateRecord(TTransferRecords record) {
		if(record != null && record.getCreateTime() == null){
			record.setCreateTime(new Date());
		}
		tTransferRecordsDao.saveOrUpdate(record);
	}

	/**
	 * ��ȡ�������롢����
	 * @return
	 */
	public List<SelectVO> getSelectList() {
		SelectVO vo1 = new SelectVO();
		vo1.setId("1");
		vo1.setName("����");
		SelectVO vo2 = new SelectVO();
		vo2.setId("2");
		vo2.setName("����");
		List<SelectVO> voList = new ArrayList<SelectVO>();
		voList.add(vo1);
		voList.add(vo2);
		return voList;
	}

	/**
	 * ɾ��
	 * @param id
	 */
	@Transactional
	public void deleteRecord(Integer id) {
		TTransferRecords record = tTransferRecordsDao.get(TTransferRecords.class, id);
		tTransferRecordsDao.delete(record);
	}

}
