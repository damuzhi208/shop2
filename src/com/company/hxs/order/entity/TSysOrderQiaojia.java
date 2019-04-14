package com.company.hxs.order.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 桥架订单
 * @author luqi
 *
 */
@Entity
@Table(name = "t_sys_order_qiaojia")
public class TSysOrderQiaojia {
	
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
	 * 桥架表主键
	 */
	private Integer baseQjId;
	
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
	 * 类型，1喷塑桥架，2镀锌桥架
	 */
	@Transient
	private Integer type;
	
	/**
	 * 材料类型，1桥架，2盖板
	 */
	@Transient
	private Integer mType;

	/**
	 * 高度
	 */
	@Transient
	private BigDecimal heights;
	
	/**
	 * 宽度
	 */
	@Transient
	private BigDecimal widths;
	
	/**
	 * 单价
	 */
	private BigDecimal danjia;
	
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

	public Integer getBaseQjId() {
		return baseQjId;
	}

	public void setBaseQjId(Integer baseQjId) {
		this.baseQjId = baseQjId;
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

	public BigDecimal getHeights() {
		return heights;
	}

	public void setHeights(BigDecimal heights) {
		this.heights = heights;
	}

	public BigDecimal getWidths() {
		return widths;
	}

	public void setWidths(BigDecimal widths) {
		this.widths = widths;
	}

	public BigDecimal getDanjia() {
		return danjia;
	}

	public void setDanjia(BigDecimal danjia) {
		this.danjia = danjia;
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

	public BigDecimal getLiushui() {
		return liushui;
	}

	public void setLiushui(BigDecimal liushui) {
		this.liushui = liushui;
	}
	
}
