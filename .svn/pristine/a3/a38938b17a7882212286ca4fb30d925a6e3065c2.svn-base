package com.yunchengke.app.ui.activity.daemon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.LoginInfo;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringDetail;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringDetailActivity;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringDetailComment;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringDetailDishes;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringList;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.interfaces.Commands;
import com.yunchengke.app.model.CateringModel;
import com.yunchengke.app.ui.activity.city.SameCityAcitvity;
import com.yunchengke.app.ui.adapter.daemon.catering.CateringDetailCommentAdapter;
import com.yunchengke.app.ui.adapter.daemon.catering.CateringDetailDishesAdapter;
import com.yunchengke.app.ui.view.daemon.GlideCircleTransform;
import com.yunchengke.app.utils.ShareUtil;
import com.yunchengke.app.utils.daemon.DialogUtil;

import java.util.HashMap;

import static com.yunchengke.app.consts.Constants.KEY_ID;
import static com.yunchengke.app.consts.Constants.KEY_PARCELABLE;
import static com.yunchengke.app.consts.Constants.KEY_TITLE;
import static com.yunchengke.app.consts.Constants.KEY_TYPE;

public class CateringDetailActivity extends BaseActivity {
    private Resp_CateringList.Rows row;

    private Resp_CateringDetail resp_cateringDetail;
    private String attentionState = "";
    private Button btn_catering_detail_attention;
    private Resp_CateringDetailActivity resp_cateringDetailActivity;
    private String X6_Product_Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering_detail);
        setModelDelegate(new CateringModel(handler, this));
        setViewChangeListener(this);

        TextView tv_title = (TextView)findViewById(R.id.tv_title);
        tv_title.setText("商家详情");

        Button btn_back = (Button)findViewById(R.id.btn_title_back);
        btn_back.setOnClickListener(this);

        Button btn_title_right = (Button)findViewById(R.id.btn_title_right);
        btn_title_right.setText("询问");
        btn_title_right.setVisibility(View.VISIBLE);
        btn_title_right.setOnClickListener(this);


        if(getIntent().hasExtra(KEY_PARCELABLE)){
            row = getIntent().getParcelableExtra(KEY_PARCELABLE);
            X6_Product_Id = row.X6_Product_Id;
            getCateringDetail();
        }else if(getIntent().hasExtra(KEY_ID)){
            X6_Product_Id = String.valueOf(getIntent().getLongExtra(KEY_ID,-1));
            getCateringDetail();
        }


    }

    private void getCateringDetail(){
        HashMap<String, String> params_map = new HashMap<String, String>();
        params_map.put("UId", "yesicity2015");
        params_map.put("Field_YHID", LoginInfo.getInstance().getId());
        params_map.put("Yesicity", "1");
        params_map.put("param", "{Id:" + X6_Product_Id + "}");

        notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_DETAIL, params_map));
    }

    private void getCommentList(){
        HashMap<String, String> params_map = new HashMap<String, String>();
        params_map.put("UId", "yesicity2015");
        params_map.put("Field_YHID", LoginInfo.getInstance().getId());
        params_map.put("Yesicity", "1");
        params_map.put("Id", X6_Product_Id);//resp_cateringDetail.rows.get(0).X6_Product_Id
        params_map.put("page", "1");

        notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_DETAIL_COMMENT, params_map));
    }

    private void getLocalDishesList(){
        HashMap<String, String> params_map = new HashMap<String, String>();
        params_map.put("UId", "yesicity2015");
        params_map.put("Field_YHID",LoginInfo.getInstance().getId());
        params_map.put("Yesicity", "1");
        params_map.put("param", "{Id:" + X6_Product_Id + ",type:1}");//resp_cateringDetail.rows.get(0).X6_Product_Id
        notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_DETAIL_DISH_LOCAL, params_map));
    }

    private void getTopDishesList(){
        HashMap<String, String> params_map = new HashMap<String, String>();
        params_map.put("UId", "yesicity2015");
        params_map.put("Field_YHID", LoginInfo.getInstance().getId());
        params_map.put("Yesicity", "1");
        params_map.put("param", "{Id:" + X6_Product_Id + ",type:0}");//resp_cateringDetail.rows.get(0).X6_Product_Id
        notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_DETAIL_DISH_TOP, params_map));
    }

    private void getActivityList(){
        HashMap<String, String> params_map = new HashMap<String, String>();
        params_map.put("UId", "yesicity2015");
        params_map.put("Field_YHID", LoginInfo.getInstance().getId());
        params_map.put("Yesicity", "1");
        params_map.put("param", "{Id:" + X6_Product_Id + "}");//resp_cateringDetail.rows.get(0).X6_Product_Id
        notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_DETAIL_ACTIVITY, params_map));
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btn_title_back:
                finish();
                break;

            case R.id.btn_title_right:
                showTip("功能暂时未能实现，敬请期待！");
                break;

            /**
             * 关注/取消
             */
            case R.id.btn_catering_detail_attention:
                final HashMap<String, String> params_map = new HashMap<String, String>();
                params_map.put("UId", "yesicity2015");
                params_map.put("Field_YHID", LoginInfo.getInstance().getId());
                params_map.put("Yesicity", "1");
                params_map.put("Id",  resp_cateringDetail.rows.get(0).X6_Product_Product);
                /**
                 *  1是已关注
                 */
                if ("1".equals(attentionState)) {
                    DialogUtil.showDialog(CateringDetailActivity.this, "提示", "您已经关注了该商家，确定要取消关注吗？", new Commands() {
                        @Override
                        public void executeCommand(Message msg_params) {
                            params_map.put("type", "cancel");
                            notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_DETAIL_ATTENTION, params_map));
                        }
                    });
                }else{
                    params_map.put("type", "follow");
                    notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_DETAIL_ATTENTION, params_map));
                }
                break;
            /**
             * 查看全部热门菜品
             */
            case R.id.btn_catering_detail_allTopDish:
                intent = new Intent(CateringDetailActivity.this,CateringDishesActivity.class);
                intent.putExtra(KEY_TITLE,resp_cateringDetail.rows.get(0).Field_DPMC);
                intent.putExtra(KEY_TYPE,"0");
                intent.putExtra(KEY_ID,X6_Product_Id);//resp_cateringDetail.rows.get(0).X6_Product_Id
                startActivity(intent);
                break;
            /**
             * 查看全部本店菜品
             */
            case R.id.btn_catering_detail_allLocalDish:
                intent = new Intent(CateringDetailActivity.this,CateringDishesActivity.class);
                intent.putExtra(KEY_TITLE,resp_cateringDetail.rows.get(0).Field_DPMC);
                intent.putExtra(KEY_TYPE,"1");
                intent.putExtra(KEY_ID, X6_Product_Id);
                startActivity(intent);
                break;
            /**
             *  预订点餐
             */
            case R.id.btn_catering_detail_book:
                intent = new Intent(CateringDetailActivity.this,CateringBookActivity.class);
                intent.putExtra(Constants.KEY, "");
                intent.putExtra(Constants.KEY_TITLE, resp_cateringDetail.rows.get(0).Field_DPMC);
                intent.putExtra(KEY_ID, X6_Product_Id);
                startActivity(intent);
                break;

            /**
             *  叫外卖
             */
            case R.id.btn_catering_detail_takeOut:
                if(resp_cateringDetail!=null) {
                    intent = new Intent(CateringDetailActivity.this, CateringBookActivity.class);
                    intent.putExtra(Constants.KEY, resp_cateringDetail.rows.get(0).Field_SFWM);
                    intent.putExtra(Constants.KEY_TITLE, resp_cateringDetail.rows.get(0).Field_DPMC);
                    intent.putExtra(KEY_ID, X6_Product_Id);
                    startActivity(intent);
                }
                break;

            /**
             * 查看全部评论
             */
            case R.id.btn_catering_detail_allComment:
                if(resp_cateringDetail!=null) {
                    intent = new Intent(CateringDetailActivity.this, CateringCommentActivity.class);
                    intent.putExtra(KEY_ID,  X6_Product_Id);
                    intent.putExtra(Constants.KEY_ID_COMMENT, X6_Product_Id);
                    startActivityForResult(intent, Constants.REQUEST_CODE_CATERING_COMMENT_ADD);
                }
                break;
            /**
             * 点击拨打电话
             */
            case R.id.tv_catering_detail_phone:
                Uri uri = Uri.parse("tel:"+resp_cateringDetail.rows.get(0).Field_DPDH);
                intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;
            /**
             * 更多资讯
             */
            case R.id.btn_catering_detail_moreActivity:
                intent = new Intent(CateringDetailActivity.this, SameCityAcitvity.class);
                startActivity(intent);
                break;
            /**
             * 评论
             */
            case R.id.btn_catering_detail_comment:
                if(resp_cateringDetail!=null) {
                    intent = new Intent(CateringDetailActivity.this, CommentActivity.class);
                    intent.putExtra(Constants.KEY_ID_COMMENT, X6_Product_Id);
                    startActivityForResult(intent, Constants.REQUEST_CODE_CATERING_COMMENT_ADD);
                }
                break;
            /**
             * 查看商家信息
             */
            case R.id.btn_catering_detail_merchant:
                if(resp_cateringDetail!=null) {
                    intent = new Intent(CateringDetailActivity.this, CateringMerchantInfoActivity.class);
                    intent.putExtra(Constants.KEY, resp_cateringDetail.rows.get(0).Field_DPJJ);
                    startActivity(intent);
                }
                break;

            /**
             * 分享
             */
            case R.id.btn_catering_detail_share:
                if(resp_cateringDetail!=null) {

                    String x6_product_pic = resp_cateringDetail.rows.get(0).X6_Product_Pic;
                    Log.d("syb","x6_product_pic"+x6_product_pic);
                    ShareUtil.shareInit(CateringDetailActivity.this,
                            "1",
                            "商家详情",
                            resp_cateringDetail.rows.get(0).Field_DPMC,
                            shareImg+"@@@"+shareImg,
                            "479",
                            Integer.valueOf(X6_Product_Id));
                }
                break;
            /**
             * 查看商家位置
             */
            case R.id.tv_catering_detail_address:
                intent = new Intent(CateringDetailActivity.this, MapActivity.class);
                intent.putExtra(Constants.KEY, resp_cateringDetail.rows.get(0).Field_DPZB);
                startActivity(intent);
                break;
        }
    }

    String shareImg;
    @Override
    public void onViewChange(Message msg) {
       switch (msg.what){
           /**
            * 商家详情
            */
           case Constants.VIEW_CATERING_DETAIL:
               if(!isDestroyed) {
               if(msg.obj instanceof String){
                   DialogUtil.showDialog(CateringDetailActivity.this, "商家详情", (String) msg.obj, new Commands() {
                       @Override
                       public void executeCommand(Message msg_params) {

                       }
                   });
               }else {

                       resp_cateringDetail = (Resp_CateringDetail) msg.obj;

                       TextView tv_catering_detail_name = (TextView)findViewById(R.id.tv_catering_detail_name);
                       tv_catering_detail_name.setText(resp_cateringDetail.rows.get(0).Field_DPMC);

                       TextView tv_catering_detail_type = (TextView)findViewById(R.id.tv_catering_detail_type);
                       tv_catering_detail_type.setText(resp_cateringDetail.rows.get(0).Field_DPFL);

                       TextView tv_catering_detail_time = (TextView)findViewById(R.id.tv_catering_detail_time);
                       tv_catering_detail_time.setText(resp_cateringDetail.rows.get(0).Field_DPYYSJ);


                       final ImageView imageView_catering_detail_bigIcon = (ImageView)findViewById(R.id.imageView_catering_detail_bigIcon);
                   if(resp_cateringDetail.rows.get(0).Field_Pic.contains("http://www.icityto.com")){
                       this.shareImg = resp_cateringDetail.rows.get(0).Field_Pic;
                   }else{
                       this.shareImg = "http://www.icityto.com"+resp_cateringDetail.rows.get(0).Field_Pic;
                   }

                   Glide.with(this).
                           load(resp_cateringDetail.rows.get(0).Field_Pic.contains("http://www.icityto.com")?resp_cateringDetail.rows.get(0).Field_Pic:"http://www.icityto.com" + resp_cateringDetail.rows.get(0).Field_Pic).
                           into(imageView_catering_detail_bigIcon);

                   handler.post(new Runnable() {
                       @Override
                       public void run() {
                           ImageView imageView_catering_detail_smallIcon = (ImageView) findViewById(R.id.imageView_catering_detail_smallIcon);

                           LinearLayout linearLayout_catering_detail_merchantInfo = (LinearLayout) findViewById(R.id.linearLayout_catering_detail_merchantInfo);
                           int height = linearLayout_catering_detail_merchantInfo.getMeasuredHeight();
                           FrameLayout.LayoutParams params_old = (FrameLayout.LayoutParams) imageView_catering_detail_smallIcon.getLayoutParams();
                           int bottomMargin = height - params_old.height / 2;
                           FrameLayout.LayoutParams params_new = new FrameLayout.LayoutParams(imageView_catering_detail_smallIcon.getLayoutParams());
                           params_new.gravity = Gravity.CENTER | Gravity.BOTTOM;
                           params_new.setMargins(0, 0, 0, bottomMargin);
                           imageView_catering_detail_smallIcon.setLayoutParams(params_new);
                           imageView_catering_detail_smallIcon.bringToFront();
                           Glide.with(CateringDetailActivity.this)
                                   .load("http://www.icityto.com" + resp_cateringDetail.rows.get(0).X6_Product_Pic)
                                   .transform(new GlideCircleTransform(CateringDetailActivity.this))
                                   .placeholder(R.drawable.submit_edit_clear_normal)
                                   .into(imageView_catering_detail_smallIcon);
                       }
                   });

                       TextView tv_catering_detail_traffic = (TextView)findViewById(R.id.tv_catering_detail_traffic);
                       tv_catering_detail_traffic.setText(resp_cateringDetail.rows.get(0).Field_GJXX);

                       TextView tv_catering_detail_saleTime = (TextView)findViewById(R.id.tv_catering_detail_saleTime);
                       tv_catering_detail_saleTime.setText(resp_cateringDetail.rows.get(0).Field_DPYYSJ);

                       TextView tv_catering_detail_other = (TextView)findViewById(R.id.tv_catering_detail_other);
                       tv_catering_detail_other.setText(resp_cateringDetail.rows.get(0).Field_DPWZ);

                       TextView tv_catering_detail_address = (TextView)findViewById(R.id.tv_catering_detail_address);
                       tv_catering_detail_address.setText(resp_cateringDetail.rows.get(0).Field_DPDZ);
                   tv_catering_detail_address.setOnClickListener(this);

                       TextView tv_catering_detail_phone = (TextView)findViewById(R.id.tv_catering_detail_phone);
                       tv_catering_detail_phone.setText(resp_cateringDetail.rows.get(0).Field_DPDH);
                       tv_catering_detail_phone.setOnClickListener(this);


                       btn_catering_detail_attention = (Button)findViewById(R.id.btn_catering_detail_attention);
                       if(resp_cateringDetail.rows.get(0).X6_Product_Id == resp_cateringDetail.rows.get(0).X6_Product_Product){
                           btn_catering_detail_attention.setVisibility(View.GONE);
                       }else {
                           attentionState = resp_cateringDetail.rows.get(0).isFollow;
                           /**
                            *  1是已关注
                            */
                           if ("1".equals(resp_cateringDetail.rows.get(0).isFollow)) {
                               btn_catering_detail_attention.setBackgroundColor(this.getResources().getColor(R.color.catering_bg_button_gray));
                               btn_catering_detail_attention.setText("已关注");
                           }
                           btn_catering_detail_attention.setOnClickListener(this);
                       }


                       Button btn_catering_detail_allTopDish = (Button)findViewById(R.id.btn_catering_detail_allTopDish);
                       btn_catering_detail_allTopDish.setOnClickListener(this);

                       Button btn_catering_detail_allLocalDish = (Button)findViewById(R.id.btn_catering_detail_allLocalDish);
                       btn_catering_detail_allLocalDish.setOnClickListener(this);

                       Button btn_catering_detail_allComment = (Button)findViewById(R.id.btn_catering_detail_allComment);
                       btn_catering_detail_allComment.setOnClickListener(this);

                       Button btn_catering_detail_book = (Button)findViewById(R.id.btn_catering_detail_book);
                       btn_catering_detail_book.setOnClickListener(this);

                   Button btn_catering_detail_comment = (Button)findViewById(R.id.btn_catering_detail_comment);
                   btn_catering_detail_comment.setOnClickListener(this);

                   Button btn_catering_detail_merchant = (Button)findViewById(R.id.btn_catering_detail_merchant);
                   btn_catering_detail_merchant.setOnClickListener(this);

                   Button btn_catering_detail_share = (Button)findViewById(R.id.btn_catering_detail_share);
                   btn_catering_detail_share.setOnClickListener(this);

                   if(!TextUtils.isEmpty(resp_cateringDetail.rows.get(0).Field_SFWM)){
                       Button btn_catering_detail_takeOut = (Button)findViewById(R.id.btn_catering_detail_takeOut);
                       btn_catering_detail_takeOut.setVisibility(View.VISIBLE);
                       btn_catering_detail_takeOut.setOnClickListener(this);
                   }


                   getActivityList();
                       getCommentList();
                       getLocalDishesList();
                       getTopDishesList();
                   }
               }
               break;
           /**
            * 评论
            */
           case Constants.VIEW_CATERING_DETAIL_COMMENT:
               if(!isDestroyed) {
               if(msg.obj instanceof String){
//                   DialogUtil.showDialog(CateringDetailActivity.this, "商家详情", (String) msg.obj, new Commands() {
//                       @Override
//                       public void executeCommand(Message msg_params) {
//
//                       }
//                   });
               }else {

                       Resp_CateringDetailComment resp_cateringDishesComment = (Resp_CateringDetailComment) msg.obj;
                       ListView listView = (ListView)findViewById(R.id.lv_catering_detail_comment);
                       CateringDetailCommentAdapter adapter = new CateringDetailCommentAdapter(CateringDetailActivity.this,resp_cateringDishesComment);
                       listView.setAdapter(adapter);
               }
               }
               break;
           /**
            *  本店菜品
            */
           case Constants.VIEW_CATERING_DETAIL_DISH_LOCAL:
               if(!isDestroyed) {
               if(msg.obj instanceof String){
//                   DialogUtil.showDialog(CateringDetailActivity.this, "商家详情", (String) msg.obj, new Commands() {
//                       @Override
//                       public void executeCommand(Message msg_params) {
//
//                       }
//                   });
               }else {

                       Resp_CateringDetailDishes resp_cateringDishesDetail = (Resp_CateringDetailDishes) msg.obj;
                       GridView gridView = (GridView)findViewById(R.id.gv_catering_detail_localDish);
                       CateringDetailDishesAdapter adapter = new CateringDetailDishesAdapter(CateringDetailActivity.this,resp_cateringDishesDetail);
                   adapter.setItemCommands(new Commands() {
                       @Override
                       public void executeCommand(Message msg_params) {
                           if(resp_cateringDetail!=null) {
                               Intent intent = new Intent(CateringDetailActivity.this, CateringDishesActivity.class);
                               intent.putExtra(KEY_TITLE, resp_cateringDetail.rows.get(0).Field_DPMC);
                               intent.putExtra(KEY_TYPE, "1");
                               intent.putExtra(KEY_ID, X6_Product_Id);
                               startActivity(intent);
                           }
                       }
                   });
                       gridView.setAdapter(adapter);
                   }
               }
             break;

           /**
            * 热门菜品
            */
           case Constants.VIEW_CATERING_DETAIL_DISH_TOP:
               if(!isDestroyed) {
               if(msg.obj instanceof String){

               }else {

                       Resp_CateringDetailDishes resp_cateringDishesDetail = (Resp_CateringDetailDishes) msg.obj;
                       GridView gridView = (GridView)findViewById(R.id.gv_catering_detail_topDish);
                       CateringDetailDishesAdapter adapter = new CateringDetailDishesAdapter(CateringDetailActivity.this,resp_cateringDishesDetail);
                   adapter.setItemCommands(new Commands() {
                       @Override
                       public void executeCommand(Message msg_params) {
                           if(resp_cateringDetail!=null) {
                               Intent intent = new Intent(CateringDetailActivity.this, CateringDishesActivity.class);
                               intent.putExtra(KEY_TITLE, resp_cateringDetail.rows.get(0).Field_DPMC);
                               intent.putExtra(KEY_TYPE, "0");
                               intent.putExtra(KEY_ID, X6_Product_Id);
                               startActivity(intent);
                           }
                       }
                   });
                       gridView.setAdapter(adapter);
                   }
               }
               break;

           /**
            * 关注/取消
            */
           case Constants.VIEW_CATERING_DETAIL_ATTENTION:

               if(msg.obj instanceof String) {
                   if ("success".equals(msg.obj)) {
                       if ("0".equals(attentionState)) {
                           attentionState = "1";
                           btn_catering_detail_attention.setBackgroundColor(this.getResources().getColor(R.color.catering_bg_button_gray));
                           btn_catering_detail_attention.setText("已关注");
                           showTip("已成功关注");
                       } else {
                           attentionState = "0";
                           btn_catering_detail_attention.setBackgroundColor(this.getResources().getColor(R.color.catering_title_color));
                           btn_catering_detail_attention.setText("关注");
                           showTip("已取消关注");
                       }

                   } else {
                       if(!isDestroyed) {
                       DialogUtil.showDialog(CateringDetailActivity.this, "商家详情", (String) msg.obj, new Commands() {
                           @Override
                           public void executeCommand(Message msg_params) {

                           }
                       });
                   }
               }
               }
               break;
           /**
            *  活动资讯
            */
           case Constants.VIEW_CATERING_DETAIL_ACTIVITY:
               if(!isDestroyed) {
                   if(msg.obj instanceof String){

                   }else {
                       Button btn_catering_detail_moreActivity = (Button)findViewById(R.id.btn_catering_detail_moreActivity);
                       btn_catering_detail_moreActivity.setOnClickListener(this);

                       TextView et_catering_detail_activity = (TextView)findViewById(R.id.et_catering_detail_activity);

                       resp_cateringDetailActivity = (Resp_CateringDetailActivity)msg.obj;
                       String content = "";
                       for (Resp_CateringDetailActivity.Rows row:
                       resp_cateringDetailActivity.rows) {
                           content += ","+row.Field_HDMC;
                       }
                       et_catering_detail_activity.setText(content.replaceFirst(",",""));
//                       et_catering_detail_activity.setText("aa");
                       et_catering_detail_activity.setHorizontallyScrolling(true);
                       et_catering_detail_activity.setFocusable(true);
                       et_catering_detail_activity.setSelected(true);

                       et_catering_detail_activity.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {

                           }
                       });
                   }
               }

               break;
       }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case Constants.REQUEST_CODE_CATERING_COMMENT_ADD:
                    getCommentList();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
