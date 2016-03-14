package com.yunchengke.app.ui.adapter.daemon.catering;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunchengke.app.R;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringBookClassify;
import com.yunchengke.app.utils.daemon.ScreenUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/1/29.
 */
public class CateringBookClassifyAdapter  extends BaseAdapter {
    private Context mContext;
    private List<Resp_CateringBookClassify.Rows> rowsList;
    private int position;

    public CateringBookClassifyAdapter(Context mContext, List<Resp_CateringBookClassify.Rows> rowsList) {
        this.mContext = mContext;
        this.rowsList = rowsList;
    }

    @Override
    public int getCount() {
        return rowsList.size();
    }

    @Override
    public Resp_CateringBookClassify.Rows getItem(int position) {
        return rowsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void setPosition(int position){
        this.position = position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            //convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);
            convertView = new LinearLayout(mContext);
            int padding = 5;
            convertView.setPadding(ScreenUtil.dip2px(mContext,padding),
                    0,
                    ScreenUtil.dip2px(mContext,padding),
                    0);
            holder.tv_catering_book_classify = (TextView)LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);
            holder.tv_catering_book_classify.setTextColor(mContext.getResources().getColor(R.color.ticket_black));
            holder.tv_catering_book_classify.setTextSize(14);
            ((ViewGroup)convertView).addView(holder.tv_catering_book_classify);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_catering_book_classify.setText(getItem(position).Field_CPFL);
        if(this.position==position){
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.ticket_white));
        }else{
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.catering_bg_gray));
        }
        return convertView;
    }
    static class ViewHolder {
        TextView tv_catering_book_classify;
    }

}
