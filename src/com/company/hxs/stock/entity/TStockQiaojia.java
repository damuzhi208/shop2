package com.company.hxs.stock.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_stock_qiaojia")
public class TStockQiaojia {

	/**
	 * ����
	 */
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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

}
