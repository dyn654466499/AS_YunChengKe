package com.yunchengke.app.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Tinchy
 * 创建时间：2016/1/14 16:16
 * 描述：
 * 版本：1.0
 */
public abstract class HomeAdapter<T> extends BaseAdapter{
    protected List<T> listData;
    protected LayoutInflater inflater;
    protected int layoutResID;

    protected HomeAdapter(Context context, List<T> list, int layoutID) {
        if (list == null) list = new ArrayList<T>();
        inflater = LayoutInflater.from(context);
        listData = new ArrayList<T>();
        listData.addAll(list);
        layoutResID = layoutID;
    }

    protected HomeAdapter(Context context, List<T> list, int layoutID, AbsListView listview) {
        if (list == null) list = new ArrayList<T>();
        inflater = LayoutInflater.from(context);
        listData = new ArrayList<T>();
        listData.addAll(list);
        layoutResID = layoutID;
    }

    /**
     * 修改所有数据
     *
     * @param list
     */
    public void updateALLData(List<T> list) {
        if (list == null) return;
        listData.clear();
        listData.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listData == null ? 0 : listData.size();
    }

    @Override
    public T getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return newView(convertView == null ? inflater.inflate(layoutResID, null) : convertView, listData == null || listData.size() < 1 ? null : listData.get(position), position);
    }

    protected abstract View newView(View convertView, T t, int position);
}
