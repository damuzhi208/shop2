package com.company.hxs.stock.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 库存查询
 * @author luqi
 *
 */
public class TStockQiaojiaVO {
	
	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 桥架表主键
	 */
	private Integer qiaojiaId;

	/**
	 * 库存数量
	 */
	private Integer stockNum;

	/**
	 * 交易日期
	 */
	private Date transDate;

	/**
	 * 操作时间
	 */
	private Date createTime;

	/**
	 * 类型，1,桥架，2盖板
	 */
	private Integer type;
	
	/**
	 * 桥架类型，1喷塑桥架2镀锌桥架
	 */
	private Integer mType;
	
	/**
	 * 宽度
	 */
	private BigDecimal widths;
	
	/**
	 * 高度
	 */
	private BigDecimal heights;
	
	/**
	 * 厚度
	 */
	private BigDecimal houdu;
	
	/**
	 * 系数
	 */
	private BigDecimal xishu;
	
	/**
	 * 吨位价
	 */
	private BigDecimal dwj;
	
	/**
	 * 单价
	 */
	private BigDecimal danjia;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQiaojiaId() {
		return qiaojiaId;
	}

	public void setQiaojiaId(Integer qiaojiaId) {
		this.qiaojiaId = qiaojiaId;
	}

	public Integer getStockNum() {
		return stockNum;
	}

	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getmType() {
		return mType;
	}

	public void setmType(Integer mType) {
		this.mType = mType;
	}

	public BigDecimal getHoudu() {
		return houdu;
	}

	public void setHoudu(BigDecimal houdu) {
		this.houdu = houdu;
	}

	public BigDecimal getXishu() {
		return xishu;
	}

	public void setXishu(BigDecimal xishu) {
		this.xishu = xishu;
	}

	public BigDecimal getDwj() {
		return dwj;
	}

	public void setDwj(BigDecimal dwj) {
		this.dwj = dwj;
	}

	public BigDecimal getDanjia() {
		return danjia;
	}

	public void setDanjia(BigDecimal danjia) {
		this.danjia = danjia;
	}

	public BigDecimal getWidths() {
		return widths;
	}

	public void setWidths(BigDecimal widths) {
		this.widths = widths;
	}

	public BigDecimal getHeights() {
		return heights;
	}

	public void setHeights(BigDecimal heights) {
		this.heights = heights;
	}
	
}
