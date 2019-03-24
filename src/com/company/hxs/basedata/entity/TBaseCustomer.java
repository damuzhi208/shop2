package com.company.hxs.basedata.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_base_customer")
public class TBaseCustomer {
	
	/**
	 * 主键
	 */
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 公司名称
	 */
	private String companyName;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 电话号码
	 */
	private String telephone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
