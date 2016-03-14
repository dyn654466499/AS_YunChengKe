package com.yunchengke.app.utils;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

@SuppressLint("NewApi")
public class BitmapCache implements ImageCache {
	private LruCache<String, Bitmap> mCache;

	public BitmapCache() {
		int maxSize = 10 * 1024 * 1024;
		mCache = new LruCache<String, Bitmap>(maxSize) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes() * value.getHeight();
			}
		};
	}

	@Override
	public Bitmap getBitmap(String url) {
		return mCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		mCache.put(url, bitmap);
	}

	public void clearCache() {
		if (mCache != null) {
			mCache.evictAll();
		}
	}
}
