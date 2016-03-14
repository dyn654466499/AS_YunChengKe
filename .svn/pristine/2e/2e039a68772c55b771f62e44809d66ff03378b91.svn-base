package com.yunchengke.app.dynamic.activity.homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lee.pullrefresh.PullToRefreshListView;
import com.yunchengke.app.R;
import com.yunchengke.app.app.Application;
import com.yunchengke.app.dynamic.activity.homepage.dynamic.DynamicDetailsActivity;
import com.yunchengke.app.dynamic.activity.homepage.dynamic.ReleaseDynamicActivity;
import com.yunchengke.app.dynamic.adapter.AbsBaseAdapter;
import com.yunchengke.app.dynamic.data.BaseParam;
import com.yunchengke.app.dynamic.entity.DynamicDetailsEntity;
import com.yunchengke.app.dynamic.entity.DynamicEntity;
import com.yunchengke.app.dynamic.view.NoScrollGridView;
import com.yunchengke.app.dynamic.xutils.HttpUtils;
import com.yunchengke.app.dynamic.xutils.exception.HttpException;
import com.yunchengke.app.dynamic.xutils.http.ResponseInfo;
import com.yunchengke.app.dynamic.xutils.http.callback.RequestCallBack;
import com.yunchengke.app.dynamic.xutils.http.client.HttpRequest.HttpMethod;
import com.yunchengke.app.ui.activity.common.UserInfoActivity;
import com.yunchengke.app.ui.view.daemon.CircleImageView;

import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class DynamicActivity extends KeyBackStopActivity {

    private PullToRefreshListView Refresh_list;
    private List<DynamicDetailsEntity> dynamicList;
    private MyAdapter myAdapter;
    private String CurrentUser;

    private HttpUtils httpUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_homepage_dynamic);

        ButterKnife.bind(this);

        getTitleActionBar().setAppTopTitle("动态");
        getTitleActionBar().getTopTitle().setBackgroundColor(getResources().getColor(R.color.red));
        getTitleActionBar().setAppTitleLeftButton(0, new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getTitleActionBar().setAppTitleRightButton1("发布", 0, new OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ReleaseDynamicActivity.class));
                ;
            }
        });

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String url = BaseParam.ServerAddress + BaseParam.dynamic_list + Application.loginInfo.getId();
        Log.e("url:", url);
        httpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
