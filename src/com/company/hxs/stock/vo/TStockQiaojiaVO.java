package com.company.hxs.stock.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ����ѯ
 * @author luqi
 *
 */
public class TStockQiaojiaVO {
	
	/**
	 * ����
	 */
	private Integer id;

	/**
	 * �żܱ�����
	 */
	private Integer qiaojiaId;

	/**
	 * �������
	 */
	private Integer stockNum;

	/**
	 * ��������
	 */
	private Date transDate;

	/**
	 * ����ʱ��
	 */
	private Date createTime;

	/**
	 * ���ͣ�1,�żܣ�2�ǰ�
	 */
	private Integer type;
	
	/**
	 * �ż����ͣ�1�����ż�2��п�ż�
	 */
	private Integer mType;
	
	/**
	 * ���
	 */
	private String guige;
	
	/**
	 * ���
	 */
	private BigDecimal houdu;
	
	/**
	 * ϵ��
	 */
	private BigDecimal xishu;
	
	/**
	 * ��λ��
	 */
	private BigDecimal dwj;
	
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

	public String getGuige() {
		return guige;
	}

	public void setGuige(String guige) {
		this.guige = guige;
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
	
}
