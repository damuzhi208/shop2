package com.company.hxs.sys.transfer.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品调动记录
 * @author luqi
 *
 */
public class TTransferRecordsVO {
	
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
	 * 单位，个，米
	 */
	private Integer unit;
	
	/**
	 * 单位，个，米
	 */
	private String unitStr;
	
	/**
	 * 调动价格
	 */
	private BigDecimal cost;
	
	/**
	 * 交易价格
	 */
	private BigDecimal salePrice;
	
	/**
	 * 利润
	 */
	private BigDecimal profit;
	
	/**
	 * 流水额
	 */
	private BigDecimal liushui;
	
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
	private Date transDate;
	
	/**
	 * 操作时间
	 */
	private Date createTime;
	
	/**
	 * 顾客姓名
	 */
	private String customerName;

	/**
	 * 公司名称
	 */
	private String companyName;
	
	/**
	 * 联系电话
	 */
	private String telephone;
	
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public String getUnitStr() {
		return unitStr;
	}

	public void setUnitStr(String unitStr) {
		this.unitStr = unitStr;
	}

	public BigDecimal getLiushui() {
		return liushui;
	}

	public void setLiushui(BigDecimal liushui) {
		this.liushui = liushui;
	}
	
}
