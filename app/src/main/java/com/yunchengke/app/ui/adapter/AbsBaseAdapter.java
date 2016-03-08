package com.yunchengke.app.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yunchengke.app.bean.TopicList;
import com.yunchengke.app.ui.view.OnNestedClickListener;

import java.util.List;

/**
 * 类名：AbsBaseAdapter <br/>
 * 描述：抽象的IAdapterView适配器
 * 创建时间：2016/01/10 14:44
 *
 * @author hanter
 * @version 1.0
 */
public abstract class AbsBaseAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mRows;
    protected OnNestedClickListener mOnNestedClickListener;

    public AbsBaseAdapter(Context context) {
        this.mContext = context;
    }

    public AbsBaseAdapter(Context context, OnNestedClickListener onNestedClickListener) {
        this.mContext = context;
        this.mOnNestedClickListener = onNestedClickListener;
    }

    @Override
    public int getCount() {
        if (mRows != null) {
            return mRows.size();
        }
        return 0;
    }

    @Override
    public T getItem(int position) {
        if (mRows != null) {
            return mRows.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<T> getRows() {
        return this.mRows;
    }

    public void setRows(List<T> rows) {
        this.mRows = rows;
    }
}
