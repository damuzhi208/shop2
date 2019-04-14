package com.company.hxs.order.vo;

import java.math.BigDecimal;

/**
 * 桥架库存
 * @author luqi
 *
 */
public class TKucunQiaojiaVo {
	
	private Integer id;//桥架表主键
	
	private String guige;
	
	private BigDecimal xishu;
	
	private BigDecimal widths;
	
	private BigDecimal heights;
	
	private BigDecimal houdu;
	
	private BigDecimal dwj;
	
	private BigDecimal danjia;
	
	private Integer type;
	
	private Integer mType;
	
	/**
	 * 总库存
	 */
	private BigDecimal total;
	
	/**
	 * 交易数量
	 */
	private BigDecimal saleNums;
	
	/**
	 * 库存数量
	 */
	private BigDecimal leaveNums;

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getSaleNums() {
		return saleNums;
	}

	public void setSaleNums(BigDecimal saleNums) {
		this.saleNums = saleNums;
	}

	public String getGuige() {
		return guige;
	}

	public void setGuige(String guige) {
		this.guige = guige;
	}

	public BigDecimal getXishu() {
		return xishu;
	}

	public void setXishu(BigDecimal xishu) {
		this.xishu = xishu;
	}

	public BigDecimal getWidths() {
		return widths;
	}

	public void setWidths(BigDecimal widths) {
		this.widths = widths;
	}

	public BigDecimal getHeights() {
		return heights;
	}

	public void setHeights(BigDecimal heights) {
		this.heights = heights;
	}

	public BigDecimal getHoudu() {
		return houdu;
	}

	public void setHoudu(BigDecimal houdu) {
		this.houdu = houdu;
	}

	public BigDecimal getDwj() {
		return dwj;
	}

	public void setDwj(BigDecimal dwj) {
		this.dwj = dwj;
	}

	public BigDecimal getDanjia() {
		return danjia;
	}

	public void setDanjia(BigDecimal danjia) {
		this.danjia = danjia;
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

	public BigDecimal getLeaveNums() {
		return leaveNums;
	}

	public void setLeaveNums(BigDecimal leaveNums) {
		this.leaveNums = leaveNums;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
