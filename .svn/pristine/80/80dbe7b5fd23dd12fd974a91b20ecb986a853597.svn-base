package com.yunchengke.app.pay.unionpay;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.unionpay.UPPayAssistEx;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

/**
 * 直接start该Activity即可测试，测试时，请在menifest注册activity（支付逻辑可以自定义实现）
 *
 * "接入指南：\n1:拷贝sdk目录下的UPPayAssistEx.jar到libs目录下\n"
 "根据需要拷贝sdk/jar/data.bin（或sdkPro/jar/data.bin）至工程的res/drawable目录下
 （似乎不需要data.bin，还未真正的测试过付款，如果遇到问题，可以考虑加上此文件）
 "获取tn后通过UPPayAssistEx.startPayByJar(...)方法调用控件"
 */
public class UnionPayActivity extends Activity implements Runnable,Handler.Callback{

    public static final String LOG_TAG = "PayDemo";
    private Context mContext = null;
    private int mGoodsIdx = 0;
    private Handler mHandler = null;
    private ProgressDialog mLoadingDialog = null;

    public static final int PLUGIN_VALID = 0;
    public static final int PLUGIN_NOT_INSTALLED = -1;
    public static final int PLUGIN_NEED_UPGRADE = 2;

    /*****************************************************************
     * mMode参数解释： "00" - 启动银联正式环境 "01" - 连接银联测试环境
     *****************************************************************/
    private final String mMode = "00";
    private static final String TN_URL_01 = "http://202.101.25.178:8080/sim/gettn";

    private final View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.e(LOG_TAG, " " + v.getTag());
            mGoodsIdx = (Integer) v.getTag();
            mLoadingDialog = ProgressDialog.show(UnionPayActivity.this, // context
                    "", // title
                    "正在努力的获取tn中,请稍候...", // message
                    true); // 进度是否是不确定的，这只和创建进度条有关

            /*************************************************
             * 步骤1：从网络开始,获取交易流水号即TN
             ************************************************/
            new Thread(UnionPayActivity.this).start();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mHandler = new Handler(this);
        mLoadingDialog = ProgressDialog.show(UnionPayActivity.this, // context
                "", // title
                "正在努力的获取tn中,请稍候...", // message
                true); // 进度是否是不确定的，这只和创建进度条有关
        mLoadingDialog.setCancelable(true);
        /*************************************************
         * 步骤1：从网络开始,获取交易流水号即TN
         ************************************************/
        new Thread(UnionPayActivity.this).start();
    }


    @Override
    public boolean handleMessage(Message msg) {
        Log.e(LOG_TAG, "handleMessage=" + msg.obj);
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }

        String tn = "";
        if (msg.obj == null || ((String) msg.obj).length() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(UnionPayActivity.this);
            builder.setTitle("错误提示");
            builder.setMessage("网络连接失败,请重试!");
            builder.setNegativeButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.create().show();
        } else {
            tn = (String) msg.obj;
            /*************************************************
             * 步骤2：通过银联工具类启动支付插件
             ************************************************/

            pay(tn);
            doStartUnionPayPlugin(UnionPayActivity.this, tn, mMode);
        }

        return false;
    }

    public void doStartUnionPayPlugin(Activity activity, String tn, String mode) {
        // mMode参数解释：
        // 0 - 启动银联正式环境
        // 1 - 连接银联测试环境
        int ret = UPPayAssistEx.startPay(this, null, null, tn, mode);
        if (ret == PLUGIN_NEED_UPGRADE || ret == PLUGIN_NOT_INSTALLED) {
            // 需要重新安装控件
            Log.e(LOG_TAG, " plugin not found or need upgrade!!!");

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("完成购买需要安装银联支付控件，是否安装？");

            builder.setNegativeButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            UPPayAssistEx.installUPPayPlugin(UnionPayActivity.this);
                            dialog.dismiss();
                            finish();
                        }
                    });

            builder.setPositiveButton("取消",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    });
            builder.create().show();

        }
        Log.e(LOG_TAG, "是否安装了银联控件？ret=" + ret);
    }

    public void pay(String tn){

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        /*************************************************
         * 步骤3：处理银联手机支付控件返回的支付结果
         ************************************************/
        if (data == null) {
            return;
        }

        String msg = "";
        /*
         * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
         */
        String str = data.getExtras().getString("pay_result");
        if (str.equalsIgnoreCase("success")) {
            msg = "支付成功！";
        } else if (str.equalsIgnoreCase("fail")) {
            msg = "支付失败！";
        } else if (str.equalsIgnoreCase("cancel")) {
            msg = "取消支付";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(UnionPayActivity.this);
        builder.setTitle("银联支付结果通知");
        builder.setMessage(msg);
        builder.setInverseBackgroundForced(true);
        builder.setCancelable(false);
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                setResult(RESULT_OK,data);
                finish();
            }
        });
        builder.create().show();
    }

    @Override
    public void run() {
        String orderId = getIntent().getStringExtra("orderId")+new Random().nextInt(10000);
        String price= getIntent().getStringExtra("price");
        Float price2 = Float.parseFloat(price)*100;
        //double price2 = Double.parseDouble(price);
        //int price2 = Integer.parseInt(price);
//            String url = TN_URL_01;
        String url = " http://121.41.24.154/tydemo/?orderId="+orderId+"&txnAmt="+price2.intValue();
        Log.d("syb", "url -> " + url.toString());
//        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.GET,url,null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d("syb", "response -> " + response.toString());
//
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("syb", "response -> " + error);
//            }
//        });
//        HttpRequestQueue.getInstance().addToRequestQueue(jsonRequest);

        String tn = null;
        InputStream is;
        try {

//            String url = TN_URL_01;
            URL myURL = new URL(url);
            URLConnection ucon = myURL.openConnection();
            ucon.setConnectTimeout(120000);
            is = ucon.getInputStream();
            int i = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((i = is.read()) != -1) {
                baos.write(i);
            }

            String response = baos.toString();
            Log.d("syb", "response -> " +response);
            JSONObject jsonObject = new JSONObject(response);
            tn = jsonObject.getString("tn");
            Log.d("syb", "tn -> " +tn);
            is.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Message msg = mHandler.obtainMessage();
        msg.obj = tn;
        mHandler.sendMessage(msg);
    }

//    int startpay(Activity act, String tn, int serverIdentifier) {
//        return 0;
//    }
}
