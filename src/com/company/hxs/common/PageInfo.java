package com.company.hxs.common;

public class PageInfo {
	
	public static final int DEFUALT_PAGE_SIZE = 10;
	private Integer maxResult = Integer.valueOf(10);
	private Integer totalResult;
	private Integer currentPage;
	private Integer totalPage;

	public PageInfo() {
		this.currentPage = Integer.valueOf(1);
	}

	public Integer getMaxResult() {
		return this.maxResult;
	}

	public void setMaxResult(Integer maxResult) {
		this.maxResult = maxResult;
	}

	public Integer getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalResult() {
		return this.totalResult;
	}

	public void setTotalResult(Integer total) {
		this.totalResult = total;
		this.totalPage = Integer.valueOf((this.totalResult.intValue() - 1) / this.maxResult.intValue() + 1);
		this.currentPage = (this.currentPage.intValue() > this.totalPage .intValue() ? this.totalPage : this.currentPage);
	}

	public Integer getTotalPage() {
		return this.totalPage;
	}

	public Integer getFirstResult() {
		return Integer.valueOf((this.currentPage.intValue() - 1) * this.maxResult.intValue());
	}
}
