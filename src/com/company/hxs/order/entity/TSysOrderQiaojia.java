package com.company.hxs.order.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * �żܶ���
 * @author luqi
 *
 */
@Entity
@Table(name = "t_sys_order_qiaojia")
public class TSysOrderQiaojia {
	
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
	 * �żܱ�����
	 */
	private Integer baseQjId;
	
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
	 * ���ͣ�1�����żܣ�2��п�ż�
	 */
	@Transient
	private Integer type;
	
	/**
	 * �������ͣ�1�żܣ�2�ǰ�
	 */
	@Transient
	private Integer mType;

	/**
	 * �߶�
	 */
	@Transient
	private BigDecimal heights;
	
	/**
	 * ���
	 */
	@Transient
	private BigDecimal widths;
	
	/**
	 * ����
	 */
	private BigDecimal danjia;
	
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
