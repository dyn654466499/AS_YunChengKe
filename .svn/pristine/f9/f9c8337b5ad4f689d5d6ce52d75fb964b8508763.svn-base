package com.yunchengke.app.ui.adapter.city;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yunchengke.app.R;

import java.util.ArrayList;
import java.util.Map;

public class YCKPhotoAdapter extends BaseAdapter {


	private LayoutInflater mInflater;

	private Context mContext;
	final ArrayList<Map<String, String>> picUrlList = new ArrayList<Map<String, String>>();


	public YCKPhotoAdapter(Context contxt, ArrayList<Map<String, String>> data) {
		super();
		mContext = contxt;
		mInflater = LayoutInflater.from(mContext);
		picUrlList.addAll(data);
		
	}


	@Override
	public int getCount() {
		return picUrlList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		PhotoItem item;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.layout_yck_photo_item, null);
			item = new PhotoItem();
			item.photoItem = (ImageView) convertView.findViewById(R.id.thought_drawable_id);
			convertView.setTag(item);
		} else {
			item = (PhotoItem) convertView.getTag();
		}

		String url = picUrlList.get(position).get("small");
		Glide.with(mContext).load(url).into(item.photoItem);
		return convertView;
	}

	class PhotoItem {
		ImageView photoItem;
	}

}
