package com.yunchengke.app.ui.adapter.city;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.city.YCKJoinedList;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.ui.adapter.AbsBaseAdapter;
import com.yunchengke.app.ui.base.AbsBaseActivity;

import java.util.ArrayList;

public class JoinedListAdapter extends AbsBaseAdapter<YCKJoinedList.RowsEntity> {

	public JoinedListAdapter(Context context) {
		super(context);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		UserItem user;
		if (convertView == null) {
			LayoutInflater flater = LayoutInflater.from(mContext);
			convertView = flater.inflate(R.layout.yck_joined_list_item, null);
			user = new UserItem();
			user.head = (ImageView) convertView.findViewById(R.id.join_head);
			user.userName = (TextView) convertView.findViewById(R.id.yck_user_name);
			user.userTime = (TextView) convertView.findViewById(R.id.yck_user_time);
			convertView.setTag(user);
		} else {
			user = (UserItem) convertView.getTag();
		}
		YCKJoinedList.RowsEntity rowsEntity = getItem(position);
		user.userTime.setText(rowsEntity.getJoinTime());
		user.userName.setText(rowsEntity.getField_YHNC());
		String url = HttpUrls.HTTP_USER_PORTRAIT_REQUEST + rowsEntity.getX6_Admin_Id();
		Glide.with(mContext).load(url).placeholder(R.drawable.ic_default_portrait).into(user.head);
		return convertView;
	}

	class UserItem {
		ImageView head;
		TextView userName;
		TextView userTime;
	}

}
