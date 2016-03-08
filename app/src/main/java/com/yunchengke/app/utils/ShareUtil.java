package com.yunchengke.app.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

import com.android.volley.VolleyError;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.CommAddEntity;
import com.yunchengke.app.bean.CommAddResult;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.CommAddRequest;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 作者：Tinchy
 * 创建时间：2016/1/28 10:35
 * 描述：分享工具
 * 版本：1.0
 */
public class ShareUtil {
    static CommAddEntity cae = new CommAddEntity();
    private static final String NULL_IMAGE = "(null)";
    /**参数依次为 动态类型值,标题，内容，图片地址，接口module值，项目id*/

    public static void shareInit(final Context context, final String type, String title, String text, String imageUrl, String module, final int id) {
//        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        if (text == null){
            text = title;
        }
        final String url;
        oks.disableSSOWhenAuthorize();
        oks.setTitle(title);
        oks.setText(text);
//如果图片链接不存在，则给一张默认图片，否则会导致分享失败
        imageUrl = (imageUrl==null||imageUrl==""||imageUrl.equals(NULL_IMAGE))?
                "http://yuck-upimage.b0.upaiyun.com/2016/3/1456797528.11.jpg@@@http://yuck-upimage.b0.upaiyun.com/2016/3/1456797528.11.jpg":imageUrl;
        //shenyibin add
        Log.d("syb","imageUrl="+imageUrl);
        String img ;
        if (imageUrl.contains("@@@"))
        {
            String[] split = imageUrl.split("@@@");
            Log.d("syb","split="+split.length);
            Log.d("syb","split="+split[0]);
            Log.d("syb","split="+split[1]);
            // shenyibin end

            url =split[0]+"||"+split[1];
            img = split[0];

        }else
        {
//            如果是多张图片url不变，img取第一张图
            url = imageUrl.contains(Constants.SUB_IMAGE_SPLIT)? imageUrl : imageUrl+Constants.SUB_IMAGE_SPLIT+imageUrl;
            img = imageUrl.contains(Constants.SUB_IMAGE_SPLIT)? imageUrl.split(Constants.SUB_IMAGE_SPLIT_REGULAR)[0] : imageUrl;
        }






        Log.d("syb","url="+url);
        Log.d("syb","img="+img);
        oks.setImageUrl(img);
        // shenyibin end

        String url2 = HttpUrls.HTTP_SHARE+"&Module=" +module + "&productid="+id;
        Log.e("ee",url2);
        oks.setTitleUrl(url2);
        oks.setUrl(url2);
        //自定义分享到云城客
        final Bitmap logo = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher58);
        String label = context.getResources().getString(R.string.label);
        final String finalText = title;
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                CommAddRequest car = new CommAddRequest(new HttpRequestListener<CommAddResult>() {
                    @Override
                    public void onResponse(CommAddResult response) {
                        super.onResponse(response);
                        if (response.isSucceed(context, response.getResultState(), response.getMessage())) {
                            ToastUtils.show(context,"分享成功");
                        }else {
                            ToastUtils.show(context,"分享失败");
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        super.onErrorResponse(error);
                    }
                });
                cae.setNr(StringUtils.replaceSpaceToJsonFormat(finalText));
                cae.setType(type);
                cae.setId(id);
                cae.setPicurl(url);
                String param = cae.toString();
                Log.e("ee",param);
                car.setRequestParams(param);
                HttpRequestQueue.addToRequestQueue(car);
            }
        };
        oks.setCustomerLogo(logo,logo,label,listener);
        oks.show(context);
    }
}
