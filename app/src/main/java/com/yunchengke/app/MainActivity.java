package com.yunchengke.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yunchengke.app.bean.TopicList;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.request.LoginRequest;




public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private static final String REQUEST_TAG = "MainActivity";

//    String url = "http://192.168.3.199:8091/ddsc/30003.html?v=2%2FOMREwPuvmvvXy1a%2B%2B5u1vRi1ncPJ43wLzzKUtkUkQ%3D%0A&k=ad2165d39ba59163";

//      String url = "http://192.168.3.230:11010/ddsc/30003.html?accountid=";

//    String url = "http://192.168.3.230:11010/ddsc/v1/30003.html";

//    String url = "http://www.icityto.com/X_UserLogic/yesicity2015/Group_Page/?UId=yesicity2015&page=1";

//    String url = "http://www.icityto.com//X_UserLogic/yesicity2015/70-1_JsonEdit/?UId=yesicity2015&username=&userpassword=";

    String url = "http://www.icityto.com/X_UserLogic/yesicity2015/Group_Page/?UId=yesicity2015&page=1";

    private TextView txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();


        // 系统级别的Cookie管理
//        CookieHandler.setDefault();

//        CookieManager cm = CookieManager.getInstance();
//        cm.getCookie("https:");
    }

    private void initViews() {
        txtContent = (TextView) findViewById(R.id.txt_main_content);
        Button btnSend = (Button) findViewById(R.id.btn_main_start);
        btnSend.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (this.isFinishing()) {
            Log.w("onStop", "isFinishing");
            HttpRequestQueue.getInstance().getRequestQueue().cancelAll(REQUEST_TAG);
        }
    }

    private void sendHttpRequest() {

        LoginRequest req = new LoginRequest(new HttpRequestListener<TopicList>() {
            @Override
            public void onResponse(TopicList response) {
                super.onResponse(response);
            }
        });
        req.setRequestParams();
        HttpRequestQueue.addToRequestQueue(req);


        /*
        TopicListRequest req = new TopicListRequest(new HttpRequestListener<TopicList>() {
            @Override
            public void onResponse(TopicList response) {
                super.onResponse(response);
            }
        });
        req.setRequestParams(2);

        ArrayList<String> cookies = new ArrayList<String>();
        cookies.add("123456");
        cookies.add("654321");

        req.setCookie(cookies);
        HttpRequestQueue.addToRequestQueue(req);
        */


/*
        GsonRequest gsonRequest = new GsonRequest(url, TopicList.class, null, new HttpRequestListener<TopicList>() {
            @Override
            public void onStartRequest(Request request) {
                super.onStartRequest(request);
            }

            @Override
            public void onResponse(TopicList response) {
                super.onResponse(response);
                Toast.makeText(MainActivity.this, response.getTime(), Toast.LENGTH_SHORT).show();

            }
        });

        HttpRequestQueue.addToRequestQueue(gsonRequest);
        */
    }


    @Override
    public void onClick (View v){
        int id = v.getId();
        switch (id) {
            case R.id.btn_main_start:
                sendHttpRequest();
                break;
        }
    }
}
