package com.yunchengke.app.ui.adapter.daemon.catering;

import android.app.Activity;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringDishesList;
import com.yunchengke.app.interfaces.Commands;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/28.
 */
public class CateringBookDishesLargeAdapter extends BaseAdapter{
    private  ArrayList<Resp_CateringDishesList.Rows> resp_cateringDishesListRows;
    private Activity activity;
    private int position_classify = 0;
    private List<ArrayList<Integer>> dishes_nums;
    private Commands command;

    public void setDishesChangeCommand(Commands command) {
        this.command = command;
    }

    public CateringBookDishesLargeAdapter(Activity activity, ArrayList<Resp_CateringDishesList.Rows> resp_cateringDishesListRows) {
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

    public void setData(ArrayList<Resp_CateringDishesList.Rows> resp_cateringDishesListRows,List<ArrayList<Integer>> dishes_nums){
        this.resp_cateringDishesListRows = resp_cateringDishesListRows;
        this.dishes_nums = dishes_nums;
    }

    public void setPosition(int position){
        this.position_classify = position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
       // if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_catering_book_dishes_large, null, false);

            holder.et_catering_book_dishes_large_name = (EditText) convertView
                    .findViewById(R.id.et_catering_book_dishes_large_name);

            holder.tv_catering_book_dishes_large_price = (TextView) convertView
                    .findViewById(R.id.tv_catering_book_dishes_large_price);

            holder.tv_catering_book_dishes_large_nums = (TextView) convertView
                    .findViewById(R.id.tv_catering_book_dishes_large_nums);

            holder.imageView_catering_book_dishes_large_icon = (ImageView)convertView.findViewById(R.id.imageView_catering_book_dishes_large_icon);

            holder.imageBtn_catering_book_dishes_large_jian = (ImageButton)convertView.findViewById(R.id.imageBtn_catering_book_dishes_large_jian);

            holder.imageBtn_catering_book_dishes_large_jia = (ImageButton)convertView.findViewById(R.id.imageBtn_catering_book_dishes_large_jia);

//            convertView.setTag(holder);
//
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }

        String imageUrl = "";
        if(getItem(position).X6_Product_Pic.contains("http://www.icityto.com")){
            imageUrl = getItem(position).X6_Product_Pic;
        }else{
            imageUrl = "http://www.icityto.com"+getItem(position).X6_Product_Pic;
        }

        Glide.with(activity)
                .load(imageUrl)
                .into(holder.imageView_catering_book_dishes_large_icon);


        holder.et_catering_book_dishes_large_name.setText(getItem(position).Field_CPPM);
        holder.tv_catering_book_dishes_large_price.setText("￥" + getItem(position).Field_CPDJ);

        final ViewHolder finalHolder = holder;
        if(dishes_nums.get(position_classify)!=null) {
            if (dishes_nums.get(position_classify).get(position) == 0) {
                holder.tv_catering_book_dishes_large_nums.setVisibility(View.INVISIBLE);
                holder.imageBtn_catering_book_dishes_large_jian.setVisibility(View.INVISIBLE);

                holder.imageBtn_catering_book_dishes_large_jia.setImageResource(R.drawable.ic_jia_white);
                holder.imageBtn_catering_book_dishes_large_jia.setBackgroundColor(activity.getResources().getColor(R.color.catering_title_color));
            } else {
                holder.tv_catering_book_dishes_large_nums.setVisibility(View.VISIBLE);
                holder.tv_catering_book_dishes_large_nums.setText("x " + String.valueOf(dishes_nums.get(position_classify).get(position)));
                holder.imageBtn_catering_book_dishes_large_jian.setVisibility(View.VISIBLE);

                holder.imageBtn_catering_book_dishes_large_jia.setImageResource(R.drawable.ic_jia);
                holder.imageBtn_catering_book_dishes_large_jia.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.layout_frame_white));
            }
        }
        holder.imageBtn_catering_book_dishes_large_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dishes_nums.get(position_classify) != null) {
                    if (dishes_nums.get(position_classify).get(position) == 0) {
                        finalHolder.imageBtn_catering_book_dishes_large_jia.setImageResource(R.drawable.ic_jia);
                        finalHolder.imageBtn_catering_book_dishes_large_jia.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.layout_frame_white));
                        finalHolder.imageBtn_catering_book_dishes_large_jian.setVisibility(View.VISIBLE);
                    }
                    dishes_nums.get(position_classify).set(position, dishes_nums.get(position_classify).get(position) + 1);
                    finalHolder.tv_catering_book_dishes_large_nums.setVisibility(View.VISIBLE);
                    finalHolder.tv_catering_book_dishes_large_nums.setText("x " + String.valueOf(dishes_nums.get(position_classify).get(position)));

                    if (command != null) {
                        Message msg = new Message();
                        msg.what = 0; //0代表加
                        msg.arg1 = position;
                        msg.arg2 = dishes_nums.get(position_classify).get(position);
                        msg.obj = Float.valueOf(getItem(position).Field_CPDJ);
                        command.executeCommand(msg);
                    }
                }
            }
        });
        holder.imageBtn_catering_book_dishes_large_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dishes_nums.get(position_classify) != null) {
                    if (dishes_nums.get(position_classify).get(position) > 1) {
                        dishes_nums.get(position_classify).set(position, dishes_nums.get(position_classify).get(position) - 1);
                        finalHolder.tv_catering_book_dishes_large_nums.setVisibility(View.VISIBLE);
                        finalHolder.tv_catering_book_dishes_large_nums.setText("x " + String.valueOf(dishes_nums.get(position_classify).get(position)));

                        if (command != null) {
                            Message msg = new Message();
                            msg.what = 1; //1代表减
                            msg.arg1 = position;
                            msg.arg2 = dishes_nums.get(position_classify).get(position);
                            msg.obj = Float.valueOf(getItem(position).Field_CPDJ);
                            command.executeCommand(msg);
                        }
                    } else if (dishes_nums.get(position_classify).get(position) == 1) {
                        dishes_nums.get(position_classify).set(position, dishes_nums.get(position_classify).get(position) - 1);
                        finalHolder.imageBtn_catering_book_dishes_large_jia.setImageResource(R.drawable.ic_jia_white);
                        finalHolder.imageBtn_catering_book_dishes_large_jia.setBackgroundColor(activity.getResources().getColor(R.color.catering_title_color));
                        finalHolder.tv_catering_book_dishes_large_nums.setVisibility(View.INVISIBLE);
                        finalHolder.imageBtn_catering_book_dishes_large_jian.setVisibility(View.INVISIBLE);

                        if (command != null) {
                            Message msg = new Message();
                            msg.what = 1; //1代表减
                            msg.arg1 = position;
                            msg.arg2 = dishes_nums.get(position_classify).get(position);
                            msg.obj = Float.valueOf(getItem(position).Field_CPDJ);
                            command.executeCommand(msg);
                        }
                    }
                }
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView et_catering_book_dishes_large_name, tv_catering_book_dishes_large_price, tv_catering_book_dishes_large_nums;
        ImageView imageView_catering_book_dishes_large_icon;
        ImageButton imageBtn_catering_book_dishes_large_jian, imageBtn_catering_book_dishes_large_jia;
    }
}
