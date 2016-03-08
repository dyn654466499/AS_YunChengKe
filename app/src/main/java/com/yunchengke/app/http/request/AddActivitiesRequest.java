package com.yunchengke.app.http.request;

import com.google.gson.JsonObject;
import com.yunchengke.app.bean.city.AddActivitiesResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpUrls;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Administrator on 2016/1/30.
 */
public class AddActivitiesRequest extends GsonRequest<AddActivitiesResult> {
    public AddActivitiesRequest(HttpRequestListener<AddActivitiesResult> listener) {
        super(HttpUrls.HTTP_CREATE_ACTIVITIES,AddActivitiesResult.class,listener);
    }

    public static final String IMAGE_SPLIT = "|-_-|";
    public static final String IMAGE_SPLIT_REGULAR = "\\|-_-\\|";

    public static final String SUB_IMAGE_SPLIT = "||";
    public static final String SUB_IMAGE_SPLIT_REGULAR = "\\|\\|";

    public void setRequestParams(String styes,String actname,String actminimage,String actmaximage,
                                 String actimagelist,String actregion,String actaddress,
                                 String actphone,String actcost,String actnumbermax,
                                 String actstarttime,String actendtime,String actstartsignup,
                                 String actendsignup,String actintro,List<String> imageUrls) {
//        mParams.put("param","{\"Field_HDFL\":"+styes+",\"Field_HDMC\":\""+actname+"\",\"X6_Product_Pic\":\""+actminimage+"\"," +
//                "\"X6_Product_PicBig\":\""+actmaximage+"\",\"X6_Product_PicList\":\""+actimagelist+"\",\"Field_HDDQ\":\""+actregion+"\"" +
//                ",\"Field_HDDD\":\""+actaddress+"\",\"Field_HDDH\":\""+actphone+"\",\"Field_HDBMFY\":\""+actcost+"\"" +
//                ",\"Field_HDBMRSXZ\":\""+actnumbermax+"\",\"Field_HDKSSJ\":\""+actstarttime+"\",\"Field_HDJSSJ\":\""+actendtime+"\"" +
//                ",\"Field_HDBMKSSJ\":\""+actstartsignup+"\",\"Field_HDBMJSSJ\":\""+actendsignup+"\",\"Field_HDJJ\":\""+actintro+"\"}");
        int i = 0;
        StringBuilder builder = new StringBuilder();
        if (imageUrls !=null && imageUrls.size()>0 ) {
            for (String imageUrl : imageUrls) {

                if (i > 0) {
                    builder.append(IMAGE_SPLIT);
                }

                builder.append(imageUrl);
                builder.append(SUB_IMAGE_SPLIT);
                builder.append(imageUrl);

                i++;
            }
        }


        JsonObject param = new JsonObject();
        param.addProperty("Field_HDFL",styes);
        param.addProperty("Field_HDMC",actname);
        param.addProperty("X6_Product_Pic",actminimage);
        param.addProperty("X6_Product_PicBig",actmaximage);
        param.addProperty("X6_Product_PicList",builder.toString());
        param.addProperty("Field_HDDQ",actregion);
        param.addProperty("Field_HDDD",actaddress);
        param.addProperty("Field_HDDH",actphone);
        param.addProperty("Field_HDBMFY",actcost);
        param.addProperty("Field_HDBMRSXZ",actnumbermax);
        param.addProperty("Field_HDKSSJ",actstarttime);
        param.addProperty("Field_HDJSSJ",actendtime);
        param.addProperty("Field_HDBMKSSJ",actstartsignup);
        param.addProperty("Field_HDBMJSSJ",actendsignup);
        param.addProperty("Field_HDJJ",actintro);
        try {
            mParams.put("param", URLEncoder.encode(param.toString(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
