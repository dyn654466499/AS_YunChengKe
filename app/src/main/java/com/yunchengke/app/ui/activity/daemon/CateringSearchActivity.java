package com.yunchengke.app.ui.activity.daemon;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunchengke.app.R;
import com.yunchengke.app.bean.LoginInfo;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringActivityList;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringList;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.interfaces.Commands;
import com.yunchengke.app.interfaces.FragmentListener;
import com.yunchengke.app.model.CateringModel;
import com.yunchengke.app.ui.fragments.catering.CateringActivityFragment;
import com.yunchengke.app.ui.fragments.catering.CateringFragment;
import com.yunchengke.app.utils.daemon.AutoLoadingUtil;
import com.yunchengke.app.utils.daemon.DialogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CateringSearchActivity extends BaseActivity implements FragmentListener{

    public final static String EXTRA_CATERING_ID = "EXTRA_CATERING_ID";

    private Button btn_catering_search_diningRoom,btn_catering_search_activity;

    private CateringFragment fragment_catering;
    private CateringActivityFragment fragment_catering_activity;

    private Resp_CateringList resp_cateringList;
    private Resp_CateringActivityList resp_cateringActivityList;

    private List<Resp_CateringList.Rows> list_cateringRows;
    private List<Resp_CateringActivityList.Rows> list_cateringActivityRows;

    private String keyWord = "";
    private int page_catering = 1;
    private int page_catering_activity = 1;

    private EditText edtSearch;
    private boolean mShowSearchView;
    private LinearLayout linearLayout_catering_title;
    private Button btn_catering_search;
    private boolean isWaimai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering);

        boolean hasBackBtn = getIntent().getBooleanExtra("hasBackBtn", false);
        isWaimai = getIntent().getBooleanExtra("isWaimai", false);
        if (hasBackBtn)
        {
            findViewById(R.id.btn_title_back).setVisibility(View.VISIBLE);
        }else
        {
            findViewById(R.id.btn_title_back).setVisibility(View.GONE);
        }

        list_cateringRows = new ArrayList<Resp_CateringList.Rows>();
        list_cateringActivityRows = new ArrayList<Resp_CateringActivityList.Rows>();

        setModelDelegate(new CateringModel(handler, this));
        setViewChangeListener(this);

        Button btn_title_back = (Button) findViewById(R.id.btn_title_back);
        btn_title_back.setOnClickListener(this);

        btn_catering_search_diningRoom = (Button)findViewById(R.id.btn_catering_search_diningRoom);
        btn_catering_search_diningRoom.setOnClickListener(this);

        btn_catering_search_activity = (Button)findViewById(R.id.btn_catering_search_activity);
        btn_catering_search_activity.setOnClickListener(this);

        btn_catering_search = (Button)findViewById(R.id.btn_catering_search);
        btn_catering_search.setOnClickListener(this);

        linearLayout_catering_title = (LinearLayout)findViewById(R.id.linearLayout_catering_title);

        searchDiningRoom();

        edtSearch = (EditText)findViewById(R.id.et_catering_search);
        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if(!TextUtils.isEmpty(textView.getText().toString())){
                        resp_cateringList = null;
                        list_cateringRows.clear();
                        keyWord = textView.getText().toString();
                        searchDiningRoom();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(edtSearch.getWindowToken(), 0);
                    }else{
                        showTip("内容不能为空哦！");
                    }

                    return true;
                }

                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_title_back:
                finish();
                break;

            case R.id.btn_catering_search_diningRoom:
                btn_catering_search.setVisibility(View.VISIBLE);
                searchDiningRoom();
                break;

            case R.id.btn_catering_search_activity:
                btn_catering_search.setVisibility(View.GONE);
                searchActivity();
                break;

            case R.id.btn_catering_search:
                if (mShowSearchView) {
                    hideSearchView(R.string.search);
                } else {
                    showSearchView(R.string.cancel);
                }
                break;
        }
    }

    private void searchDiningRoom(){
        btn_catering_search_diningRoom.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_conner_left_press));
        btn_catering_search_diningRoom.setTextColor(getResources().getColor(R.color.catering_title_color));

        btn_catering_search_activity.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_conner_right_unpress_orange));
        btn_catering_search_activity.setTextColor(getResources().getColor(R.color.ticket_white));
        if(resp_cateringList==null){
            btn_catering_search_diningRoom.setClickable(false);
            btn_catering_search_activity.setClickable(false);
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.relativeLayout_fragment_content);
            AutoLoadingUtil.setAutoLoadingView(layout);
            HashMap<String, String> params_map = new HashMap<String, String>();
            params_map.put("UId", "yesicity2015");
            params_map.put("Field_YHID", LoginInfo.getInstance().getId());
            params_map.put("Yesicity", "1");
            params_map.put("page", String.valueOf(page_catering));
            params_map.put("type", "0");

            if(!TextUtils.isEmpty(keyWord))
                params_map.put("keyword", keyWord);
            if (isWaimai)
            {
                params_map.put("class", "1");
            }
            else
            {
                params_map.put("class", "0");
            }


            Log.d("syb","params_map 13 "+params_map.toString());
            notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_SEARCH_DINING_ROOM, params_map));
        }else {
            if (!fragment_catering.isAdded()) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.relativeLayout_fragment_content, fragment_catering);
                transaction.commitAllowingStateLoss();
            }
        }
    }

    private void searchActivity(){
        btn_catering_search_activity.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_conner_right_press));
        btn_catering_search_activity.setTextColor(getResources().getColor(R.color.catering_title_color));

        btn_catering_search_diningRoom.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_conner_left_unpress_orange));
        btn_catering_search_diningRoom.setTextColor(getResources().getColor(R.color.ticket_white));
        if(resp_cateringActivityList==null){
            btn_catering_search_diningRoom.setClickable(false);
            btn_catering_search_activity.setClickable(false);
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.relativeLayout_fragment_content);
            AutoLoadingUtil.setAutoLoadingView(layout);
            HashMap<String, String> params_map = new HashMap<String, String>();
            params_map.put("UId", "yesicity2015");
            params_map.put("Field_YHID",LoginInfo.getInstance().getId());
            params_map.put("Yesicity", "1");
            params_map.put("page", String.valueOf(page_catering_activity));
            params_map.put("Id", "");
