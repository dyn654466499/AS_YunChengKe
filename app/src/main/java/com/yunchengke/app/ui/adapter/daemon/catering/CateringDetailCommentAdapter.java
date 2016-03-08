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
import com.yunchengke.app.bean.daemon.catering.Resp_CateringDetailComment;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.view.daemon.GlideCircleTransform;

/**
 * Created by 邓耀宁 on 2016/1/28.
 */
public class CateringDetailCommentAdapter extends BaseAdapter{
    private Resp_CateringDetailComment resp_cateringDetailComment;
    private Activity activity;
    public CateringDetailCommentAdapter(Activity activity, Resp_CateringDetailComment resp_cateringDetailComment) {
        this.activity = activity;
        this.resp_cateringDetailComment = resp_cateringDetailComment;
    }
    @Override
    public int getCount() {
        return resp_cateringDetailComment.rows.size() > 3 ? 3 : resp_cateringDetailComment.rows.size();
    }

    @Override
    public Resp_CateringDetailComment.Rows getItem(int position) {
        return resp_cateringDetailComment.rows.get(position);
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
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_catering_detail_comment, parent, false);

            holder.tv_catering_detail_comment_user = (TextView) convertView
                    .findViewById(R.id.tv_catering_detail_comment_user);

            holder.tv_catering_detail_comment_date = (TextView) convertView
                    .findViewById(R.id.tv_catering_detail_comment_date);

            holder.tv_catering_detail_comment_desc = (TextView) convertView
                    .findViewById(R.id.tv_catering_detail_comment_desc);


            holder.imageView_catering_detail_comment_head = (ImageView)convertView.findViewById(R.id.imageView_catering_detail_comment_head);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String imageUrl = "www.icityto.com/X_UserFileLogic/X_yesicity2015/X_TX/?TX=" + getItem(position).X6_Product_Admin;
        Glide.with(activity)
                .load(imageUrl)
                .transform(new GlideCircleTransform(activity))
                 .placeholder(R.drawable.comment_default_head)
                 .into(holder.imageView_catering_detail_comment_head);
        holder.imageView_catering_detail_comment_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 跳转
                 */
                UIHelper.gotoUserInfoActivity(activity, Long.valueOf(getItem(position).X6_Product_Admin));
            }
        });

        holder.tv_catering_detail_comment_user.setText(getItem(position).Field_PLYH);

        holder.tv_catering_detail_comment_date.setText(getItem(position).Field_PLSJ);

        holder.tv_catering_detail_comment_desc.setText(getItem(position).Field_PLNR);


        return convertView;
    }

    static class ViewHolder {
        TextView tv_catering_detail_comment_user,tv_catering_detail_comment_date,tv_catering_detail_comment_desc;
        ImageView imageView_catering_detail_comment_head;
    }
}
