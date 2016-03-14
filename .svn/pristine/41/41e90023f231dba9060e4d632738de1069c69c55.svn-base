package com.yunchengke.app.dynamic.activity.homepage.dynamic;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lee.pullrefresh.PullToRefreshBase;
import com.lee.pullrefresh.PullToRefreshListView;
import com.yunchengke.app.R;
import com.yunchengke.app.dynamic.adapter.AbsBaseAdapter;
import com.yunchengke.app.dynamic.data.BaseParam;
import com.yunchengke.app.dynamic.entity.DynamicDetailsCommentEntity;
import com.yunchengke.app.dynamic.entity.DynamicDetailsCommentListEntity;
import com.yunchengke.app.dynamic.entity.DynamicDetailsEntity;
import com.yunchengke.app.dynamic.xutils.HttpUtils;
import com.yunchengke.app.dynamic.xutils.exception.HttpException;
import com.yunchengke.app.dynamic.xutils.http.RequestParams;
import com.yunchengke.app.dynamic.xutils.http.ResponseInfo;
import com.yunchengke.app.dynamic.xutils.http.callback.RequestCallBack;
import com.yunchengke.app.dynamic.xutils.http.client.HttpRequest.HttpMethod;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.ui.view.daemon.CircleImageView;

import java.util.ArrayList;
import java.util.List;

public class DynamicDetailsActivity extends BaseActivity {

	private TextView dynamic_name;
	private CircleImageView dynamic_portrait;
	private TextView dynamic_date;
	private TextView dynamic_images_title;
	private GridView dynamic_images_list;
	private MyCommentAdapter commentAdapter;
	private DynamicDetailsEntity t;	//动态详情
	private String new_comment;
	StringBuffer b = new StringBuffer();
	char c = '"';
	private PullToRefreshListView refresh_list;
	private List<DynamicDetailsCommentEntity> rows;
	//评论列表
	private String st1= "http://www.icityto.com/X_UserLogic/yesicity2015/trends_comm_Page/?UId=yesicity2015&pagesize=5&page=1&Id=";
	private String st2= "http://www.icityto.com/X_UserLogic/yesicity2015/trends_comm_Page/?UId=yesicity2015&pagesize=5&page=2&Id=";

