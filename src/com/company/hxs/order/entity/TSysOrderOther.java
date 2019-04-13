package com.company.hxs.order.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * ��������
 * @author luqi
 *
 */
@Entity
@Table(name = "t_sys_order_other")
public class TSysOrderOther {

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
	 * ��Ʒ����ͺ�
	 */
	private String guige;
	
	/**
	 * ��λ
	 */
	private Integer unit;
	
	/**
	 * ��λ
	 */
	@Transient
	private String danweis;
	
	/**
	 * ��������
	 */
	private BigDecimal orderNums;
	
	/**
	 * �ɱ��۸�
	 */
	private BigDecimal costPrice;
	
	/**
	 * �ɽ��۸�
	 */
	private BigDecimal salePrice;
	
	/**
	 * ����
	 */
	private BigDecimal profit;

	/**
	 * ��ˮ
	 */
	@Transient
	private BigDecimal liushui;
	
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

	public BigDecimal getOrderNums() {
		return orderNums;
	}

	public void setOrderNums(BigDecimal orderNums) {
		this.orderNums = orderNums;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
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

	public String getDanweis() {
		return danweis;
	}

	public void setDanweis(String danweis) {
		this.danweis = danweis;
	}

	public BigDecimal getLiushui() {
		return liushui;
	}

	public void setLiushui(BigDecimal liushui) {
		this.liushui = liushui;
	}
	
}
