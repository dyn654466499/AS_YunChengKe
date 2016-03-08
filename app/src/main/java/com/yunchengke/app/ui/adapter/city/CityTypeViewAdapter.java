package com.yunchengke.app.ui.adapter.city;


import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yunchengke.app.R;
import com.yunchengke.app.bean.city.CityType;

import java.util.ArrayList;
import java.util.List;

public class CityTypeViewAdapter extends BaseAdapter {
    private List<CityType.RowsEntity> list = new ArrayList<CityType.RowsEntity>();
    private LayoutInflater layoutInflater;
    private Context context;
    private String selectorText;
    private float textSize = -1;
    private int selectorResId;
    private int normalResId;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(CityTypeViewAdapter adapter, int position);
    }

    public void setOnItemClickListener(OnItemClickListener l) {
        mOnItemClickListener = l;
    }

    public CityTypeViewAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setList(List<CityType.RowsEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setTextSize(float tSize) {
        textSize = tSize;
    }

    public void setSelectorText(String text) {
        selectorText = text;
    }

    public void setSelectorResId(int resId, int nresId) {
        selectorResId = resId;
        normalResId = nresId;
    }

    public void setSelectedPositionNotify(int position) {
        if (list != null && list.size() > 0) {
            selectorText = list.get(position).getField_HDFL();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView view;
        if (convertView == null) {
            view = (TextView) layoutInflater.inflate(R.layout.expand_tab_popview_item1_layout, null);
        } else {
            view = (TextView) convertView;
        }

        CityType.RowsEntity keyValueBean = (CityType.RowsEntity) getItem(position);
        if (keyValueBean.getField_HDFL().equals(selectorText)) {
            view.setBackgroundResource(selectorResId);
        } else {
            view.setBackgroundResource(normalResId);
        }
        int pading = context.getResources().getDimensionPixelSize(R.dimen.expand_tab_popview_padingtop);

        view.setText(keyValueBean.getField_HDFL());
        //view.setTag(position);
        if(textSize != -1) {
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        }
        view.setPadding(pading, pading, 0, pading);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    TextView txtView = (TextView) view;
                    setSelectorText(txtView.getText().toString());
                    setSelectedPositionNotify(position);
                    mOnItemClickListener.onItemClick(CityTypeViewAdapter.this, position);
                }
            }
        });

        return view;
    }
}
