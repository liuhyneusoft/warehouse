package com.zwzw.warehouse.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zwzw.warehouse.param.Pagination;

public class PaginationUtil<T> {
	  public final static int MAX_PAGE_SIZE = 50;
	public static <T> Page<T> getPage(Pagination pagination) {
		if (pagination == null) {
			return getDefaultPage();
		}
		int startPage = pagination.getPage();
		int pageSize = pagination.getPageSize();
		if (startPage < 1) {
			startPage = 1;
		}
		if (pageSize > MAX_PAGE_SIZE || pageSize < 1) {
			pageSize = MAX_PAGE_SIZE;
		}
		return PageHelper.startPage(startPage, pageSize);
	}

	public static <T> Page<T> getDefaultPage() {
		return new Page<T>(1, MAX_PAGE_SIZE);
	}

	public static <T> Page<T> getPageForEs(Pagination pagination) {
		if (pagination == null) {
			return new Page<T>(1, MAX_PAGE_SIZE);
		}
		int startPage = pagination.getPage() - 1;
		int pageSize = pagination.getPageSize();
		if (startPage < 0) {
			startPage = 0;
		}
		if (pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		return new Page<T>(startPage, pageSize);
	}

	public static int getOffset(Pagination pagination) {
		if (pagination == null) {
			return 0;
		}
		int startPage = pagination.getPage() - 1;
		int pageSize = pagination.getPageSize();
		if (startPage < 0) {
			startPage = 0;
		}
		if (pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		return startPage * pageSize;
	}

	public static int getPageSize(Pagination pagination) {
		if (pagination == null) {
			return MAX_PAGE_SIZE;
		}
		int pageSize = pagination.getPageSize();
		if (pageSize > MAX_PAGE_SIZE) {
			pageSize = MAX_PAGE_SIZE;
		}
		return pageSize;
	}
}
