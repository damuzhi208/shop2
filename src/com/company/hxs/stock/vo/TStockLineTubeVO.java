package com.company.hxs.stock.vo;

import java.math.BigDecimal;
import java.util.Date;

public class TStockLineTubeVO {
	
	/**
	 * ����
	 */
	private Integer id;

	/**
	 * ��ܱ�����
	 */
	private Integer lineId;

	/**
	 * �������
	 */
	private BigDecimal stockNum;

	/**
	 * ��������
	 */
	private Date transDate;

	/**
	 * ����ʱ��
	 */
	private Date createTime;

	private String danweis;
	
	/**
	 * �ż����ͣ�1�����ż�2��п�ż�
	 */
	private Integer mType;
	
	/**
	 * ���
	 */
	private String guige;
	
	
	/**
	 * ��λ
	 */
	private Integer unit;
	
	/**
	 * ����
	 */
	private BigDecimal danjia;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public BigDecimal getStockNum() {
		return stockNum;
	}

	public void setStockNum(BigDecimal stockNum) {
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


	public Integer getmType() {
		return mType;
	}

	public void setmType(Integer mType) {
		this.mType = mType;
	}

	public String getGuige() {
		return guige;
	}

	public void setGuige(String guige) {
		this.guige = guige;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public BigDecimal getDanjia() {
		return danjia;
	}

	public void setDanjia(BigDecimal danjia) {
		this.danjia = danjia;
	}

	public String getDanweis() {
		return danweis;
	}

	public void setDanweis(String danweis) {
		this.danweis = danweis;
	}
	
}
