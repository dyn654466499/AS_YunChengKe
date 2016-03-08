package com.yunchengke.app.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.lee.pullrefresh.PullToRefreshListView;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.BaseEntity;
import com.yunchengke.app.bean.GroupList;
import com.yunchengke.app.bean.GroupSectionInfo;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.request.MyCreatedGroupListRequest;
import com.yunchengke.app.http.request.MyJoinedGroupListRequest;
import com.yunchengke.app.ui.UIHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 类名：MyGroupView <br/>
 * 描述：我的小组
 * 创建时间：2016/01/07 22:00
 *
 * @author hanter
 * @version 1.0
 */
public class MyGroupView extends BaseCompoundView {

    ListView lvMyGroup;

    List<GroupList.RowsEntity> mCreatedGroupList;
    List<GroupList.RowsEntity> mJoinedGroupList;
    SectionGroupListAdapter mMyGroupListAdapter;

    private int mCreatedPage;
    private int mJoinedPage;

    public MyGroupView(Context context) {
        super(context);
    }

    public MyGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyGroupView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public int getContentLayoutResourceId() {
        return R.layout.view_mygroup;
    }

    @Override
    protected void initViews() {

        PullToRefreshListView plvCreatedGroup = (PullToRefreshListView) findViewById(R.id.lv_view_mygroup_created_group);
        plvCreatedGroup.setScrollLoadEnabled(false);
        plvCreatedGroup.setPullRefreshEnabled(false);
        lvMyGroup = plvCreatedGroup.getRefreshableView();
//        lvMyGroup.setShadowVisible(false);


        lvMyGroup.setSelector(R.color.transparent);
        mJoinedGroupList = new ArrayList<GroupList.RowsEntity>();
        mCreatedGroupList = new ArrayList<GroupList.RowsEntity>();
        mMyGroupListAdapter = new SectionGroupListAdapter(getContext());
        lvMyGroup.setAdapter(mMyGroupListAdapter);

        lvMyGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Object info = mMyGroupListAdapter.getItem(position);

                    if (info instanceof GroupList.RowsEntity) {
                        GroupList.RowsEntity row = (GroupList.RowsEntity) info;
                        long groupId = row.getX6_Product_Id();
                        UIHelper.gotoGroupDetailActivity(getContext(), groupId);
                    }

                } catch (Exception e) {
                    // 异常捕捉
                }
            }
        });

