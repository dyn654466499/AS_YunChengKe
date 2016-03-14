package com.yunchengke.app.http.request;

import com.google.gson.JsonObject;
import com.yunchengke.app.bean.CreateGroupResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 类名：CreateGroupRequest <br/>
 * 描述：创建小组请求
 * 创建时间：2016/01/16 11:43
 *
 * @author hanter
 * @version 1.0
 */
public class CreateGroupRequest extends GsonRequest<CreateGroupResult> {

    public CreateGroupRequest(HttpRequestListener<CreateGroupResult> listener) {
        super(HttpUrls.HTTP_CREATE_GROUP_REQUEST, CreateGroupResult.class, listener);
    }

    public void setRequestParams(long classId, String categoryName, String groupType, String groupName,
                                 String groupIntroduction, String groupLogoUrl,String image) {
        JsonObject params = new JsonObject();
        params.addProperty("classId", classId);
        params.addProperty("R", groupType);
        params.addProperty("className", categoryName);
        params.addProperty("Field_XZBT", groupName);
        params.addProperty("Field_XZJJ", groupIntroduction);
        params.addProperty("img", groupLogoUrl);
        params.addProperty("pic",image);
        mParams.put("param", params.toString());
    }

}
