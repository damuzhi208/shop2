package com.company.hxs.sys.transfer.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ��Ʒ������¼
 * @author luqi
 *
 */
public class TTransferRecordsVO {
	
	private Integer id;
	
	/**
	 * ��Ʒ����
	 */
	private String shopName;
	
	/**
	 * ����
	 */
	private BigDecimal nums;
	
	/**
	 * ��λ��������
	 */
	private Integer unit;
	
	/**
	 * ��λ��������
	 */
	private String unitStr;
	
	/**
	 * �����۸�
	 */
	private BigDecimal cost;
	
	/**
	 * ���׼۸�
	 */
	private BigDecimal salePrice;
	
	/**
	 * ����
	 */
	private BigDecimal profit;
	
	/**
	 * ��ˮ��
	 */
	private BigDecimal liushui;
	
	/**
	 * �������ͣ�1����2����
	 */
	private Integer transType;
	
	/**
	 * �ͻ�Id
	 */
	private Integer customerId;
	
	/**
	 * ����ʱ��
	 */
	private Date transDate;
	
	/**
	 * ����ʱ��
	 */
	private Date createTime;
	
	/**
	 * �˿�����
	 */
	private String customerName;

	/**
	 * ��˾����
	 */
	private String companyName;
	
	/**
	 * ��ϵ�绰
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
