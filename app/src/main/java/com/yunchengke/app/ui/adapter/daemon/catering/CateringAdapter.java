package com.yunchengke.app.ui.adapter.daemon.catering;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringList;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.ui.activity.daemon.CateringDetailActivity;
import com.yunchengke.app.ui.adapter.daemon.MyBaseAdapter;

import java.util.List;

public class CateringAdapter extends MyBaseAdapter {
	private List<Resp_CateringList.Rows> list_rows;
	private Activity activity;
	public CateringAdapter(Activity activity, List<Resp_CateringList.Rows> list_rows) {
		super(activity);
        this.activity = activity;
		this.list_rows = list_rows;
	}

	@Override
	public int getCount() {
		return list_rows.size();
	}

	@Override
	public Resp_CateringList.Rows getItem(int position) {
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
			convertView = LayoutInflater.from(activity).inflate(R.layout.item_catering, parent, false);

			holder.et_catering_merchant = (EditText) convertView
					.findViewById(R.id.et_catering_merchant);
			
			holder.et_catering_time = (EditText) convertView
					.findViewById(R.id.et_catering_time);
			
			holder.et_catering_address = (EditText) convertView
					.findViewById(R.id.et_catering_address);

			holder.imageView_catering_icon = (ImageView)convertView.findViewById(R.id.imageView_catering_icon);

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

		Glide.with(activity)
					.load(imageUrl)
					.into(holder.imageView_catering_icon);

		convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(activity, CateringDetailActivity.class);
				intent.putExtra(Constants.KEY_PARCELABLE, getItem(position));
				activity.startActivity(intent);
			}
		});

		holder.et_catering_time.setText(getItem(position).Field_DPYYSJ);

		holder.et_catering_address.setText(getItem(position).Field_DPDZ);
		holder.et_catering_merchant.setText(getItem(position).Field_DPMC);

		return convertView;
	}

	static class ViewHolder {
		EditText et_catering_merchant, et_catering_address,et_catering_time;
		ImageView imageView_catering_icon;
	}
}
