package com.yunchengke.app.ui.adapter.daemon.catering;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringDetailDishes;
import com.yunchengke.app.interfaces.Commands;

/**
 * Created by Administrator on 2016/1/28.
 */
public class CateringDetailDishesAdapter extends BaseAdapter{
    private Resp_CateringDetailDishes resp_cateringDishesDetail;
    private Activity activity;
    private Commands itemCommands;
    public void setItemCommands(Commands itemCommands){
        this.itemCommands = itemCommands;
    }


    public CateringDetailDishesAdapter(Activity activity, Resp_CateringDetailDishes resp_cateringDishesDetail) {
        this.activity = activity;
        this.resp_cateringDishesDetail = resp_cateringDishesDetail;
    }
    @Override
    public int getCount() {
        return resp_cateringDishesDetail.rows.size();
    }

    @Override
    public Resp_CateringDetailDishes.Rows getItem(int position) {
        return resp_cateringDishesDetail.rows.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_catering_detail_dishes, parent, false);

            holder.tv_catering_detail_dishes_name = (TextView) convertView
                    .findViewById(R.id.tv_catering_detail_dishes_name);

            holder.imageView_catering_detail_dishes_icon = (ImageView)convertView.findViewById(R.id.imageView_catering_detail_dishes_icon);

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
                .into(holder.imageView_catering_detail_dishes_icon);


        holder.tv_catering_detail_dishes_name.setText(getItem(position).Field_CPPM);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemCommands!=null)itemCommands.executeCommand(null);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView tv_catering_detail_dishes_name;
        ImageView imageView_catering_detail_dishes_icon;
    }
}
