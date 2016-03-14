package com.yunchengke.app.dynamic.entity;

import org.json.JSONObject;


public class PageBuilder
{
	private Class<?> c;
	
	public <T> PageBuilder(Class<T> c) {
		this.c = c;
	}
	
	public Page build(JSONObject jsonObject) throws Exception {
		Page page = new Page();
		
		if (jsonObject.has("plainPageNum") && !jsonObject.isNull("plainPageNum"))
		{
			page.setPlainPageNum(jsonObject.getInt("plainPageNum"));
		}
		
		if (jsonObject.has("pageNum") && !jsonObject.isNull("pageNum"))
		{
			page.setPageNum(jsonObject.getInt("pageNum"));
		}
		
//		if (jsonObject.has("result") && !jsonObject.isNull("result"))
//		{
//			JSONArray array = jsonObject.getJSONArray("result");
//			List<?> list = JsonTools.getBeanList(array.toString(), this.c);
//			page.setResult((List<Object>) list);
//		}
		
		if (jsonObject.has("numPerPage") && !jsonObject.isNull("numPerPage"))
		{
			page.setNumPerPage(jsonObject.getInt("numPerPage"));
		}
		
		if (jsonObject.has("totalPage") && !jsonObject.isNull("totalPage"))
		{
			page.setTotalPage(jsonObject.getInt("totalPage"));
		}
		
		if (jsonObject.has("prePage") && !jsonObject.isNull("prePage"))
		{
			page.setPrePage(jsonObject.getInt("prePage"));
		}
		
		if (jsonObject.has("nextPage") && !jsonObject.isNull("nextPage"))
		{
			page.setNextPage(jsonObject.getInt("nextPage"));
		}
		
		if (jsonObject.has("totalCount") && !jsonObject.isNull("totalCount"))
		{
			page.setTotalCount(jsonObject.getLong("totalCount"));
		}
		
		return page;
	}
}
