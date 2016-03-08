package com.yunchengke.app.ui.adapter.daemon.catering;

import android.app.Activity;
import android.content.Intent;
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
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.interfaces.Commands;
import com.yunchengke.app.ui.activity.daemon.CateringBookDishesLargeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/28.
 */
public class CateringBookDishesAdapter extends BaseAdapter{
    private  ArrayList<Resp_CateringDishesList.Rows> resp_cateringDishesListRows;
    private Activity activity;
    private int position_classify = 0;
    private float total_price = 0;
    private int total_count = 0;
    private List<ArrayList<Integer>> dishes_nums;
    private Commands command;

    public void setDishesChangeCommand(Commands command) {
        this.command = command;
    }

    public CateringBookDishesAdapter(Activity activity, ArrayList<Resp_CateringDishesList.Rows> resp_cateringDishesListRows) {
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

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_catering_book_dishes, parent, false);

            holder.et_catering_book_dishes_name = (EditText) convertView
                    .findViewById(R.id.et_catering_book_dishes_name);

            holder.tv_catering_book_dishes_price = (TextView) convertView
                    .findViewById(R.id.tv_catering_book_dishes_price);

            holder.tv_catering_book_dishes_nums = (TextView) convertView
                    .findViewById(R.id.tv_catering_book_dishes_nums);

            holder.imageView_catering_book_dishes_icon = (ImageView)convertView.findViewById(R.id.imageView_catering_book_dishes_icon);

            holder.imageBtn_catering_book_dishes_jian = (ImageButton)convertView.findViewById(R.id.imageBtn_catering_book_dishes_jian);

            holder.imageBtn_catering_book_dishes_jia = (ImageButton)convertView.findViewById(R.id.imageBtn_catering_book_dishes_jia);

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
                .into(holder.imageView_catering_book_dishes_icon);

        holder.imageView_catering_book_dishes_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, CateringBookDishesLargeActivity.class);
                intent.putParcelableArrayListExtra(Constants.KEY_PARCELABLE, resp_cateringDishesListRows);
                intent.putIntegerArrayListExtra(Constants.KEY_INTEGER_LIST, dishes_nums.get(position_classify));
                intent.putExtra(Constants.KEY_POSITION, position);
                intent.putExtra(Constants.KEY_PRICE, total_price);
                intent.putExtra(Constants.KEY_COUNT, total_count);
                activity.startActivityForResult(intent, Constants.REQUEST_CODE_CATERING_BOOK_DISHES_LARGE);
                activity.overridePendingTransition(0,0);
            }
        });

        holder.et_catering_book_dishes_name.setText(getItem(position).Field_CPPM);
        holder.tv_catering_book_dishes_price.setText("￥"+getItem(position).Field_CPDJ);

        final ViewHolder finalHolder = holder;
        if(dishes_nums.get(position_classify)!=null) {
            if (dishes_nums.get(position_classify).get(position) == 0) {
                holder.tv_catering_book_dishes_nums.setText("");
                holder.imageBtn_catering_book_dishes_jian.setVisibility(View.GONE);

                holder.imageBtn_catering_book_dishes_jia.setImageResource(R.drawable.ic_jia_white);
                holder.imageBtn_catering_book_dishes_jia.setBackgroundColor(activity.getResources().getColor(R.color.catering_title_color));
            } else {
                holder.tv_catering_book_dishes_nums.setText("x " + String.valueOf(dishes_nums.get(position_classify).get(position)));
                holder.imageBtn_catering_book_dishes_jian.setVisibility(View.VISIBLE);

                holder.imageBtn_catering_book_dishes_jia.setImageResource(R.drawable.ic_jia);
                holder.imageBtn_catering_book_dishes_jia.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.layout_frame_white));
            }
        }
        holder.imageBtn_catering_book_dishes_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dishes_nums.get(position_classify) != null) {
                    if (dishes_nums.get(position_classify).get(position) == 0) {
                        finalHolder.imageBtn_catering_book_dishes_jia.setImageResource(R.drawable.ic_jia);
                        finalHolder.imageBtn_catering_book_dishes_jia.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.layout_frame_white));
                        finalHolder.imageBtn_catering_book_dishes_jian.setVisibility(View.VISIBLE);
                    }
                    dishes_nums.get(position_classify).set(position, dishes_nums.get(position_classify).get(position) + 1);
                    finalHolder.tv_catering_book_dishes_nums.setText("x " + String.valueOf(dishes_nums.get(position_classify).get(position)));

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
        holder.imageBtn_catering_book_dishes_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dishes_nums.get(position_classify) != null) {
                    if (dishes_nums.get(position_classify).get(position) > 1) {
                        dishes_nums.get(position_classify).set(position, dishes_nums.get(position_classify).get(position) - 1);
                        finalHolder.tv_catering_book_dishes_nums.setText("x " + String.valueOf(dishes_nums.get(position_classify).get(position)));

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
                        finalHolder.imageBtn_catering_book_dishes_jia.setImageResource(R.drawable.ic_jia_white);
                        finalHolder.imageBtn_catering_book_dishes_jia.setBackgroundColor(activity.getResources().getColor(R.color.catering_title_color));
                        finalHolder.tv_catering_book_dishes_nums.setText("");
                        finalHolder.imageBtn_catering_book_dishes_jian.setVisibility(View.GONE);

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
        TextView et_catering_book_dishes_name,tv_catering_book_dishes_price,tv_catering_book_dishes_nums;
        ImageView imageView_catering_book_dishes_icon;
        ImageButton imageBtn_catering_book_dishes_jian,imageBtn_catering_book_dishes_jia;
    }
}
