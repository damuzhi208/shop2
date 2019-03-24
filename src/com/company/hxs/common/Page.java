package com.company.hxs.common;

import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "rawtypes"})
public class Page<T> {
	private Integer total;
	private List<T> rows;
	private List<T> footer;

	private Page(List<T> rows, Integer total) {
		this.rows = (List<T>) (null == rows ? Collections.emptyList() : rows);
		this.total = (null == total ? Integer.valueOf(0) : total);
	}

	private Page(List<T> rows, List<T> footer, Integer total) {
		this.rows = (List<T>) (null == rows ? Collections.emptyList() : rows);
		this.footer = (List<T>) (null == footer ? Collections.emptyList() : footer);
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
}
