package com.yunchengke.app.ui.activity.maintab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunchengke.app.R;
import com.yunchengke.app.ui.activity.CommonWebView;
import com.yunchengke.app.ui.activity.daemon.CateringSearchActivity;
import com.yunchengke.app.ui.activity.daemon.FlightSearchActivity;
import com.yunchengke.app.ui.activity.group.GroupActivity;
import com.yunchengke.app.ui.activity.headline.HeadlineActivity;
import com.yunchengke.app.ui.activity.login.LoginActivity;
import com.yunchengke.app.ui.view.NoScrollGridView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends KeyBackStopActivity {
    DecimalFormat format = new DecimalFormat("0");
    private NoScrollGridView home_gv;
    private TextView person;
    boolean login = false;

    @SuppressWarnings("serial")
    private List<Integer> list = new ArrayList<Integer>() {
        {
            add(R.drawable.ic_faxian_gouwu);
            add(R.drawable.ic_faxian_tejia);
            add(R.drawable.ic_faxian_wmai);
            add(R.drawable.ic_faxian_chedai);
            add(R.drawable.ic_cant);
            add(R.drawable.ic_faxian_jiudian);
            add(R.drawable.ic_faxian_jipiao);
            add(R.drawable.ic_faxian_shipin);
            add(R.drawable.ic_faxian_xiaozu);
            add(R.drawable.ic_faxian_xinwen);
            add(R.drawable.ic_faxian_yishupin);
            add(R.drawable.ic_faxian_fangchan);
            add(R.drawable.ic_faxian_qiche);
            add(R.drawable.ic_faxian_dianying);
            add(R.drawable.ic_houg);
            add(R.drawable.ic_book);
        }
    };
    private String[] names = new String[]{"购物", "特价", "餐饮", "工银e车贷", "外卖", "酒店", "机票", "视频", "小组", "头条", "艺术品", "房产", "汽车", "电影", "优品直选", "书城"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        initView();
    }

    private void initView() {

        home_gv = (NoScrollGridView) findViewById(R.id.home_gv);
        home_gv.setAdapter(new MyAdapter());
        home_gv.setOnItemClickListener(clickListener);

        person = (TextView) findViewById(R.id.app_title_right_button);
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainTabAct) getParent()).showMenu();
            }
        });

    }

    OnItemClickListener clickListener = new OnItemClickListener() {

        private Animation animation;

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            SharedPreferences pref = getSharedPreferences("login_info", MODE_PRIVATE);
            if (login = pref.contains("id")) {
                if (position >= 0 && position <= 3) {
                    if (position == 0){
                        Intent i = new Intent(HomePageActivity.this, CommonWebView.class);
                        i.putExtra(getString(R.string.url), "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=258&UId=yesicity2015");
                        startActivity(i);
                    }
                    if (position == 1){
                        Intent i = new Intent(HomePageActivity.this, CommonWebView.class);
                        i.putExtra(getString(R.string.url), "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=264&UId=yesicity2015");
                        startActivity(i);
                    }
                    if (position == 2){
                        Intent intent = new Intent(HomePageActivity.this, CateringSearchActivity.class);
                        intent.putExtra("isWaimai",false);
                        intent.putExtra("hasBackBtn",true);
                        startActivity(intent);
                    }
                    if (position == 3){
                        Intent i = new Intent(HomePageActivity.this, CommonWebView.class);
                        i.putExtra(getString(R.string.url), "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=356&UId=yesicity2015");
                        startActivity(i);
                    }
                    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_top_out);
                } else if (position > 3 && position <= 7) {
                    if (position == 4){
//                        Intent i = new Intent(HomePageActivity.this, CommonWebView.class);
//                        i.putExtra(getString(R.string.url), "http://www.icityto.com/?Type=3&Module=407&UId=yesicity2015&UId=yesicity2015");
//                        startActivity(i);
                        Intent intent = new Intent(HomePageActivity.this, CateringSearchActivity.class);
                        intent.putExtra("isWaimai",true);
                        intent.putExtra("hasBackBtn",true);
                        startActivity(intent);
                    }
                    if (position == 5){
                        Intent i = new Intent(HomePageActivity.this, CommonWebView.class);
                        i.putExtra(getString(R.string.url), "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=239&UId=yesicity2015");
                        startActivity(i);
                    }
                    if (position == 6) {
                        Intent i = new Intent(HomePageActivity.this, FlightSearchActivity.class);
                        startActivity(i);
                    }
                    if (position == 7){
                        Intent i = new Intent(HomePageActivity.this, CommonWebView.class);
                        i.putExtra(getString(R.string.url), "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=190&UId=yesicity2015");
                        startActivity(i);
                        //退出登录
/*                        SharedPreferences.Editor editor = getSharedPreferences("login_info",MODE_PRIVATE).edit();
                        editor.clear();
                        editor.commit();*/
                    }
                    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_left_out);
                } else if (position > 7 && position <= 11) {
                    if (position == 8) {
                        Intent i = new Intent(HomePageActivity.this, GroupActivity.class);
                        startActivity(i);
                    }
                    if (position == 9) {
                        Intent i = new Intent(HomePageActivity.this, HeadlineActivity.class);
                        startActivity(i);
                    }
                    if (position == 10){
                        Intent i = new Intent(HomePageActivity.this, CommonWebView.class);
                        i.putExtra(getString(R.string.url), "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=188&UId=yesicity2015");
                        startActivity(i);
                    }
                    if (position == 11){
                        Intent i = new Intent(HomePageActivity.this, CommonWebView.class);
                        i.putExtra(getString(R.string.url), "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=186&UId=yesicity2015");
                        startActivity(i);
                    }
                    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_right_out);
                } else {
                    if (position == 12){
                        Intent i = new Intent(HomePageActivity.this, CommonWebView.class);
                        i.putExtra(getString(R.string.url), "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=339&UId=yesicity2015");
                        startActivity(i);
                    }
                    if (position == 13){
                        Intent i = new Intent(HomePageActivity.this, CommonWebView.class);
                        i.putExtra(getString(R.string.url), "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=215&UId=yesicity2015");
                        startActivity(i);
                    }
                    if (position == 14){
                        Intent i = new Intent(HomePageActivity.this, CommonWebView.class);
//                        i.putExtra(getString(R.string.url), "http://www.icityto.com/?Type=3&Module=318&UId=yesicity2015&UId=yesicity2015");
//                        Log.d("syb","LoginInfo.getInstance().getId()"+LoginInfo.getInstance().getId());
//                        Log.d("syb","LoginInfo.getInstance().2()"+   Application.loginInfo.getId());

                        i.putExtra(getString(R.string.url), "http://www.icityto.com//?Type=3&Module=489");

                        startActivity(i);

                    }
                    if (position == 15){
                        Intent i = new Intent(HomePageActivity.this, CommonWebView.class);
                        i.putExtra(getString(R.string.url), "http://www.icityto.com/x/?Action=ListMobile&Type=2&Module=453&UId=yesicity2015");
                        startActivity(i);
                    }
                    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_bottom_out);
                }
                view.startAnimation(animation);
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            Thread.sleep(600);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//					startActivity(new Intent(getApplicationContext(),ReleaseDynamicActivity.class));
                    }
                }).start();
            } else {
                Intent i =new Intent(HomePageActivity.this, LoginActivity.class);
                startActivity(i);
            }
        }
    };

    private class MyAdapter extends BaseAdapter {

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
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(), R.layout.list_item_home_icon, null);
            }
            RelativeLayout main = (RelativeLayout) convertView.findViewById(R.id.main);
            Animation animation = null;
            if (position >= 0 && position <= 3) {
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_top_in);
            } else if (position > 3 && position <= 7) {
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_left_in);
            } else if (position > 7 && position <= 11) {
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_right_in);
            } else {
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_bottom_in);
            }
            main.startAnimation(animation);
            TextView state = (TextView) convertView.findViewById(R.id.state);
            if (position == 12)
                state.setVisibility(View.VISIBLE);
            TextView icon = (TextView) convertView.findViewById(R.id.icon);
            TextView name = (TextView) convertView.findViewById(R.id.name);
            icon.setBackgroundResource(list.get(position));
            name.setText(names[position]);
            return convertView;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
