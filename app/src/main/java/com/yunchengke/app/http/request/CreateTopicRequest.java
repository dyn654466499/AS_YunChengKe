package com.yunchengke.app.http.request;

import com.google.gson.JsonObject;
import com.yunchengke.app.bean.CreateTopicResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

import java.util.List;

/**
 * 名称: CreateTopicRequest <br/>
 * 描述: 创建话题 <br/>
 * 创建时间：2016/1/25 13:31
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class CreateTopicRequest extends GsonRequest<CreateTopicResult> {

    public static final String IMAGE_SPLIT = "|-_-|";
    public static final String IMAGE_SPLIT_REGULAR = "\\|-_-\\|";

    public static final String SUB_IMAGE_SPLIT = "||";
    public static final String SUB_IMAGE_SPLIT_REGULAR = "\\|\\|";

    public CreateTopicRequest(HttpRequestListener<CreateTopicResult> listener) {
        super(HttpUrls.HTTP_ADD_TOPIC_REQUEST, CreateTopicResult.class, listener);
    }

    public void setRequestParams(long groupId, List<String> imageUrls, String topicTitle, String topicContent) {
        JsonObject params = new JsonObject();
        params.addProperty("groupId", groupId);
        params.addProperty("Field_HTBT", topicTitle);
        params.addProperty("Field_HTNR", topicContent);

        int i = 0;
        StringBuilder builder = new StringBuilder();
        for (String imageUrl : imageUrls) {

            if (i > 0) {
                builder.append(IMAGE_SPLIT);
            }

            builder.append(imageUrl);
            builder.append(SUB_IMAGE_SPLIT);
            builder.append(imageUrl);

            i++;
        }

        params.addProperty("pic", builder.toString());


        mParams.put("param", params.toString());
    }

}
