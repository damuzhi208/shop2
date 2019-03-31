package com.company.hxs.sys.transfer.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 商品调动记录
 * @author luqi
 *
 */
@Entity
@Table(name = "t_transfer_records")
public class TTransferRecords {
	
	/**
	 * 主键
	 */
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * 商品名称
	 */
	private String shopName;
	
	/**
	 * 数量
	 */
	private BigDecimal nums;
	
	/**
	 * 单位,个，米
	 */
	private Integer unit;
	
	/**
	 * 调动价格
	 */
	private BigDecimal cost;
	
	/**
	 * 成交价格
	 */
	private BigDecimal salePrice;
	
	/**
	 * 利润
	 */
	private BigDecimal profit;
	
	/**
	 * 调动类型，1调进2调出
	 */
	private Integer transType;
	
	/**
	 * 客户Id
	 */
	private Integer customerId;
	
	/**
	 * 调动时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date transDate;
	
	/**
	 * 操作时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public BigDecimal getNums() {
		return nums;
	}

	public void setNums(BigDecimal nums) {
		this.nums = nums;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public Integer getTransType() {
		return transType;
	}

	public void setTransType(Integer transType) {
		this.transType = transType;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	
}