	@Override
	protected View createContentView(ViewGroup parent) {
		View rootView = inflate(R.layout.activity_dynamic_detail, parent);

		PullToRefreshListView pullToRefreshListView = (PullToRefreshListView) rootView.findViewById(R.id.plv_dynamic_detail_comment);

		ListView listView = pullToRefreshListView.getRefreshableView();
		listView.addHeaderView(inflate(R.layout.list_item_dynamic, listView), null, false);

		return rootView;
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		super.initViews(savedInstanceState);

		lytTitle.setBackgroundResource(R.color.dynamic_title_background);
		setTitle(R.string.dynamic_detail_title);
		setTitleLeft(R.string.back);
		setTitleRight(R.string.dynamic_detail_publish);

		// TODO

		t = (DynamicDetailsEntity) getIntent().getSerializableExtra("DynamicDetailsEntity");
		dynamic_name = (TextView) findViewById(R.id.dynamic_name);
		findViewById(R.id.dynamic_comment).setVisibility(View.GONE);
		dynamic_portrait = (CircleImageView) findViewById(R.id.dynamic_portrait);
		dynamic_date = (TextView) findViewById(R.id.dynamic_date);
		dynamic_images_title = (TextView) findViewById(R.id.dynamic_images_title);
		dynamic_images_list = (GridView) findViewById(R.id.dynamic_images_list);
		refresh_list = (PullToRefreshListView) findViewById(R.id.plv_dynamic_detail_comment);

		edit_comment = (EditText) findViewById(R.id.edt_dynamic_detail_comment);

		findViewById(R.id.btn_dynamic_detail_comment).setOnClickListener(clickListener);
		refresh_list.setPullRefreshEnabled(false);
		refresh_list.setScrollLoadEnabled(true);
		refresh_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

				HttpUtils httpUtils = new HttpUtils();
				RequestParams params=new RequestParams();
				httpUtils.send(HttpMethod.GET,st2 + t.getX6_Product_Id(), params, new RequestCallBack<String>() {


					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						refresh_list.onPullRefreshComplete();
						Log.d("jsondata", responseInfo.result);
						String result = responseInfo.result;
						Gson gson = new Gson();
						DynamicDetailsCommentListEntity dynamicListEntity = gson.fromJson(result, DynamicDetailsCommentListEntity.class);
						List<DynamicDetailsCommentEntity> row = dynamicListEntity.getRows();
						rows.addAll(row);
						commentAdapter.updateALLData(rows);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						Toast.makeText(getApplicationContext(), "加载失败!", Toast.LENGTH_SHORT).show();
						refresh_list.onPullRefreshComplete();
					}
				});
			}
			});
		if(null!=t.getX6_Product_PicList()&&!"".equals(t.getX6_Product_PicList()))
		{
			String[] split = t.getX6_Product_PicList().split("\\|\\|");
			List<String> images = new ArrayList<String>();
			for (int i = 0; i < split.length; i++) {
				images.add(split[i]);
			}
			dynamic_images_list.setAdapter(new MyImagesAdapter(getApplicationContext(), images));
		}
		Log.e("ss",t.getX6_Product_Id());
		dynamic_name.setText(t.getField_DTYH());
		dynamic_date.setText(t.getField_DTSJ());
		HttpUtils httpUtils = new HttpUtils();
		RequestParams params=new RequestParams();
		httpUtils.send(HttpMethod.GET,st1 +"47", params, new RequestCallBack<String>() {



			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				Log.d("jsondata", responseInfo.result);
				String result = responseInfo.result;
				Gson gson = new Gson();
				DynamicDetailsCommentListEntity dynamicListEntity = gson.fromJson(result, DynamicDetailsCommentListEntity.class);
				rows = dynamicListEntity.getRows();
				commentAdapter = new MyCommentAdapter(getApplicationContext(),rows);
				refresh_list.setAdapter(commentAdapter);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(getApplicationContext(), "加载失败!", Toast.LENGTH_SHORT).show();
			}
		});

	}
	
	private OnClickListener clickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_dynamic_detail_comment:
				if(edit_comment.getText().toString()==null||"".equals(edit_comment.getText()))
				{
					Toast.makeText(getApplicationContext(), "发表评论不能为空!", Toast.LENGTH_SHORT).show();
				}else
				{
					//发送评论
					new_comment = b.append("param={").append(c).append("Id").append(c).append(":").append(c)
							.append("52").append(c).append(":").append(c).append("Field_PLNR").append(c).append(":").append(c).append("不错.").append(c).append("}").toString();
					HttpUtils httpUtils = new HttpUtils();
					RequestParams params=new RequestParams();;
//					showProgressDialog(R.string.loading, false);
					params.addBodyParameter("UId", "yesicity2015");
					params.addBodyParameter("param", new_comment);
					httpUtils.send(HttpMethod.GET, BaseParam.ServerAddress+ "/X_UserLogic/yesicity2015/trends_Add/", params, new RequestCallBack<String>() {
			
						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
//							hideProgressDialog();
							Toast.makeText(getApplicationContext(), "评论成功!", Toast.LENGTH_SHORT).show();
						}
			
						@Override
						public void onFailure(HttpException error, String msg) {
							Toast.makeText(getApplicationContext(), "加载失败!", Toast.LENGTH_SHORT).show();
//							hideProgressDialog();
						}
					});
				}
				break;

			default:
				break;
			}
		}
	};
	

	private EditText edit_comment;

	@Override
	public void onClick(View v) {

	}

	private class MyCommentAdapter extends AbsBaseAdapter<DynamicDetailsCommentEntity> {

		protected MyCommentAdapter(Context context, List<DynamicDetailsCommentEntity> list) {
			super(context, list, R.layout.list_item_dynamic_comment);
		}

		@Override
		protected View newView(View convertView, DynamicDetailsCommentEntity t, int position) {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			if(holder==null)
			{
				holder= new ViewHolder();
				holder.comment_name = (TextView) convertView.findViewById(R.id.comment_name);
				holder.comment_time = (TextView) convertView.findViewById(R.id.comment_time);
				holder.comment_content = (TextView) convertView.findViewById(R.id.comment_content);
				holder.comment_image = (ImageView) convertView.findViewById(R.id.comment_image);
				convertView.setTag(holder);
			}
			holder.comment_name.setText(t.getField_PLYH());
			holder.comment_time.setText(t.getField_PLSJ());
			holder.comment_content.setText(t.getField_PLNR());
			return convertView;
		}
	}
	
	private class MyImagesAdapter extends AbsBaseAdapter<String> {

		protected MyImagesAdapter(Context context, List<String> list) {
			super(context, list, R.layout.item_grid_img);
		}

		@Override
		protected View newView(View convertView, String t, int position) {
			ViewHolderIm holder= (ViewHolderIm) convertView.getTag();
			if(holder==null)
			{
				holder= new ViewHolderIm();
				holder.images= (ImageView) convertView.findViewById(R.id.img);
				convertView.setTag(holder);
			}

			Glide.with(DynamicDetailsActivity.this).load(BaseParam.ServerAddress + t).into(holder.images);

			return convertView;
		}
	
		}
	
	private class ViewHolderIm{
		public ImageView images;
	}

	private class ViewHolder{

		public ImageView comment_image;
		public TextView comment_content;
		public TextView comment_time;
		public TextView comment_name;
	}
}