//            if(!TextUtils.isEmpty(keyWord))
//                params_map.put("keyword", keyWord);
            notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_SEARCH_ACTIVITY, params_map));
        }else {
            if (!fragment_catering_activity.isAdded()) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.relativeLayout_fragment_content, fragment_catering_activity);
                transaction.commitAllowingStateLoss();
            }
        }
    }


    @Override
    public void onViewChange(Message msg) {
      switch (msg.what){
          case Constants.VIEW_CATERING_SEARCH_DINING_ROOM:
              btn_catering_search_diningRoom.setClickable(true);
              btn_catering_search_activity.setClickable(true);
              if(!isDestroyed) {
                  keyWord = "";

              AutoLoadingUtil.cancelAutoLoadingView();
              if(msg.obj instanceof String){
                  DialogUtil.showDialog(CateringSearchActivity.this, "哎呀出错了", (String) msg.obj, new Commands() {
                      @Override
                      public void executeCommand(Message msg_params) {

                      }
                  });
                  if(fragment_catering!=null){
                      list_cateringRows.clear();
                      fragment_catering.notifyDataSetChanged();
                  }
              }else {
                      resp_cateringList = (Resp_CateringList) msg.obj;
                      if(list_cateringRows.size()==0 && fragment_catering == null){
                          list_cateringRows.addAll(resp_cateringList.rows);

                          fragment_catering = CateringFragment.newInstance(list_cateringRows);
                          FragmentManager fm = getFragmentManager();
                          FragmentTransaction transaction = fm.beginTransaction();
                          transaction.replace(R.id.relativeLayout_fragment_content, fragment_catering);
                          transaction.commitAllowingStateLoss();
                      }else{
                          list_cateringRows.addAll(resp_cateringList.rows);
                          fragment_catering.notifyDataSetChanged();
                      }

                  }
              }
              break;

          case Constants.VIEW_CATERING_SEARCH_ACTIVITY:
              btn_catering_search_diningRoom.setClickable(true);
              btn_catering_search_activity.setClickable(true);
              if(!isDestroyed) {

                  AutoLoadingUtil.cancelAutoLoadingView();

                  if(msg.obj instanceof String){
                      DialogUtil.showDialog(CateringSearchActivity.this, "哎呀出错了", (String) msg.obj, new Commands() {
                          @Override
                          public void executeCommand(Message msg_params) {

                          }
                      });
                      if(fragment_catering_activity!=null){
                          list_cateringActivityRows.clear();
                          fragment_catering_activity.notifyDataSetChanged();
                      }
                  }else {
                      resp_cateringActivityList = (Resp_CateringActivityList) msg.obj;
                      if(list_cateringActivityRows.size()==0 && fragment_catering_activity == null){
                          list_cateringActivityRows.addAll(resp_cateringActivityList.rows);

                          fragment_catering_activity = CateringActivityFragment.newInstance(list_cateringActivityRows);
                          FragmentManager fm = getFragmentManager();
                          FragmentTransaction transaction = fm.beginTransaction();
                          transaction.replace(R.id.relativeLayout_fragment_content, fragment_catering_activity);
                          transaction.commitAllowingStateLoss();
                      }else{
                          list_cateringActivityRows.addAll(resp_cateringActivityList.rows);
                          fragment_catering_activity.notifyDataSetChanged();
                      }
                  }
              }
              break;
      }
    }

    @Override
    public void onPullDownToRefresh() {
        if(fragment_catering!=null&&fragment_catering.isAdded()){
            list_cateringRows.clear();
            page_catering = 1;

            HashMap<String, String> params_map = new HashMap<String, String>();
            params_map.put("UId", "yesicity2015");
            params_map.put("Field_YHID", LoginInfo.getInstance().getId());
            params_map.put("Yesicity", "1");
            params_map.put("page", String.valueOf(page_catering));
            params_map.put("type", "0");
            if(!TextUtils.isEmpty(keyWord))
                params_map.put("Keyword", keyWord);
            if (isWaimai)
            {
                params_map.put("class", "1");
            }
            else
            {
                params_map.put("class", "0");
            }

            Log.d("syb","params_map 11 "+params_map.toString());
            notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_SEARCH_DINING_ROOM, params_map));

        }else if(fragment_catering_activity!=null&&fragment_catering_activity.isAdded()){
            list_cateringActivityRows.clear();
            page_catering_activity = 1;

            HashMap<String, String> params_map = new HashMap<String, String>();
            params_map.put("UId", "yesicity2015");
            params_map.put("Field_YHID", LoginInfo.getInstance().getId());
            params_map.put("Yesicity", "1");
            params_map.put("page", String.valueOf(page_catering_activity));
            params_map.put("Id", "");
            notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_SEARCH_ACTIVITY, params_map));
        }

    }

    @Override
        public void onPullUpToRefresh() {
        if(fragment_catering!=null&&fragment_catering.isAdded()){
            if(page_catering < Integer.valueOf(resp_cateringList.PageCount)){
                page_catering++;
                HashMap<String, String> params_map = new HashMap<String, String>();
                params_map.put("UId", "yesicity2015");
                params_map.put("Field_YHID", LoginInfo.getInstance().getId());
                params_map.put("Yesicity", "1");
                params_map.put("page", String.valueOf(page_catering));
                params_map.put("type", "0");
                if(!TextUtils.isEmpty(keyWord))
                    params_map.put("Keyword", keyWord);
                if (isWaimai)
                {
                    params_map.put("class", "1");
                }
                else
                {
                    params_map.put("class", "0");
                }

                Log.d("syb","params_map 12 "+params_map.toString());
                notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_SEARCH_DINING_ROOM, params_map));
            }else{
                fragment_catering.notifyDataSetChanged();
            }
        }else if(fragment_catering_activity!=null&&fragment_catering_activity.isAdded()){
            if(page_catering_activity < Integer.valueOf(resp_cateringActivityList.PageCount)){
                page_catering_activity++;
                HashMap<String, String> params_map = new HashMap<String, String>();
                params_map.put("UId", "yesicity2015");
                params_map.put("Field_YHID", LoginInfo.getInstance().getId());
                params_map.put("Yesicity", "1");
                params_map.put("page", String.valueOf(page_catering_activity));
                params_map.put("Id", "");
                notifyModelChange(Message.obtain(handler, Constants.MODEL_CATERING_SEARCH_ACTIVITY, params_map));
            }else{
                fragment_catering_activity.notifyDataSetChanged();
            }
        }

    }


    public void showSearchView(@StringRes int resid) {
        btn_catering_search.setText(resid);
        linearLayout_catering_title.setVisibility(View.INVISIBLE);
        edtSearch.setVisibility(View.VISIBLE);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_search_view_show);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                edtSearch.requestFocus();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(edtSearch, 0);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        edtSearch.startAnimation(anim);
        mShowSearchView = true;
    }

    public void hideSearchView(@StringRes int resid) {
        btn_catering_search.setText(resid);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_search_view_hide);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edtSearch.getWindowToken(), 0);
                linearLayout_catering_title.setVisibility(View.VISIBLE);
                edtSearch.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        edtSearch.startAnimation(anim);
        mShowSearchView = false;
    }
}