//				hideProgressDialog();
                dynamicList.clear();
                Log.d("jsondata", responseInfo.result);
                Gson gson = new Gson();
                DynamicEntity dynamicEntity = gson.fromJson(responseInfo.result, DynamicEntity.class);
                dynamicList = dynamicEntity.getRows();
                myAdapter = new MyAdapter(getApplicationContext(), dynamicList);
                Refresh_list.setAdapter(myAdapter);
                DefaultHttpClient dh = (DefaultHttpClient) httpUtils.getHttpClient();
                CookieStore cs = dh.getCookieStore();
                List<Cookie> cookies = cs.getCookies();
                CurrentUser = cookies.get(0).getValue();
            }

            @Override
            public void onFailure(HttpException error, String msg) {
//				hideProgressDialog();
                Log.e("jsondata", msg);

            }
        });
    }

    private void initView() {
        httpUtils = new HttpUtils();
//		showProgressDialog(R.string.loading, false);
        dynamicList = new ArrayList<DynamicDetailsEntity>();
        httpUtils.send(HttpMethod.GET, BaseParam.ServerAddress + BaseParam.dynamic_list, new RequestCallBack<String>() {


            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
//				hideProgressDialog();
                Log.d("jsondata", responseInfo.result);
                Gson gson = new Gson();
                DynamicEntity dynamicEntity = gson.fromJson(responseInfo.result, DynamicEntity.class);
                dynamicList = dynamicEntity.getRows();
                myAdapter = new MyAdapter(getApplicationContext(), dynamicList);
                Refresh_list.setAdapter(myAdapter);
                DefaultHttpClient dh = (DefaultHttpClient) httpUtils.getHttpClient();
                CookieStore cs = dh.getCookieStore();
                List<Cookie> cookies = cs.getCookies();
                CurrentUser = cookies.get(0).getValue();
            }

            @Override
            public void onFailure(HttpException error, String msg) {
//				hideProgressDialog();
                Log.e("jsondata", msg);

            }
        });
        Refresh_list = (PullToRefreshListView) findViewById(R.id.Refresh_list);
        Refresh_list.setPullRefreshEnabled(true);
        Refresh_list.setScrollLoadEnabled(true);

        ListView listView = Refresh_list.getRefreshableView();
        listView.setDivider(getResources().getDrawable(R.color.transparent));
        listView.setDividerHeight(getResources().getDimensionPixelSize(R.dimen.dynamic_list_divider_height));
    }

    private class MyAdapter extends AbsBaseAdapter<DynamicDetailsEntity> {

        protected MyAdapter(Context context, List<DynamicDetailsEntity> dynamicList) {
            super(context, dynamicList, R.layout.list_item_dynamic);
        }

        @Override
        protected View newView(View convertView, final DynamicDetailsEntity t, int position) {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            if (holder == null) {
                holder = new ViewHolder();
                holder.dynamic_name = (TextView) convertView.findViewById(R.id.dynamic_name);
                holder.dynamic_comment = (RelativeLayout) convertView.findViewById(R.id.dynamic_comment);
                holder.dynamic_portrait = (CircleImageView) convertView.findViewById(R.id.dynamic_portrait);
                holder.dynamic_date = (TextView) convertView.findViewById(R.id.dynamic_date);
                holder.dynamic_images_title = (TextView) convertView.findViewById(R.id.dynamic_images_title);
                holder.dynamic_images_list = (NoScrollGridView) convertView.findViewById(R.id.dynamic_images_list);
                convertView.setTag(holder);
            }
            holder.dynamic_portrait.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if ("".equals(CurrentUser) && null == CurrentUser) return;
                    Intent intent = new Intent();
                    intent.putExtra("DynamicDetailsEntity", t);
                    if (CurrentUser.equals(t.getField_DTYH())) {
                        intent.setClass(getApplicationContext(), UserInfoActivity.class);
                    } else {
                        intent.setClass(getApplicationContext(), UserInfoActivity.class);
                    }
                    startActivity(intent);
                }
            });

            // TODO 这里修改
            Glide.with(DynamicActivity.this).load("http://www.icityto.com/X_UserFileLogic/X_yesicity2015/X_TX/?TX=8").into(holder.dynamic_portrait);

            if (null != t.getX6_Product_PicList() && !"".equals(t.getX6_Product_PicList())) {
                String[] split = t.getX6_Product_PicList().split("\\|\\|");
                List<String> images = new ArrayList<String>();
                for (int i = 0; i < split.length; i++) {
                    images.add(split[i]);
                }
                holder.dynamic_images_list.setAdapter(new MyImagesAdapter(DynamicActivity.this, images));
            } else {
                holder.dynamic_images_list.setAdapter(null);
            }
            holder.dynamic_name.setText(t.getField_DTYH());
            holder.dynamic_date.setText(t.getField_DTSJ());
            holder.dynamic_comment.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("DynamicDetailsEntity", t);
                    intent.setClass(getApplicationContext(), DynamicDetailsActivity.class);
                    startActivity(intent);
                }
            });
            holder.dynamic_images_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent();
                    intent.putExtra("DynamicDetailsEntity", t);
                    intent.setClass(getApplicationContext(), DynamicDetailsActivity.class);
                    startActivity(intent);
                }
            });
            // String[] split = st.split("\\|\\|");
            return convertView;
        }
    }

    private class ViewHolder {
        public NoScrollGridView dynamic_images_list;
        public TextView dynamic_date;
        public TextView dynamic_images_title;
        public CircleImageView dynamic_portrait;
        public RelativeLayout dynamic_comment;
        public TextView dynamic_name;
        public ImageView images;
    }

    /*
    public static class MyImagesAdapter extends com.yunchengke.app.ui.adapter.AbsBaseAdapter<String> {

        public MyImagesAdapter(Context context, List<String> list) {
            super(context);
            this.mRows = list;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_grid_img, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Glide.with(mContext).load(BaseParam.ServerAddress + getItem(position)).into(holder.img);

            return null;
        }

        static class ViewHolder {
            @Bind(R.id.img)
            ImageView img;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
    */


    private class MyImagesAdapter extends AbsBaseAdapter<String> {

		protected MyImagesAdapter(Context context, List<String> list) {
			super(context, list, R.layout.item_grid_img);
		}

		@Override
		protected View newView(View convertView, String t, int position) {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			if (holder == null) {
				holder = new ViewHolder();
				holder.images = (ImageView) convertView.findViewById(R.id.img);
				convertView.setTag(holder);
			}

			// TODO 这里修改
			Glide.with(DynamicActivity.this).load(BaseParam.ServerAddress + t).into(holder.images);

			return convertView;
		}

	}


}
