package com.yunchengke.app.ui.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.yunchengke.app.R;
import com.yunchengke.app.bean.CategoryList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.request.CategoryListRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.adapter.AbsBaseAdapter;
import com.yunchengke.app.ui.widget.SplitLineGridView;

/**
 * 类名：CategoryView <br/>
 * 描述：分类-小组
 * 创建时间：2016/01/07 21:59
 *
 * @author hanter
 * @version 1.0
 */
public class CategoryView extends BaseCompoundView {

    public static final int CATEGORY_TYPE_GOTO_GROUP = 0;
    public static final int CATEGORY_TYPE_CREATE_GROUP = 1;

    private Activity mActivity;

    private int mCategoryType = CATEGORY_TYPE_GOTO_GROUP; // 跳转类型

    private TextView tvCategoryAll;
    private GridView gdvCategoryList;
    private CategoryListAdapter categoryCategoryListAdapter;

    public CategoryView(Context context) {
        super(context);
    }

    public CategoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CategoryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CategoryView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initViews() {
        tvCategoryAll = (TextView) findViewById(R.id.tv_view_category_all);

        gdvCategoryList = (GridView) findViewById(R.id.gdv_view_category_content);
        gdvCategoryList.setOverScrollMode(View.OVER_SCROLL_NEVER);
        gdvCategoryList.setHorizontalScrollBarEnabled(false);

        gdvCategoryList.setBackgroundResource(R.color.white);
        gdvCategoryList.setSelector(R.drawable.bg_common_item);

        categoryCategoryListAdapter = new CategoryListAdapter(getContext());
        gdvCategoryList.setAdapter(categoryCategoryListAdapter);

        gdvCategoryList.setOverScrollMode(View.OVER_SCROLL_NEVER);

        gdvCategoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CategoryList.RowsEntity row = categoryCategoryListAdapter.getRows().get(position);

                if (mCategoryType == CATEGORY_TYPE_CREATE_GROUP) {
                    UIHelper.gotoCreateGroupActivity(mActivity, row.getX6_Product_Id(), row.getField_XZFL());
                } else if (mCategoryType == CATEGORY_TYPE_GOTO_GROUP) {
                    UIHelper.gotoCategoryDetailActivity(getContext(), row.getX6_Product_Id());
                }
            }
        });


        postDelayed(new Runnable() {
            @Override
            public void run() {
                fetchData();
            }
        }, 100);
    }

    public void fetchData() {
        CategoryListRequest req = new CategoryListRequest(new HttpRequestListener<CategoryList>() {
            @Override
            public void onResponse(CategoryList response) {
                super.onResponse(response);

                categoryCategoryListAdapter.setRows(response.getRows());
                categoryCategoryListAdapter.notifyDataSetChanged();
            }
        });
        HttpRequestQueue.addToRequestQueue(req);
    }

    @Override
    public int getContentLayoutResourceId() {
        return R.layout.view_category;
    }

    public int getCategoryType() {
        return mCategoryType;
    }

    public void setCategoryType(int categoryType) {
        mCategoryType = categoryType;

        if (mCategoryType == CATEGORY_TYPE_CREATE_GROUP) {
            tvCategoryAll.setVisibility(View.GONE);
        } else if (mCategoryType == CATEGORY_TYPE_GOTO_GROUP) {
            tvCategoryAll.setVisibility(View.VISIBLE);
        }
    }

    public void setActivity(Activity activity) {
        mActivity = activity;
    }

    static class CategoryListAdapter extends AbsBaseAdapter<CategoryList.RowsEntity> {

        public CategoryListAdapter(Context context) {
            super(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(
                        R.layout.item_category_list, parent, false);

                holder = new ViewHolder();

                holder.name = (TextView) convertView
                        .findViewById(R.id.tv_item_category_name);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            CategoryList.RowsEntity row = mRows.get(position);

            holder.name.setText(row.getField_XZFL());

            return convertView;
        }

        class ViewHolder {
            TextView name;
        }
    }
}
