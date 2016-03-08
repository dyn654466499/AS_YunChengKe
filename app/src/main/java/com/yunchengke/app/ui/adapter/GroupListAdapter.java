package com.yunchengke.app.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.GroupList;
import com.yunchengke.app.http.HttpUrls;

/**
 * 类名：GroupListAdapter <br/>
 * 描述：小组列表适配器
 * 创建时间：2016/01/16 16:44
 *
 * @author hanter
 * @version 1.0
 */
public class GroupListAdapter extends AbsBaseAdapter<GroupList.RowsEntity> {

    public GroupListAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_group_list, parent, false);

            holder = new ViewHolder();

            holder.image = (ImageView) convertView
                    .findViewById(R.id.iv_item_group_image);
            holder.title = (TextView) convertView
                    .findViewById(R.id.tv_item_group_title);
            holder.content = (TextView) convertView
                    .findViewById(R.id.tv_item_group_content);
            holder.people = (TextView) convertView
                    .findViewById(R.id.tv_item_group_people);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GroupList.RowsEntity row = mRows.get(position);

        holder.title.setText(row.getField_XZBT());
        holder.content.setText(row.getField_XZJJ());

        String memberCount = mContext.getResources().getString(R.string.group_list_member_count, row.getField_YHSL());
        holder.people.setText(memberCount);

        Glide.with(mContext)
                .load(row.getX6_Product_Pic())
                .into(holder.image);

        return convertView;
    }

    class ViewHolder {
        ImageView image;
        TextView title; // 标题
        TextView content; // 小组名
        TextView people; // 人数
    }
}

