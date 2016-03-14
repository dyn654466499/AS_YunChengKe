package com.yunchengke.app.http;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

import android.content.Context;

/**
 * 类名：GlideConfiguration <br/>
 * 描述：配置Glide库参数 <br/>
 * 创建时间：2015年10月21日 上午9:17:50
 * @author 709835509@qq.com
 * @version 1.0
 */
public class GlideConfiguration implements GlideModule {
	/** 磁盘缓存大小 */ 
	private static final int DISK_CACHE_MAX_SIZE = 18 * 1024 * 1024;
	/** 内存缓存大小 */
	private static final int MEMORY_CACHE_MAX_SIZE = 60 * 1024 * 1024;
	 
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
    	
		int maxMemory = (int) Runtime.getRuntime().maxMemory();		
		 
		// 默认可用内存的1/6，如果内存足够大，容量不能超过60M
		int cacheSize = maxMemory / 6;
		if (cacheSize > MEMORY_CACHE_MAX_SIZE) {
			cacheSize = MEMORY_CACHE_MAX_SIZE;
		}
    	
        // Apply options to the builder here.
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888)
        	.setMemoryCache(new LruResourceCache(cacheSize))
        	.setDiskCache(new InternalCacheDiskCacheFactory(context, DISK_CACHE_MAX_SIZE));
    }
 
    @Override
    public void registerComponents(Context context, Glide glide) {
        // register ModelLoaders here.
    }
}
