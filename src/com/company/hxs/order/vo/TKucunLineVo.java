package com.company.hxs.order.vo;

import java.math.BigDecimal;

/**
 * œﬂπ‹/»Ìπ‹ø‚¥Ê
 * @author luqi
 *
 */
public class TKucunLineVo {
	
	private Integer id;
	
	private String guige;
	
	private String danweis;
	
	private BigDecimal danjia;
	
	private Integer mType;
	
	private BigDecimal total;
	
	private BigDecimal sales;
	
	private BigDecimal leaves;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGuige() {
		return guige;
	}

	public void setGuige(String guige) {
		this.guige = guige;
	}

	public String getDanweis() {
		return danweis;
	}

	public void setDanweis(String danweis) {
		this.danweis = danweis;
	}

	public BigDecimal getDanjia() {
		return danjia;
	}

	public void setDanjia(BigDecimal danjia) {
		this.danjia = danjia;
	}

	public Integer getmType() {
		return mType;
	}

	public void setmType(Integer mType) {
		this.mType = mType;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getSales() {
		return sales;
	}

	public void setSales(BigDecimal sales) {
		this.sales = sales;
	}

	public BigDecimal getLeaves() {
		return leaves;
	}

	public void setLeaves(BigDecimal leaves) {
		this.leaves = leaves;
	}
	
}
