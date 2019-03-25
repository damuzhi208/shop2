package com.company.hxs.sys.transfer.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ��Ʒ������¼
 * @author luqi
 *
 */
@Entity
@Table(name = "t_transfer_records")
public class TTransferRecords {
	
	/**
	 * ����
	 */
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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
	 * ��λ,������
	 */
	private Integer unit;
	
	/**
	 * �����۸�
	 */
	private BigDecimal cost;
	
	/**
	 * ����
	 */
	private BigDecimal salePrice;
	
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
	
}