//        refreshMyCreatedGroupList(mCreatedPage++);
//        refreshMyJoinedGroupList(mJoinedPage++);
    }

    public void refresh() {
        mCreatedPage = 0;
        mJoinedPage = 0;
        refreshMyCreatedGroupList(mCreatedPage++);
        refreshMyJoinedGroupList(mJoinedPage++);
    }

    public void updateListView() {
        List<BaseEntity> data = new ArrayList<BaseEntity>();
        GroupSectionInfo sectionCreatedGroup = new GroupSectionInfo();
        sectionCreatedGroup.setTitle("我创建的小组");
        data.add(sectionCreatedGroup);
        data.addAll(mCreatedGroupList);

        GroupSectionInfo sectionJoinedGroup = new GroupSectionInfo();
        sectionJoinedGroup.setTitle("我加入的小组");
        sectionJoinedGroup.setShowMargin(true);
        data.add(sectionJoinedGroup);

        data.addAll(mJoinedGroupList);

        mMyGroupListAdapter.setRows(data);
        mMyGroupListAdapter.notifyDataSetChanged();
    }

    public void refreshMyCreatedGroupList(int page) {
        MyCreatedGroupListRequest req = new MyCreatedGroupListRequest(new HttpRequestListener<GroupList>() {
            @Override
            public void onResponse(GroupList response) {
                super.onResponse(response);

                if (mCreatedPage == 1) {
                    mCreatedGroupList.clear();
                }

                mCreatedGroupList.addAll(response.getRows());

                updateListView();

                if (response.getTotal() > mCreatedGroupList.size()) {
                    refreshMyCreatedGroupList(mCreatedPage++);
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);

                // 错误处理
                mCreatedPage--;
            }
        });
        req.setRequestParams(page);
        HttpRequestQueue.addToRequestQueue(req);
    }

    public void refreshMyJoinedGroupList(int page) {
        MyJoinedGroupListRequest req = new MyJoinedGroupListRequest(new HttpRequestListener<GroupList>() {

            @Override
            public void onResponse(GroupList response) {
                super.onResponse(response);

                if (mJoinedPage == 1) {
                    mJoinedGroupList.clear();
                }

                List<GroupList.RowsEntity> row = response.getRows();

                mJoinedGroupList.addAll(response.getRows());

//                mJoinedGroupListAdapter.notifyDataSetChanged();

                updateListView();

                if (response.getTotal() > mJoinedGroupList.size()) {
                    refreshMyJoinedGroupList(mJoinedPage++);
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);

                // 错误处理
                mJoinedPage--;
            }

        });
        req.setRequestParams(page);
        HttpRequestQueue.addToRequestQueue(req);
    }

    static class SectionGroupListAdapter extends BaseAdapter {

        /**
         * Item 类型种类数
         */
        private final static int VIEW_TYPE_COUNT = 2;
        /**
         * Item 内容
         */
        private final static int VIEW_TYPE_ITEM = 0;
        /**
         * Item 段落头
         */
        private final static int VIEW_TYPE_SECTION = 1;

        protected Context mContext;
        private List<BaseEntity> mGroupList;

        public SectionGroupListAdapter(Context context) {
            this.mContext = context;
        }

        public void setRows(List<BaseEntity> list) {
            this.mGroupList = list;
        }

        @Override
        public int getCount() {
            if (mGroupList != null) {
                return mGroupList.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (mGroupList != null) {
                return mGroupList.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext)
                        .inflate(R.layout.item_section_group, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            BaseEntity info = mGroupList.get(position);

            if (getItemViewType(position) == VIEW_TYPE_ITEM) {
                holder.lytSection.setVisibility(View.GONE);
                holder.lytContent.setVisibility(View.VISIBLE);


                GroupList.RowsEntity row = (GroupList.RowsEntity) info;

                holder.title.setText(row.getField_XZBT());
                holder.content.setText(row.getField_XZJJ());

                String memberCount = mContext.getResources().getString(R.string.group_list_member_count, row.getField_YHSL());
                holder.people.setText(memberCount);

                Glide.with(mContext)
                        .load(row.getX6_Product_Pic())
                        .into(holder.image);

            } else {
                holder.lytSection.setVisibility(View.VISIBLE);
                holder.lytContent.setVisibility(View.GONE);

                holder.sectionName.setText(((GroupSectionInfo) info).getTitle());

                if (((GroupSectionInfo) info).isShowMargin()) {
                    holder.sectionMargin.setVisibility(View.VISIBLE);
                } else {
                    holder.sectionMargin.setVisibility(View.GONE);
                }

            }


            return convertView;
        }

        public int getItemViewType(int position) {

            if (mGroupList != null) {
                BaseEntity info = mGroupList.get(position);

                if (info != null) {
                    if (info instanceof GroupSectionInfo) {
                        return VIEW_TYPE_SECTION;
                    } else {
                        return VIEW_TYPE_ITEM;
                    }
                } else {
                    return VIEW_TYPE_ITEM;
                }
            } else {
                return VIEW_TYPE_ITEM;
            }

        }

        static class ViewHolder {
            @Bind(R.id.section_margin)
            View sectionMargin;
            @Bind(R.id.tv_item_group_section_name)
            TextView sectionName;
            @Bind(R.id.section)
            RelativeLayout lytSection;
            @Bind(R.id.iv_item_group_image)
            ImageView image;
            @Bind(R.id.tv_item_group_title)
            TextView title;
            @Bind(R.id.tv_item_group_content)
            TextView content;
            @Bind(R.id.tv_item_group_people)
            TextView people;
            @Bind(R.id.content)
            RelativeLayout lytContent;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

}
