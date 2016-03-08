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
import com.yunchengke.app.bean.daemon.catering.Resp_CateringDishesList;
import com.yunchengke.app.interfaces.Commands;

import java.util.List;

/**
 * Created by Administrator on 2016/1/28.
 */
public class CateringDishesAdapter extends BaseAdapter{
    private List<Resp_CateringDishesList.Rows> resp_cateringDishesListRows;
    private Activity activity;
    private Commands commands;

    public void setItemCommands(Commands commands) {
        this.commands = commands;
    }

    public CateringDishesAdapter(Activity activity, List<Resp_CateringDishesList.Rows> resp_cateringDishesListRows) {
        this.activity = activity;
        this.resp_cateringDishesListRows = resp_cateringDishesListRows;
    }
    @Override
    public int getCount() {
        return resp_cateringDishesListRows.size();
    }

    @Override
    public Resp_CateringDishesList.Rows getItem(int position) {
        return resp_cateringDishesListRows.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_catering_dishes, parent, false);

            holder.tv_catering_dishes_name = (TextView) convertView
                    .findViewById(R.id.tv_catering_dishes_name);

            holder.tv_catering_dishes_price = (TextView) convertView
                    .findViewById(R.id.tv_catering_dishes_price);

            holder.imageView_catering_dishes_icon = (ImageView)convertView.findViewById(R.id.imageView_catering_dishes_icon);

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
                .into(holder.imageView_catering_dishes_icon);

        holder.tv_catering_dishes_name.setText(getItem(position).Field_CPPM);
        holder.tv_catering_dishes_price.setText("￥"+getItem(position).Field_CPDJ);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(commands!=null)commands.executeCommand(null);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView tv_catering_dishes_name,tv_catering_dishes_price;
        ImageView imageView_catering_dishes_icon;
    }
}
