package com.company.hxs.stock.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.hxs.common.Page;
import com.company.hxs.common.service.BaseService;
import com.company.hxs.common.util.CTools;
import com.company.hxs.stock.dao.TStockQiaojiaDao;
import com.company.hxs.stock.entity.TStockQiaojia;
import com.company.hxs.stock.vo.TStockQiaojiaVO;

@Service
public class TStockQiaojiaService extends BaseService{

	@Resource private TStockQiaojiaDao tStockQiaojiaDao;
	
	public Page<TStockQiaojiaVO> getPageResult(TStockQiaojiaVO stock, Integer page, Integer rows) {
		StringBuffer sql = new StringBuffer("select s.id,s.qiaojiaId,s.stockNum,s.transDate,s.createTime,q.widths,q.heights,q.houdu,q.xishu,q.dwj,q.danjia,q.type,q.mType ");
			sql.append(" from t_stock_qiaojia s,t_base_qiaojia q where s.qiaojiaId = q.id");
		List<Object> params = new ArrayList<Object>();
		if(stock != null){
			if(CTools.isNotEmpty(stock.getType())){
				sql.append(" and q.type = ?");
				params.add(stock.getType());
			}
			if(CTools.isNotEmpty(stock.getmType())){
				sql.append(" and q.mType = ?");
				params.add(stock.getmType());
			}
			if(CTools.isNotEmpty(stock.getWidths())){
				sql.append(" and q.widths like ? ");
				params.add("%"+stock.getWidths()+"%");
			}
			if(CTools.isNotEmpty(stock.getXishu())){
				sql.append(" and q.xishu like ? ");
				params.add("%"+stock.getXishu()+"%");
			}
			if(CTools.isNotEmpty(stock.getDwj())){
				sql.append(" and q.dwj like ? ");
				params.add("%"+stock.getDwj()+"%");
			}
		}
		List<TStockQiaojiaVO> voList = sqlCommonDao.findListBySqlAsAliasToBean2(sql.toString(), TStockQiaojiaVO.class, params.toArray(), page, rows);
		Integer total = tStockQiaojiaDao.sqlGetCount("select count(1) from (" + sql + ") o", params.toArray());
		return Page.create(voList, total);
	}

	public TStockQiaojia getTStockQj(Integer id) {
		return tStockQiaojiaDao.get(TStockQiaojia.class, id);
	}

	@Transactional
	public void saveOrUpdateTStockQiaojia(TStockQiaojia stock) {
		if(stock != null && stock.getCreateTime() == null){
			stock.setCreateTime(new Date());
		}
		tStockQiaojiaDao.saveOrUpdate(stock);
	}

}
