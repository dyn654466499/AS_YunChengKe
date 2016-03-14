package com.yunchengke.app.http.request;

import com.google.gson.JsonObject;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.bean.CommonRequestResult;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

import java.util.List;

/**
 * 类名：PublishDynamicRequest <br/>
 * 描述：发布动态请求
 * 创建时间：2016/01/31 15:38
 *
 * @author hanter
 * @version 1.0
 */
public class PublishDynamicRequest extends GsonRequest<CommonRequestResult> {


    public PublishDynamicRequest(HttpRequestListener<CommonRequestResult> listener) {
        super(HttpUrls.HTTP_PUBLISH_DYNAMIC_REQUEST, CommonRequestResult.class, listener);
    }

    public void setRequestParams(String content, List<String> imageUrls) {
        JsonObject json = new JsonObject();
        json.addProperty("Field_DTJL", content);

        int i = 0;
        StringBuilder builder = new StringBuilder();
        if (imageUrls !=null && imageUrls.size()>0 ) {
            for (String imageUrl : imageUrls) {

                if (i > 0) {
                    builder.append(Constants.IMAGE_SPLIT);
                }

                builder.append(imageUrl);
                builder.append(Constants.SUB_IMAGE_SPLIT);
                builder.append(imageUrl);

                i++;
            }
        }

        json.addProperty("X6_Product_PicList", builder.toString());

        json.addProperty("type", "0");

        mParams.put("param", json.toString());
    }
}
