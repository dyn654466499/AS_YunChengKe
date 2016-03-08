package com.yunchengke.app.dynamic.entity;

import java.util.List;

/**
 * 动态列表
 * @author Administrator
 *
 */
public class DynamicDetailsCommentListEntity {
	private String total;
	
	private List<DynamicDetailsCommentEntity> rows;
	
	private String PageCount;
	
	private String page;
	
	private String time;

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<DynamicDetailsCommentEntity> getRows() {
		return rows;
	}

	public void setRows(List<DynamicDetailsCommentEntity> rows) {
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
