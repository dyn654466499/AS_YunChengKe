package com.yunchengke.app.bean.dynamic;

import java.util.List;

/**
 * 动态列表
 * @author Administrator
 *
 */
public class DynamicListEntity {
	private long total;
	
	private List<DynamicDetailsEntity> rows;
	
	private String PageCount;
	
	private String page;
	
	private String time;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<DynamicDetailsEntity> getRows() {
		return rows;
	}

	public void setRows(List<DynamicDetailsEntity> rows) {
		this.rows = rows;
	}

	public String getPageCount() {
		return PageCount;
	}

	public void setPageCount(String pageCount) {
		PageCount = pageCount;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
}
