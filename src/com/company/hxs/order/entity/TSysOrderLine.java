package com.company.hxs.order.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 线管订单
 * @author luqi
 *
 */
@Entity
@Table(name = "t_sys_order_line")
public class TSysOrderLine {

	/**
	 * 主键，订单id
	 */
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * 订单id
	 */
	private Integer orderId;
	
	/**
	 * 线管表主键
	 */
	private Integer baseLineId;
	
	/**
	 * 订单数量
	 */
	private BigDecimal orderNums;
	
	/**
	 * 成交价格
	 */
	private BigDecimal salePrice;
	
	/**
	 * 利润
	 */
	private BigDecimal profit;
	
	/**
	 * 流水金额
	 */
	@Transient
	private BigDecimal liushui;
	
	/**
	 * 单价
	 */
	private BigDecimal danjia;
	
	/**
	 * 单位
	 */
	@Transient
	private String danweis;
	
	/**
	 * 规格
	 */
	@Transient
	private String guige;
	
	/**
	 * 类型，1金属软管，2线管
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
