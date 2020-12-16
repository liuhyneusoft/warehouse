package com.zwzw.warehouse.param;


import lombok.ToString;

@ToString
public class Pagination {
	private int page;
	private int pageSize = 20;
	private long totalRows;

	public Pagination() {
	}

	public Pagination(int page, int pageSize, long totalRows) {
		this.page = page;
		this.pageSize = pageSize;
		this.totalRows = totalRows;
	}

	public int getStartRownum() {
		int startRownum = 0;

		if (page > 1) {
			startRownum = pageSize * (page - 1);
		}

		return startRownum;
	}

	public long getEndRownum() {
		return Math.min((long) (pageSize * page), totalRows);
	}

	public int getTotalPages() {
		if (pageSize <= 0) {
			return 0;
		}

		return (int) ((totalRows - 1) / pageSize + 1);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
}

