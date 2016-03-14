package com.yunchengke.app.ui.adapter.daemon.myconsume;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.daemon.order.Resp_OrderLocalCityList;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.ui.activity.daemon.OrderLocalCityDetailActivity;
import com.yunchengke.app.ui.adapter.daemon.MyBaseAdapter;
import com.yunchengke.app.utils.daemon.SPUtil;

import java.util.List;


public class ConsumeLocalCityAdapter extends MyBaseAdapter {
	private List<Resp_OrderLocalCityList.Rows> list_rows;
	private Activity activity;
	public ConsumeLocalCityAdapter(Activity activity, List<Resp_OrderLocalCityList.Rows> list_rows) {
		super(activity);
        this.activity = activity;
		this.list_rows = list_rows;
	}

	@Override
	public int getCount() {
		return list_rows.size();
	}

	@Override
	public Resp_OrderLocalCityList.Rows getItem(int position) {
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
			convertView = LayoutInflater.from(activity).inflate(R.layout.item_my_consume_local_city, parent, false);

			holder.btn_my_consume_localCityPay = (Button) convertView
					.findViewById(R.id.btn_my_consume_localCityPay);
			
			holder.tv_my_consume_localCityPrice = (TextView) convertView
					.findViewById(R.id.tv_my_consume_localCityPrice);
			
			holder.tv_my_consume_localCityName = (TextView) convertView
					.findViewById(R.id.tv_my_consume_localCityName);

			holder.imageView_my_consume_localCityIcon = (ImageView)convertView.findViewById(R.id.imageView_my_consume_localCityIcon);

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
				.into(holder.imageView_my_consume_localCityIcon);
		convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(activity, OrderLocalCityDetailActivity.class);
				intent.putExtra(Constants.KEY_PARCELABLE, getItem(position));
				activity.startActivityForResult(intent, Constants.REQUEST_CODE_PAY_LOCAL_CITY);
			}
		});

		holder.btn_my_consume_localCityPay.setText(SPUtil.getPayStatus(activity).getString(getItem(position).X6_Product_Recommend,""));
        if(",1,".equals(getItem(position).X6_Product_Recommend)){
			holder.btn_my_consume_localCityPay.setTextColor(Color.WHITE);
			holder.btn_my_consume_localCityPay.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.ticket_title_corner));
		}else if(",2,".equals(getItem(position).X6_Product_Recommend)){
			holder.btn_my_consume_localCityPay.setTextColor(Color.WHITE);
			holder.btn_my_consume_localCityPay.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.ticket_title_corner));
			holder.btn_my_consume_localCityPay.setText("待确认");
		}else if(",3,".equals(getItem(position).X6_Product_Recommend)){
			holder.btn_my_consume_localCityPay.setTextColor(activity.getResources().getColor(R.color.ticket_font_gray));
			holder.btn_my_consume_localCityPay.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.button_disable_corner));
			holder.btn_my_consume_localCityPay.setText("已支付");
		}else{
			holder.btn_my_consume_localCityPay.setTextColor(activity.getResources().getColor(R.color.ticket_font_gray));
			holder.btn_my_consume_localCityPay.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.button_disable_corner));
		}

		holder.tv_my_consume_localCityPrice.setText("￥"+getItem(position).Field_HDBMFY);
		holder.tv_my_consume_localCityName.setText(getItem(position).Field_HDMC);

		return convertView;
	}

	static class ViewHolder {
		TextView tv_my_consume_localCityName, tv_my_consume_localCityPrice;
		ImageView imageView_my_consume_localCityIcon;
		Button btn_my_consume_localCityPay;
	}
}
