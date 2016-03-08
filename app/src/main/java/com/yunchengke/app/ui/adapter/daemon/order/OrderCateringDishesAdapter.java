package com.yunchengke.app.ui.adapter.daemon.order;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.daemon.order.Resp_OrderCateringDishesDetail;


/**
 * Created by Administrator on 2016/1/26.
 */
public class OrderCateringDishesAdapter extends BaseAdapter{
    private Activity activity;
    private Resp_OrderCateringDishesDetail detail;
    public OrderCateringDishesAdapter(Activity activity, Resp_OrderCateringDishesDetail detail){
        this.activity = activity;
        this.detail = detail;
    }

    @Override
    public int getCount() {
        return detail.rows.size();
    }

    @Override
    public Resp_OrderCateringDishesDetail.Rows getItem(int position) {
        return detail.rows.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_order_catering_detail_dish,null,false);
            holder.tv_order_catering_detail_dishName = (TextView)convertView.findViewById(R.id.tv_order_catering_detail_dishName);
            holder.tv_order_catering_detail_dishPrice = (TextView)convertView.findViewById(R.id.tv_order_catering_detail_dishPrice);
            holder.tv_order_catering_detail_dishCount = (TextView)convertView.findViewById(R.id.tv_order_catering_detail_dishCount);
            holder.imageView_order_catering_detail_dishIcon = (ImageView)convertView.findViewById(R.id.imageView_order_catering_detail_dishIcon);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        String imageUrl = "";
        if(getItem(position).X6_Product_Pic.contains("http://www.icityto.com")){
            imageUrl = getItem(position).X6_Product_Pic;
        }else{
            imageUrl = "http://www.icityto.com"+getItem(position).X6_Product_Pic;
        }

        Glide.with(activity)
                .load(imageUrl)
                .into(holder.imageView_order_catering_detail_dishIcon);
        holder.tv_order_catering_detail_dishName.setText(getItem(position).Field_CPPM);
        holder.tv_order_catering_detail_dishPrice.setText("￥"+getItem(position).Field_CPDJ);
        holder.tv_order_catering_detail_dishCount.setText("x"+getItem(position).Field_CPSL);
        return  convertView;
    }
    class ViewHolder{
        ImageView imageView_order_catering_detail_dishIcon;
        TextView tv_order_catering_detail_dishName,tv_order_catering_detail_dishPrice,
                tv_order_catering_detail_dishCount;
    }
}
