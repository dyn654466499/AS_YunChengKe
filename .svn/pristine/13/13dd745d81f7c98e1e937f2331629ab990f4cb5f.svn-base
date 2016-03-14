package com.yunchengke.app.ui.adapter.daemon.catering;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringActivityList;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.adapter.daemon.MyBaseAdapter;

import java.util.List;

public class CateringActivityAdapter extends MyBaseAdapter {
	private List<Resp_CateringActivityList.Rows> list_rows;
	private Activity activity;
	public CateringActivityAdapter(Activity activity, List<Resp_CateringActivityList.Rows> list_rows) {
		super(activity);
        this.activity = activity;
		this.list_rows = list_rows;
	}

	@Override
	public int getCount() {
		return list_rows.size();
	}

	@Override
	public Resp_CateringActivityList.Rows getItem(int position) {
		return list_rows.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(activity).inflate(R.layout.item_catering_activity, parent, false);

			holder.tv_catering_activity_personNum = (TextView) convertView
					.findViewById(R.id.tv_catering_activity_personNum);
			
			holder.tv_catering_activity_attentionNum = (TextView) convertView
					.findViewById(R.id.tv_catering_activity_attentionNum);
			
			holder.tv_catering_activity_title = (TextView) convertView
					.findViewById(R.id.tv_catering_activity_title);

			holder.tv_catering_activity_type = (TextView) convertView
					.findViewById(R.id.tv_catering_activity_type);

			holder.tv_catering_activity_address = (TextView) convertView
					.findViewById(R.id.tv_catering_activity_address);

			holder.tv_catering_activity_time = (TextView) convertView
					.findViewById(R.id.tv_catering_activity_time);

			holder.imageView_catering_activity_icon = (ImageView)convertView.findViewById(R.id.imageView_catering_activity_icon);

			holder.linearLayout_catering_activity_content = (LinearLayout)convertView.findViewById(R.id.linearLayout_catering_activity_content);
			holder.linearLayout_catering_activity_blank = (LinearLayout)convertView.findViewById(R.id.linearLayout_catering_activity_blank);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		String imageUrl = "";
		if(getItem(position).X6_Product_Pic.contains("http://www.icityto.com")){
			imageUrl = getItem(position).X6_Product_Pic;
		}else{
			imageUrl = "http://www.icityto.com"+getItem(position).X6_Product_Pic;
		}

		//Log.e("sdfsdfsdf",imageUrl);
		Glide.with(activity)
					.load(imageUrl)
					.into(holder.imageView_catering_activity_icon);

		convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/**
				 * 跳转到活动详情
				 */
				UIHelper.gotoCityDetailsActivity(activity,Integer.valueOf(getItem(position).X6_Product_Id));
			}
		});

		holder.tv_catering_activity_personNum.setText(getItem(position).Field_HDBMRS + "人参加");

		holder.tv_catering_activity_attentionNum.setText(getItem(position).gzrs + "人关注");
		holder.tv_catering_activity_title.setText(getItem(position).Field_HDMC);
		holder.tv_catering_activity_type.setText(getItem(position).Field_HDFL);
		holder.tv_catering_activity_address.setText(getItem(position).Field_HDDD);
		holder.tv_catering_activity_time.setText(getItem(position).Field_HDSJ);

		FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) holder.linearLayout_catering_activity_blank.getLayoutParams();
		params.width = holder.linearLayout_catering_activity_content.getMeasuredWidth();
		params.height = holder.linearLayout_catering_activity_content.getMeasuredHeight();
		params.gravity = Gravity.END | Gravity.BOTTOM;
		holder.linearLayout_catering_activity_blank.setLayoutParams(params);
		return convertView;
	}

	static class ViewHolder {
		LinearLayout linearLayout_catering_activity_content,linearLayout_catering_activity_blank;
		TextView tv_catering_activity_personNum,tv_catering_activity_attentionNum,tv_catering_activity_title,
		          tv_catering_activity_type,tv_catering_activity_address,tv_catering_activity_time;
		ImageView imageView_catering_activity_icon;
	}
}
