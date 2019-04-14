package com.company.hxs.order.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * �߹ܶ���
 * @author luqi
 *
 */
@Entity
@Table(name = "t_sys_order_line")
public class TSysOrderLine {

	/**
	 * ����������id
	 */
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * ����id
	 */
	private Integer orderId;
	
	/**
	 * �߹ܱ�����
	 */
	private Integer baseLineId;
	
	/**
	 * ��������
	 */
	private BigDecimal orderNums;
	
	/**
	 * �ɽ��۸�
	 */
	private BigDecimal salePrice;
	
	/**
	 * ����
	 */
	private BigDecimal profit;
	
	/**
	 * ��ˮ���
	 */
	@Transient
	private BigDecimal liushui;
	
	/**
	 * ����
	 */
	private BigDecimal danjia;
	
	/**
	 * ��λ
	 */
	@Transient
	private String danweis;
	
	/**
	 * ���
	 */
	@Transient
	private String guige;
	
	/**
	 * ���ͣ�1������ܣ�2�߹�
	 */
	@Transient
	private Integer mType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getBaseLineId() {
		return baseLineId;
	}

	public void setBaseLineId(Integer baseLineId) {
		this.baseLineId = baseLineId;
	}

	public BigDecimal getOrderNums() {
		return orderNums;
	}

	public void setOrderNums(BigDecimal orderNums) {
		this.orderNums = orderNums;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getLiushui() {
		return liushui;
	}

	public void setLiushui(BigDecimal liushui) {
		this.liushui = liushui;
	}

	public Integer getmType() {
		return mType;
	}

	public void setmType(Integer mType) {
		this.mType = mType;
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

	public String getGuige() {
		return guige;
	}

	public void setGuige(String guige) {
		this.guige = guige;
	}

}
