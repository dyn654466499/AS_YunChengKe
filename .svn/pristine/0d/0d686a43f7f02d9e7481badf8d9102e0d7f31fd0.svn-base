package com.yunchengke.app.ui.adapter.city;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.city.YCKCommentList;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.adapter.AbsBaseAdapter;

public class CommentListAdapter extends AbsBaseAdapter<YCKCommentList.RowsEntity> {

	public CommentListAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		UserItem user;
		if (convertView == null) {
			LayoutInflater flater = LayoutInflater.from(mContext);
			convertView = flater.inflate(R.layout.yck_comment_list_item, null);
			user = new UserItem();
			user.head = (ImageView) convertView.findViewById(R.id.comment_head);
		//	user.userName = (TextView) convertView.findViewById(R.id.yck_user_name);
			user.userTime = (TextView) convertView.findViewById(R.id.yck_user_time);
			user.title=(TextView) convertView.findViewById(R.id.yck_user_title);
			user.title2=(TextView) convertView.findViewById(R.id.yck_user_title2);
			convertView.setTag(user);
		} else {
			user = (UserItem) convertView.getTag();
		}
		final YCKCommentList.RowsEntity row = mRows.get(position);
		String url = HttpUrls.HTTP_USER_PORTRAIT_REQUEST + row.getX6_Product_Admin();
		Glide.with(mContext).load(url).into(user.head);
		final View finalConvertView = convertView;
		user.head.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				UIHelper.gotoUserInfoActivity(finalConvertView.getContext(),row.getX6_Product_Admin());
			}
		});
		user.userTime.setText(row.getField_PLSJ());
		user.title.setText(row.getField_PLYH());
		user.title2.setText(row.getField_PLNR());
		return convertView;
	}

	class UserItem {
		ImageView head;
		TextView userName;
		TextView userTime;
		TextView title;
		TextView title2;
	}

}
