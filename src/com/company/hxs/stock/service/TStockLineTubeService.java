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
import com.company.hxs.stock.dao.TStockLineTubeDao;
import com.company.hxs.stock.entity.TStockLineTube;
import com.company.hxs.stock.vo.TStockLineTubeVO;

@Service
public class TStockLineTubeService extends BaseService{

	@Resource private TStockLineTubeDao lineTubeDao;
	
	/**
	 * 查询列表数据
	 * @param stock
	 * @param page
	 * @param rows
	 * @return
	 */
	public Page<TStockLineTubeVO> getPageResult(TStockLineTubeVO vo, Integer page, Integer rows) {
		StringBuffer sql = new StringBuffer("select t.id,t.lineId,t.stockNum,t.transDate,t.createTime,l.guige,l.danjia,l.mType,b.name danweis ");
			sql.append(" from t_stock_linetube t,t_base_linetube l,t_base_unit b where t.lineId = l.id and b.id = l.danwei");
		List<Object> params = new ArrayList<Object>();
		if(vo != null){
			if(vo.getmType() != null){
				sql.append(" and l.mType = ?");
				params.add(vo.getmType());
			}
			if(CTools.isNotEmpty(vo.getGuige())){
				sql.append(" and l.guige like ?");
				params.add("%" + vo.getGuige() + "%");
			}
		}
		List<TStockLineTubeVO> voList = sqlCommonDao.findListBySqlAsAliasToBean2(sql.toString(), TStockLineTubeVO.class, params.toArray(), page, rows);
		Integer total = lineTubeDao.sqlGetCount("select count(1) from (" + sql.toString() + ") o", params.toArray());
		//合计
		String sSql = "select '合计' guige,sum(o.stockNum) stockNum from (" + sql.toString() + ") o";
		List<TStockLineTubeVO> footer = sqlCommonDao.findListBySqlAsAliasToBean2(sSql, TStockLineTubeVO.class, params.toArray());
		return Page.create(voList, footer, total);
	}

	/**
	 * 根据主键获取记录
	 * @param id
	 * @return
	 */
	public TStockLineTube getTStockLineTube(Integer id) {
		return lineTubeDao.get(TStockLineTube.class, id);
	}

	/**
	 * 保存或更新软管库存
	 * @param line
	 */
	@Transactional
	public void saveOrUpdateTStockLineTube(TStockLineTube line) {
		line.setCreateTime(new Date());
		lineTubeDao.saveOrUpdate(line);
	}

}
