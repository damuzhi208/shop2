package com.company.hxs.stock.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "t_stock_qiaojia")
public class TStockQiaojia {

	/**
	 * 主键
	 */
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 桥架表主键
	 */
	private Integer qiaojiaId;

	/**
	 * 库存数量
	 */
	private Integer stockNum;

	/**
	 * 交易日期
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

	public Integer getQiaojiaId() {
		return qiaojiaId;
	}

	public void setQiaojiaId(Integer qiaojiaId) {
		this.qiaojiaId = qiaojiaId;
	}

	public Integer getStockNum() {
		return stockNum;
	}

	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
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

}
