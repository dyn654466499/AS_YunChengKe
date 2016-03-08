package com.yunchengke.app.ui.adapter.daemon.myconsume;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.daemon.order.Resp_OrderCateringList;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.ui.activity.daemon.OrderCateringDetailActivity;
import com.yunchengke.app.ui.adapter.daemon.MyBaseAdapter;

import java.util.List;

import static com.yunchengke.app.consts.Constants.*;

public class ConsumeCateringAdapter extends MyBaseAdapter {
	private List<Resp_OrderCateringList.Rows> list_rows;
	private Activity activity;
	public ConsumeCateringAdapter(Activity activity, List<Resp_OrderCateringList.Rows> list_rows) {
		super(activity);
        this.activity = activity;
		this.list_rows = list_rows;
	}

	@Override
	public int getCount() {
		return list_rows.size();
	}

	@Override
	public Resp_OrderCateringList.Rows getItem(int position) {
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
			convertView = LayoutInflater.from(activity).inflate(R.layout.item_my_consume_catering, parent, false);

			holder.tv_my_consume_cateringType = (TextView) convertView
					.findViewById(R.id.tv_my_consume_cateringType);
			
			holder.tv_my_consume_cateringPrice = (TextView) convertView
					.findViewById(R.id.tv_my_consume_cateringPrice);
			
			holder.et_my_consume_cateringName = (EditText) convertView
					.findViewById(R.id.et_my_consume_cateringName);
			
			holder.tv_my_consume_cateringTime = (TextView) convertView
					.findViewById(R.id.tv_my_consume_cateringTime);
			
			holder.et_my_consume_cateringId = (EditText) convertView
					.findViewById(R.id.et_my_consume_cateringId);

			holder.imageView_my_consume_cateringIcon = (ImageView)convertView.findViewById(R.id.imageView_my_consume_cateringIcon);
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
				.into(holder.imageView_my_consume_cateringIcon);

		convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(activity, OrderCateringDetailActivity.class);
				intent.putExtra(Constants.KEY_ID, getItem(position).X6_Product_Id);
				intent.putExtra(Constants.KEY_TYPE, getItem(position).Field_Type);
				activity.startActivityForResult(intent, REQUEST_CODE_PAY_CATERING);
			}
		});

		holder.tv_my_consume_cateringType.setText(getItem(position).Field_Type);
		holder.tv_my_consume_cateringPrice.setText("￥"+getItem(position).Field_CYDDJE);
		holder.tv_my_consume_cateringTime.setText(getItem(position).X6_Product_Time);

		holder.et_my_consume_cateringId.setText(getItem(position).Field_CYDDBH);
		holder.et_my_consume_cateringName.setText(getItem(position).Field_DPMC);

		return convertView;
	}

	static class ViewHolder {
		TextView tv_my_consume_cateringType, tv_my_consume_cateringPrice,
				tv_my_consume_cateringTime;
		EditText et_my_consume_cateringName,et_my_consume_cateringId;
		ImageView imageView_my_consume_cateringIcon;
	}
}
