package com.yunchengke.app.utils;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;

import android.content.Context;
import android.widget.ImageView;

public class ImageLoadManager {
	private static ImageLoadManager instance;

	private RequestQueue mQueue;

	private ImageLoader imageLoader;

	private BitmapCache mBitmapCache;

	private Context mContext;

	private ImageLoadManager(Context contxt) {
		mContext = contxt;
		mQueue = Volley.newRequestQueue(mContext);
		mBitmapCache = new BitmapCache();
		imageLoader = new ImageLoader(mQueue, mBitmapCache);
	}

	public static ImageLoadManager getInstance(Context context) {
		if (instance == null) {
			instance = new ImageLoadManager(context);
		}
		return instance;
	}

	public void init() {
		mQueue = Volley.newRequestQueue(mContext);
		mBitmapCache = new BitmapCache();
		imageLoader = new ImageLoader(mQueue, mBitmapCache);
	}

	public ImageLoader getmImageLoader() {
		return imageLoader;
	}

	public void loadImage(final ImageView view, final int defaultImageResId, final int errorImageResId, String url) {
		if (mQueue == null) {
			init();
		}
		ImageListener listener = ImageLoader.getImageListener(view, defaultImageResId, errorImageResId);
		imageLoader.get(url, listener);
	}

	public void loadImage(String url, ImageListener listener) {
		if (mQueue == null) {
			init();
		}
		imageLoader.get(url, listener);
	}

}
