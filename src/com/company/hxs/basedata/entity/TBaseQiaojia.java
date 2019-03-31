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
	 * ������������
	 */
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * ���
	 */
	private String guige;
	
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
	
	/**
	 * ����
	 */
	private BigDecimal danjia;

	/**
	 * ���͡�1�ż�,2�ǰ�
	 */
	private Integer type;
	
	/**
	 * �������ͣ�1�����żܣ�2��п�ż�
	 */
	private Integer mType;
	
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

	public Integer getmType() {
		return mType;
	}

	public void setmType(Integer mType) {
		this.mType = mType;
	}

}
