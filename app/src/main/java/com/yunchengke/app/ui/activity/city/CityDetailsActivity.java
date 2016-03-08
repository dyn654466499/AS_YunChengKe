package com.yunchengke.app.ui.activity.city;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.FollowMediaResult;
import com.yunchengke.app.bean.city.ActiveinPageResult;
import com.yunchengke.app.bean.city.FreeJoinResult;
import com.yunchengke.app.bean.city.YCKCommentList;
import com.yunchengke.app.bean.city.YCKJoinedList;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.ActiveinPageRequest;
import com.yunchengke.app.http.request.ActivityFocus;
import com.yunchengke.app.http.request.FreeJoinRequest;
import com.yunchengke.app.http.request.PublishDynamicRequest;
import com.yunchengke.app.http.request.YCKCommentListRequest;
import com.yunchengke.app.http.request.YCKJoinedListRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.adapter.AbsBaseAdapter;
import com.yunchengke.app.ui.adapter.city.CommentListAdapter;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.utils.ShareUtil;
import com.yunchengke.app.utils.ToastUtils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：Tinchy ￠飞想蓝→
 * 创建时间：2016/2/1 22:25
 * 描述：活动详情
 * 版本：1.0
 */
public class CityDetailsActivity extends BaseActivity {
    public final static String EXTRA_CITY_ID = "EXTRA_CITY_ID";
    @Bind(R.id.scrollview)
    ScrollView scrollView;
    @Bind(R.id.top_tv1)
    TextView topTv1;
    @Bind(R.id.top_tv2)
    TextView topTv2;
    @Bind(R.id.top_iv)
    ImageView topIv;
    @Bind(R.id.activity_type)
    TextView tvType;
    @Bind(R.id.activity_cast)
    TextView tvCast;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.activity_address)
    TextView tvAddress;
    @Bind(R.id.activity_phonenum)
    TextView tvPhonenum;
    @Bind(R.id.activity_join)
    LinearLayout tvJoin;
    @Bind(R.id.activity_focus)
    LinearLayout tvFocus;
    @Bind(R.id.tv_join)
    TextView textViewJ;
    @Bind(R.id.tv_focus)
    TextView textViewF;
    @Bind(R.id.activity_watch_pic)
    LinearLayout llSee;
    @Bind(R.id.tv_introduction)
    TextView tvIntroduction;
    @Bind(R.id.activity_detail_comments)
    ListView lvComments;
    @Bind(R.id.gv_activity_img)
    GridView gvActivityImg;
    @Bind(R.id.gv_activity_joined)
    GridView gvActivityJoined;
    @Bind(R.id.ll_watch_comment)
    LinearLayout llWatchCommment;
    @Bind(R.id.ll_watch_joined)
    LinearLayout llWatchJoined;
    @Bind(R.id.tv_show)
    TextView tv_show;

    private  int mId;
    private List<YCKJoinedList.RowsEntity> mYCKJoinedList;
    CommentListAdapter mAdapter;
    List<YCKCommentList.RowsEntity> mList;
    static ActiveinPageResult.RowsEntity rows;

    GridViewActivityJoinAdapter gvajAdapter;
    @Override
    protected View createContentView(ViewGroup parent) {
        View view = inflate(R.layout.activity_details,parent);
        return view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        tvIntroduction.post(new Runnable() {
//
//            @Override
//            public void run() {
//                int s = tvIntroduction.getLineCount();
////                if (s<6)
////                    llShow.setVisibility(View.GONE);
//            }
//        });
//
//        llShow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (llShow.getText()=="展开全文"){
//                    llShow.setText("收起");
//                    tvIntroduction.setMaxLines(40);
//                }else {
//                    llShow.setText("展开全文");
//                    tvIntroduction.setMaxLines(6);
//                }
//                if (llShow.getLineCount()>6){
//                    llShow.setText("收起");
//                    llShow.setMaxLines(20);
//                }else{
//                    llShow.setText("展开全文");
//                    llShow.setMaxLines(6);
//                }
//            }
//        });
    }

    String shareImg ;
    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        mId = this.getIntent().getIntExtra(EXTRA_CITY_ID,0);
        lytTitle.setBackgroundResource(R.color.city_background);
        setTitle("活动详情");

        setTitleLeft(R.string.back);
        setShowSlidingMenu(true);
        initCommentList();

//


        ActiveinPageRequest request = new ActiveinPageRequest(new HttpRequestListener<ActiveinPageResult>(){
            @Override
            public void onResponse(ActiveinPageResult response) {
                rows = response.getRows().get(0);
                String url = rows.getX6_Product_PicBig();
                Glide.with(CityDetailsActivity.this).load(url).into(topIv);
                shareImg = url;
                topTv1.setText(getResources().getString(R.string.activity_join_num,rows.getField_HDBMRS()));
                topTv2.setText(getResources().getString(R.string.activity_focus_num,rows.getGzrs()));
                tvType.setText(getResources().getString(R.string.activity_type,rows.getField_HDFL()));
                tvCast.setText(getResources().getString(R.string.activity_case,rows.getField_HDBMFY()));
                tvTitle.setText(rows.getField_HDMC());
                tvAddress.setText(getResources().getString(R.string.activity_address,rows.getField_HDDD()));
                tvPhonenum.setText(getResources().getString(R.string.activity_phonenum,rows.getField_HDDH()));
                tvIntroduction.setText(rows.getField_HDJJ());
                if (rows.getIsJoin()!=0){
                    tvJoin.setClickable(false);
                    textViewJ.setText("已参加");
                }
                if (rows.getIsfollow() != 0){
                    setCancel();
                }else {
                    setFollow();
                }
                SimpleDateFormat formatter = new SimpleDateFormat    ("yyyy-MM-dd HH:mm:ss");
                java.util.Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                try {
                    if (formatter.parse(rows.getField_HDBMJZSJ()).getTime()<curDate.getTime()){
                        tvJoin.setClickable(false);
                        textViewJ.setText("报名时间已截止");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // shenyibin add start
                tvIntroduction.post(new Runnable() {

                    private View.OnClickListener click2;
                    private View.OnClickListener click1;

                    @Override
                    public void run() {
                        if (tvIntroduction.getLineCount()<=6){
                            tv_show.setVisibility(View.GONE);
                        }else
                        {
                            tv_show.setVisibility(View.VISIBLE);

                            click1 = new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    tv_show.setText("收起");
                                    tv_show.setOnClickListener(click2);
                                    tvIntroduction.setMaxLines(100);
                                }
                            };
                            click2 = new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    tv_show.setText("展开全文");
                                    tv_show.setOnClickListener(click1);
                                    tvIntroduction.setMaxLines(6);
                                }
                            };
                            tv_show.setOnClickListener(click1);
                        }
                    }
                });


                // shenyibin add end

                String picList = rows.getX6_Product_PicList();
                String[] urlList = picList.split(Constants.IMAGE_SPLIT_REGULAR);
                final ArrayList<Map<String, String>> picUrlList = new ArrayList<Map<String, String>>();
                for (String imagePairUrl : urlList) {
                    String[] pairUrl = imagePairUrl.split(Constants.SUB_IMAGE_SPLIT_REGULAR);
                    try {
                        Map<String, String> pairUrlMap = new HashMap<String, String>();
                        pairUrlMap.put("small", pairUrl[0]);
                        pairUrlMap.put("large", pairUrl[1]);
                        picUrlList.add(pairUrlMap);
                    } catch (Exception e) {
                        // 捕获异常
                    }
                }
                GridViewActivityImgAdapter gvaAdapter = new GridViewActivityImgAdapter(CityDetailsActivity.this);
                gvaAdapter.setRows(picUrlList);
                gvActivityImg.setAdapter(gvaAdapter);

                YCKJoinedListRequest req = new YCKJoinedListRequest(mListener);
                req.setRequestParams(mId);
                HttpRequestQueue.addToRequestQueue(req);
                mYCKJoinedList = new ArrayList<YCKJoinedList.RowsEntity>();
                gvajAdapter = new GridViewActivityJoinAdapter(CityDetailsActivity.this);
                gvajAdapter.setRows(mYCKJoinedList);
                gvActivityJoined.setAdapter(gvajAdapter);
            }
        });
        request.setRequestParams(mId);
        HttpRequestQueue.addToRequestQueue(request);
    }

    HttpRequestListener<YCKJoinedList> mListener = new HttpRequestListener<YCKJoinedList>() {
        @Override
        public void onResponse(YCKJoinedList response) {
            super.onResponse(response);
            List<YCKJoinedList.RowsEntity> row = response.getRows();
            int a = 5;
            if (row.size()<5){
                a = row.size();
            }
            for (int i=0;i<a;i++){
                mYCKJoinedList.add(row.get(i));
            }
            gvajAdapter.notifyDataSetChanged();
        }
    };

    private void initCommentList() {
        mList = new ArrayList<YCKCommentList.RowsEntity>();
        mAdapter = new CommentListAdapter(getBaseContext());
        mAdapter.setRows(mList);
        lvComments.setAdapter(mAdapter);
        queryComments();
    }

    /**
     * 请求评论数据
     * @￠飞想蓝→
     * */
    private void queryComments(){
        mList.clear();
        YCKCommentListRequest clr = new YCKCommentListRequest(new HttpRequestListener<YCKCommentList>() {
            @Override
            public void onResponse(YCKCommentList response) {
                super.onResponse(response);
                final int count = Math.min(response.getRows().size(), 3);
                for (int i=0;i<count;i++){
                    mList.add(response.getRows().get(i));
                }

                mAdapter.notifyDataSetChanged();
            }
        });
        clr.setRequestParams(mId,1);
        HttpRequestQueue.addToRequestQueue(clr);
    }

    private  void updateView(ActiveinPageResult response){

    }

    /**
     * 如果发送了评论，到这里刷新一次评论
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 10086:
                if (resultCode == RESULT_OK){
                    queryComments();
                }
                break;
            default:
                break;
        }

    }

    @Override
    @OnClick({R.id.ll_activity_comment,
    R.id.ll_activity_share,
    R.id.activity_join,
    R.id.activity_focus,
    R.id.activity_watch_pic,
    R.id.ll_watch_comment,
    R.id.ll_watch_joined})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_base_title_left:
                finish();
                break;
            case R.id.ll_activity_comment:
                UIHelper.gotoYCKAddCommentActivity(CityDetailsActivity.this,rows.getX6_Product_Id());
                break;
            case R.id.ll_activity_share:
                String a = shareImg +"@@@"+shareImg;
                Log.d("syb","city= "+a);
                ShareUtil.shareInit(this,"2",rows.getField_HDMC(),rows.getField_HDJJ(),a,"480",rows.getX6_Product_Id());
                break;
            case R.id.activity_join:
                if (rows.getField_HDBMFY()!=0)
                UIHelper.gotoToPayActivity(this,String.valueOf(rows.getField_HDBMFY()),rows.getField_HDSJ(),""+rows.getX6_Product_Id());
                else
                {
                    FreeJoinRequest freeJoinRequest = new FreeJoinRequest(new HttpRequestListener<FreeJoinResult>() {
                        @Override
                        public void onResponse(FreeJoinResult response) {
                            if (response.isSucceed(getBaseContext(), response.getResultState(), response.getMessage())) {
                                tvJoin.setClickable(false);
                                textViewJ.setText("已参加");
                            }else {
                                ToastUtils.show(getBaseContext(), response.getMessage());
                            }
                        }
                    });
                    freeJoinRequest.setRequestParams(rows.getX6_Product_Id());
                    HttpRequestQueue.addToRequestQueue(freeJoinRequest);
                }
                break;
            case R.id.activity_focus:
                focus();
                break;
            case R.id.activity_watch_pic:
                UIHelper.gotoYCKPhotoActivity(this,rows.getX6_Product_PicList());
                break;
            case R.id.ll_watch_comment:
                UIHelper.gotoYCKCommentActivity(this,rows.getX6_Product_Id());
                break;
            case R.id.ll_watch_joined:
                UIHelper.gotoYCKJoinedActivity(this,rows.getX6_Product_Id());
                break;
            default:
                super.onClick(v);
                break;
        }
    }
    private FollowMediaResult mFollowMediaResult;
    private void focus() {
        ActivityFocus req = new ActivityFocus(new HttpRequestListener<FollowMediaResult>() {
            @Override
            public void onResponse(FollowMediaResult response) {
                super.onResponse(response);
                mFollowMediaResult = response;

                if (rows.getIsfollow() == 0){
                    setCancel();
                    rows.setIsfollow(1);
                }else {

                    setFollow();
                    rows.setIsfollow(0);
                }

                Toast.makeText(CityDetailsActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        });

        req.setRequestParams(rows.getX6_Product_Id());
        HttpRequestQueue.addToRequestQueue(req);
    }

    private void setCancel(){

        tvFocus.setBackgroundResource(R.color.white);
        textViewF.setText("已关注");
        textViewF.setTextColor(getResources().getColor(R.color.common_gray_color));

    }

    private void setFollow(){

        tvFocus.setBackgroundResource(R.color.white);
        textViewF.setText("关注");
        textViewF.setTextColor(getResources().getColor(R.color.common_gray_color));

    }
    public static class GridViewActivityImgAdapter extends AbsBaseAdapter<Map<String, String>>{

        public GridViewActivityImgAdapter(Context context) {
            super(context);
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            final ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_activity_img, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String picList = rows.getX6_Product_PicList();
            String[] urlList = picList.split(Constants.IMAGE_SPLIT_REGULAR);

            final ArrayList<Map<String, String>> picUrlList = new ArrayList<Map<String, String>>();

            for (String imagePairUrl : urlList) {
                String[] pairUrl = imagePairUrl.split(Constants.SUB_IMAGE_SPLIT_REGULAR);
                try {
                    Map<String, String> pairUrlMap = new HashMap<String, String>();
                    pairUrlMap.put("small", pairUrl[0]);
                    pairUrlMap.put("large", pairUrl[1]);
                    picUrlList.add(pairUrlMap);
                } catch (Exception e) {
                    // 捕获异常
                }
            }
            final Map<String, String> urlMap = getItem(position);
            final String smallUrl = urlMap.get("small");
            Glide.with(mContext).load(smallUrl).into(holder.img);
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<String> imageList = new ArrayList<String>();
                    for (Map<String, String> map : picUrlList) {
                        imageList.add(map.get("large"));
                    }
                    UIHelper.gotoZoomGalleryActivity(mContext, imageList, position);
                }
            });
            return convertView;
        }
        static class ViewHolder {
            @Bind(R.id.img)
            ImageView img;
            ViewHolder(View view){
                ButterKnife.bind(this,view);
            }
        }
    }

    public static class GridViewActivityJoinAdapter extends AbsBaseAdapter<YCKJoinedList.RowsEntity> {
        public GridViewActivityJoinAdapter(Context context) {
            super(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_activity_join, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final YCKJoinedList.RowsEntity rows = getItem(position);
            String url = HttpUrls.HTTP_USER_PORTRAIT_REQUEST + rows.getX6_Admin_Id();
            Glide.with(mContext).load(url).into(holder.img);
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.gotoUserInfoActivity(v.getContext(),rows.getX6_Admin_Id());
                }
            });

            return convertView;
        }
        static class ViewHolder {
            @Bind(R.id.img)
            ImageView img;
            ViewHolder(View view){
                ButterKnife.bind(this,view);
            }
        }
    }
}
