package com.company.hxs.order.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "t_sys_order_record")
public class TSysOrderRecord {
	
	/**
	 * 主键，订单id
	 */
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer orderId;
	
	/**
	 * 顾客id
	 */
	private Integer customerId;
	
	/**
	 * 订单时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date orderDate;
	
	/**
	 * 操作时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date opTime;

	/**
	 * 流水金额
	 */
	@Transient
	private BigDecimal liushui;
	
	/**
	 * 客户名称
	 */
	@Transient
	private String customerName;
	
	/**
	 * 客户单位名称
	 */
	@Transient
	private String companyName;
	
	/**
	 * 利润
	 */
	@Transient
	private BigDecimal profit;
	
	/**
	 * 订单开始日期
	 */
	@Transient
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date beginDate;
	
	/**
	 * 订单结束日期
	 */
	@Transient
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	
	/**
	 * 电话号码
	 */
	@Transient
	private String telephone;
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOpTime() {
		return opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
}
