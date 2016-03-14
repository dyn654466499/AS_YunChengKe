package com.yunchengke.app.ui.adapter.daemon.myconsume;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yunchengke.app.R;
import com.yunchengke.app.bean.daemon.order.Resp_OrderTicketQueryInfo;
import com.yunchengke.app.ui.activity.daemon.OrderTicketDetailActivity;
import com.yunchengke.app.ui.adapter.daemon.MyBaseAdapter;

import java.util.List;

import static com.yunchengke.app.consts.Constants.KEY_PARCELABLE;
import static com.yunchengke.app.consts.Constants.REQUEST_CODE_PAY_TICKET;

public class ConsumeTicketAdapter extends MyBaseAdapter {
	private List<Resp_OrderTicketQueryInfo> infos;
	private Activity activity;

	public ConsumeTicketAdapter(Activity activity, List<Resp_OrderTicketQueryInfo> objects) {
		super(activity);
        this.activity = activity;
		infos = objects;
	}

	@Override
	public int getCount() {
		return infos.size();
	}

	@Override
	public Resp_OrderTicketQueryInfo getItem(int position) {
		return infos.get(position);
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
			convertView = LayoutInflater.from(activity).inflate(R.layout.item_my_consume_ticket, parent, false);

			holder.tv_my_consume_ticketStatus = (TextView) convertView
					.findViewById(R.id.tv_my_consume_ticketStatus);
			
			holder.tv_my_consume_Scity = (TextView) convertView
					.findViewById(R.id.tv_my_consume_Scity);
			
			holder.tv_my_consume_Ecity = (TextView) convertView
					.findViewById(R.id.tv_my_consume_Ecity);
			
			holder.tv_my_consume_flightTime = (TextView) convertView
					.findViewById(R.id.tv_my_consume_flightTime);
			
			holder.tv_my_consume_Sdate = (TextView) convertView
					.findViewById(R.id.tv_my_consume_Sdate);
			
			holder.tv_my_consume_flightType = (TextView) convertView
					.findViewById(R.id.tv_my_consume_flightType);

			holder.tv_my_consume_ticketPrice = (TextView) convertView
					.findViewById(R.id.tv_my_consume_ticketPrice);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(activity, OrderTicketDetailActivity.class);
				intent.putExtra(KEY_PARCELABLE, getItem(position));
				activity.startActivityForResult(intent, REQUEST_CODE_PAY_TICKET);
			}
		});
		holder.tv_my_consume_ticketStatus.setText(sp_orderStatus.getString(getItem(position).Status, ""));
		 holder.tv_my_consume_Scity.setText(sp_city.getString(getItem(position).Scity,""));
		 holder.tv_my_consume_ticketPrice.setText("￥" +getItem(position).PayMoney);

		 holder.tv_my_consume_Sdate.setText(getItem(position).Date);
		 holder.tv_my_consume_flightTime.setText(
				 getItem(position).Stime.trim().replace(getItem(position).Date,"")+
						 "至"+
				 getItem(position).Etime.trim().replace(getItem(position).Date,"")
		 );
		Log.e("test ticket order","getItem(position).PayMoney="+getItem(position).PayMoney+",getItem(position).OrderNo="+getItem(position).OrderNo);
		 holder.tv_my_consume_flightType.setText(getItem(position).Flight);
		holder.tv_my_consume_Ecity.setText(sp_city.getString(getItem(position).Ecity,""));

		return convertView;
	}

	static class ViewHolder {
		TextView tv_my_consume_ticketStatus, tv_my_consume_Scity,
				tv_my_consume_Ecity, tv_my_consume_Sdate,
				tv_my_consume_flightTime, tv_my_consume_flightType,
				tv_my_consume_ticketPrice;
	}
}
