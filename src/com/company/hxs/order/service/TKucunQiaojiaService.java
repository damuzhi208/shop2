package com.company.hxs.order.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.company.hxs.common.Page;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.util.CTools;
import com.company.hxs.order.dao.TSysOrderRecordDao;
import com.company.hxs.order.vo.TKucunLineVo;
import com.company.hxs.order.vo.TKucunQiaojiaVo;

@Service
public class TKucunQiaojiaService extends BaseService{

	@Resource private TSysOrderRecordDao tSysOrderRecordDao;
	
	/**
	 * ÇÅ¼Ü¿â´æ
	 * @param vo
	 * @param page
	 * @param rows
	 * @return
	 */
	public Page<TKucunQiaojiaVo> getKucunQiaojia(TKucunQiaojiaVo vo, Integer page, Integer rows) {
		StringBuffer sql = new StringBuffer("select bq.id,bq.widths,bq.heights,bq.houdu,bq.xishu,bq.dwj,bq.danjia,bq.type,bq.mType,getTotalStockQj(bq.id) total,getTotalSalesQj(bq.id) saleNums, (getTotalStockQj(bq.id) - getTotalSalesQj(bq.id)) leaveNums ");
			sql.append(" from t_base_qiaojia bq where 1 = 1 ");
		List<Object> params = new ArrayList<Object>();
		if(vo != null){
			if(vo.getType() != null){
				sql.append(" and bq.type = ? ");
				params.add(vo.getType());
			}
			if(vo.getmType() != null){
				sql.append(" and bq.mType = ? ");
				params.add(vo.getmType());
			}
			if(CTools.isNotEmpty(vo.getGuige())){
				sql.append(" and (bq.widths like ? or bq.heights like ?)");
				params.add("%" + vo.getWidths() + "%");
				params.add("%" + vo.getHeights() + "%");
			}
			if(vo.getHoudu() != null){
				sql.append(" and bq.houdu = ? ");
				params.add("%" + vo.getHoudu() + "%");
			}
			if(vo.getXishu() != null){
				sql.append(" and bq.xishu = ? ");
				params.add("%" + vo.getXishu() + "%");
			}
			if(vo.getDanjia() != null){
				sql.append(" and bq.danjia = ? ");
				params.add("%" + vo.getDanjia() + "%");
			}
		}
		List<TKucunQiaojiaVo> list = sqlCommonDao.findListBySqlAsAliasToBean2(sql.toString(), TKucunQiaojiaVo.class, params.toArray(), page, rows);
		
		Integer total = tSysOrderRecordDao.sqlGetCount("select count(1) from (" + sql.toString() + ") o", params.toArray());
		return Page.create(list, total);
	}

	/**
	 * Ïß¹Ü¿â´æ
	 * @param vo
	 * @param page
	 * @param rows
	 * @return
	 */
	public Page<TKucunLineVo> getKucunLine(TKucunLineVo vo, Integer page, Integer rows) {
		StringBuffer sql = new StringBuffer("select bl.id,bl.guige,bl.danjia,bl.mType,u.`name` danweis,getTotalStockLine(bl.id) total,getTotalSalesLine(bl.id) sales,(getTotalStockLine(bl.id) - getTotalSalesLine(bl.id)) leaves");
			sql.append(" from t_base_linetube bl left join t_base_unit u on bl.danwei = u.id");
			sql.append(" where 1 = 1");
		List<Object> params = new ArrayList<Object>();
		if(vo != null){
			if(vo.getmType() != null){
				sql.append(" and bl.mType = ?");
				params.add(vo.getmType());
			}
			if(CTools.isNotEmpty(vo.getGuige())){
				sql.append(" and bl.guige like ? ");
				params.add("%" + vo.getGuige() + "%");
			}
			if(CTools.isNotEmpty(vo.getDanweis())){
				sql.append(" and bl.danwei = ?");
				params.add(vo.getDanweis());
			}
		}
		List<TKucunLineVo> list = sqlCommonDao.findListBySqlAsAliasToBean2(sql.toString(), TKucunLineVo.class, params.toArray(), page, rows);
		
		Integer total = tSysOrderRecordDao.sqlGetCount("select count(1) from (" + sql.toString() + ")o", params.toArray());
		return Page.create(list, total);
	}

}
