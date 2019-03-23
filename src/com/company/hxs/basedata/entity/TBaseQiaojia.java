package com.company.hxs.basedata.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_base_qiaojia")
public class TBaseQiaojia {

	/**
	 * 主键，自增长
	 */
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	private String id;
	
	/**
	 * 规格
	 */
	private String guige;
	
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
	
	/**
	 * 单价
	 */
	private BigDecimal danjia;

	/**
	 * 类型。1桥架,2盖板
	 */
	private Integer type;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGuige() {
		return guige;
	}

	public void setGuige(String guige) {
		this.guige = guige;
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

}
