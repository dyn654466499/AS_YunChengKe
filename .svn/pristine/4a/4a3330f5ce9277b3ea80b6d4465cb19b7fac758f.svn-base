package com.yunchengke.app.http;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

public abstract class HttpRequestListener<T> implements Response.Listener<T>, Response.ErrorListener {

	/** 
	 * 开始请求执行接口
	 */
	public void onStartRequest(Request<T> request) {
		
	}
	
	/**
	 * 完成、取消、失败统一调用接口
	 */
	public void onFinishRequest() {
		
	}

    /**
     * 请求完成
     */
    @Override
    public void onResponse(T response) {

    }
	
    /**
     * 请求错误
     */
    @Override
    public void onErrorResponse(VolleyError error) {

    }
    
    /**
     * 取消请求
     */
    public void onCancel() {
    	
    }
    
}
