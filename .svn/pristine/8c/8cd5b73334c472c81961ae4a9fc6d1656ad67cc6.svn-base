package com.yunchengke.app.dynamic.entity;

import java.util.ArrayList;
import java.util.List;


public class Page<T>
{
	
	public static final String ASC               = "ASC";
	public static final String DESC              = "DESC";
	public static final int    DEFAULT_PAGE_SIZE = 15;
	private int                plainPageNum;
	private List<T>       	   result;
	protected int              pageNum;                   //当前页的页码
	protected int              numPerPage;                //每页条数
	private String             orderField;
	private String             orderDirection;
	private int                totalPage;                 //总页数
	private int                prePage;                   //上一页
	private int                nextPage;                  //下一页
	protected long             totalCount;                //总条数
	
	public Page() {
		plainPageNum = 1;
		result = new ArrayList<T>();
		pageNum = 1;
		numPerPage = 10;
		orderField = "";
		orderDirection = "";
		totalPage = 1;
		prePage = 1;
		nextPage = 1;
		totalCount = 0L;
	}
	
	public int getPageNum() {
		if (pageNum > totalPage) pageNum = totalPage;
		return pageNum;
	}
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum <= 0 ? 1 : pageNum;
		plainPageNum = this.pageNum;
	}
	
	public int getNumPerPage() {
		return numPerPage;
	}
	
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage <= 0 ? 10 : numPerPage;
	}
	
	public String getOrderField() {
		return orderField;
	}
	
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	
	public String getOrderDirection() {
		return orderDirection;
	}
	
	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getPrePage() {
		prePage = pageNum - 1;
		if (prePage < 1) prePage = 1;
		return prePage;
	}
	
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	
	public int getNextPage() {
		nextPage = pageNum + 1;
		if (nextPage > totalPage) nextPage = totalPage;
		return nextPage;
	}
	
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	
	public long getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		totalPage = (int) (totalCount - 1L) / numPerPage + 1;
	}
	
	public int getPlainPageNum() {
		return plainPageNum;
	}
	
	public void setPlainPageNum(int plainPageNum) {
		this.plainPageNum = plainPageNum;
	}
	
	public List<T> getResult() {
		return result;
	}
	
	public void setResult(List<T> result) {
		this.result = result;
	}

}
