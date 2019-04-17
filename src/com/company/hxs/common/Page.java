package com.company.hxs.common;

import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "rawtypes"})
public class Page<T> {
	
	private Integer total;//�ܼ�¼��
	
	private Integer page;//ҳ�룬�ڼ�ҳ
	
	private Integer size;//ÿҳ��ʾ����
	
	private List<T> rows;//����list
	
	private List<T> footer;//�ϼ�list

	private Page(List<T> rows, Integer total) {
		this.rows = (List<T>) (null == rows ? Collections.emptyList() : rows);
		this.total = (null == total ? Integer.valueOf(0) : total);
	}

	private Page(List<T> rows, List<T> footer, Integer total) {
		this.rows = (List<T>) (null == rows ? Collections.emptyList() : rows);
		this.footer = (List<T>) (null == footer ? Collections.emptyList() : footer);
		this.total = (null == total ? Integer.valueOf(0) : total);
	}
	
	public Page(List<T> rows, Integer page, Integer size, Integer total) {
		this.rows = (List<T>) (null == rows ? Collections.emptyList() : rows);
		this.page = (null == page ? Integer.valueOf(0) : page);
		this.size = (null == size ? Integer.valueOf(0) : size);
		this.total = (null == total ? Integer.valueOf(0) : total);
	}

	public static <E> Page<E> create(List<E> rows, Integer total) {
		return new Page(rows, total);
	}

	public static <E> Page<E> create(List<E> rows, List<E> footer, Integer total) {
		return new Page(rows, footer, total);
	}

	public Integer getTotal() {
		return this.total;
	}

	public List<T> getRows() {
		return this.rows;
	}

	public List<T> getFooter() {
		return this.footer;
	}
	
	public void setFooter(List<T> footer) {
		this.footer = footer;
	}

	public Integer getPage(){
		return this.page;
	}
	
	public Integer getSize(){
		return this.size;
	}
	
}
