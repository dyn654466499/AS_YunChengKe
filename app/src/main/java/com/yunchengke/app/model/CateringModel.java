package com.yunchengke.app.model;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringActivityList;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringBookClassify;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringDetail;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringDetailActivity;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringDetailComment;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringDetailDishes;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringDishesList;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringList;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.utils.daemon.VolleyUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class CateringModel extends BaseModel {
    private Context mContext;

    public CateringModel(Handler handler, Context context) {
        super(handler);
        // TODO Auto-generated constructor stub
        mContext = context;
    }

    @Override
    public void changeModelState(int changeState) {
        // TODO Auto-generated method stub

    }

    @Override
    public void changeModelState(final Message changeStateMessage) {
        // TODO Auto-generated method stub
        HashMap<String, String> params_map = null;
        RequestQueue requestQueue;
        String url;
        StringRequest request;
        JsonObjectRequest jRequest;
        switch (changeStateMessage.what) {

/**
 *  列表餐厅
 */
            case Constants.MODEL_CATERING_SEARCH_DINING_ROOM:
                params_map = (HashMap<String, String>) changeStateMessage.obj;
//
                Log.d("syb","params_map  "+params_map.toString());
                url = "http://www.icityto.com/X_UserLogic/yesicity2015/canyin_Page/" + VolleyUtil.formatGetParams(params_map);
                Log.d("syb","url="+url);
                requestQueue = Volley.newRequestQueue(mContext);

                request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            Gson gson = new Gson();
                            java.lang.reflect.Type type = new TypeToken<Resp_CateringList>() {}.getType();
                            Resp_CateringList info = gson.fromJson(s, type);
                            Message.obtain(handler, Constants.VIEW_CATERING_SEARCH_DINING_ROOM, info).sendToTarget();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Message.obtain(handler, Constants.VIEW_CATERING_SEARCH_DINING_ROOM, "解析餐厅列表json出错").sendToTarget();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        String message = "网络出错";
                        Message.obtain(handler, Constants.VIEW_CATERING_SEARCH_DINING_ROOM, message).sendToTarget();
                        Log.e(getTAG(), "onErrorResponse=" + volleyError.getMessage());
                    }
                });
                requestQueue.add(request);
                break;

            /**
             *  餐饮活动列表
             */
            case Constants.MODEL_CATERING_SEARCH_ACTIVITY:
                params_map = (HashMap<String, String>) changeStateMessage.obj;
                url = "http://www.icityto.com/X_UserLogic/yesicity2015/canyin_active_Page/" + VolleyUtil.formatGetParams(params_map);
                requestQueue = Volley.newRequestQueue(mContext);
                request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            Gson gson = new Gson();
                            java.lang.reflect.Type type = new TypeToken<Resp_CateringActivityList>() {}.getType();
                            Resp_CateringActivityList info = gson.fromJson(s, type);
                            Message.obtain(handler, Constants.VIEW_CATERING_SEARCH_ACTIVITY, info).sendToTarget();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Message.obtain(handler, Constants.VIEW_CATERING_SEARCH_ACTIVITY, "解析餐饮活动列表json出错").sendToTarget();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        String message = "网络出错";
                        Message.obtain(handler, Constants.VIEW_CATERING_SEARCH_ACTIVITY, message).sendToTarget();
                        Log.e(getTAG(), "onErrorResponse=" + volleyError.getMessage());
                    }
                });
                requestQueue.add(request);
                break;

            /**
             *  餐厅详情
             */
            case Constants.MODEL_CATERING_DETAIL:
                params_map = (HashMap<String, String>) changeStateMessage.obj;
                url = "http://a.ezhanyun.com/X_UserLogic/yesicity2015/canyin_Info_Page/" + VolleyUtil.formatGetParams(params_map);
                requestQueue = Volley.newRequestQueue(mContext);
                request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            Gson gson = new Gson();
                            java.lang.reflect.Type type = new TypeToken<Resp_CateringDetail>() {}.getType();
                            Resp_CateringDetail info = gson.fromJson(s, type);
                            Message.obtain(handler, Constants.VIEW_CATERING_DETAIL, info).sendToTarget();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Message.obtain(handler, Constants.VIEW_CATERING_DETAIL, "解析餐厅详情json出错").sendToTarget();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        String message = "网络出错";
                        Message.obtain(handler, Constants.VIEW_CATERING_DETAIL, message).sendToTarget();
                        Log.e(getTAG(), "onErrorResponse=" + volleyError.getMessage());
                    }
                });
                //request.setRetryPolicy(new DefaultRetryPolicy(VOLLEY_TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(request);
                break;

            /**
             * 本店菜品
             */
            case Constants.MODEL_CATERING_DETAIL_DISH_LOCAL:
                params_map = (HashMap<String, String>) changeStateMessage.obj;
                url = "http://www.icityto.com/X_UserLogic/yesicity2015/canyin_dishes_Page/" + VolleyUtil.formatGetParams(params_map);
                requestQueue = Volley.newRequestQueue(mContext);
                request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            Gson gson = new Gson();
                            java.lang.reflect.Type type = new TypeToken<Resp_CateringDetailDishes>() {}.getType();
                            Resp_CateringDetailDishes info = gson.fromJson(s, type);
                            Message.obtain(handler, Constants.VIEW_CATERING_DETAIL_DISH_LOCAL, info).sendToTarget();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Message.obtain(handler, Constants.VIEW_CATERING_DETAIL_DISH_LOCAL, "解析本店菜品json出错").sendToTarget();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        String message = "网络出错";
                        Message.obtain(handler, Constants.VIEW_CATERING_DETAIL_DISH_LOCAL, message).sendToTarget();
                        Log.e(getTAG(), "onErrorResponse=" + volleyError.getMessage());
                    }
                });
                //request.setRetryPolicy(new DefaultRetryPolicy(VOLLEY_TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(request);
                break;

            /**
             * 热门菜品
             */
            case Constants.MODEL_CATERING_DETAIL_DISH_TOP:
                params_map = (HashMap<String, String>) changeStateMessage.obj;
                url = "http://www.icityto.com/X_UserLogic/yesicity2015/canyin_dishes_Page/" + VolleyUtil.formatGetParams(params_map);
                requestQueue = Volley.newRequestQueue(mContext);
                request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            Gson gson = new Gson();
                            java.lang.reflect.Type type = new TypeToken<Resp_CateringDetailDishes>() {}.getType();
                            Resp_CateringDetailDishes info = gson.fromJson(s, type);
                            Message.obtain(handler, Constants.VIEW_CATERING_DETAIL_DISH_TOP, info).sendToTarget();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Message.obtain(handler, Constants.VIEW_CATERING_DETAIL_DISH_TOP, "解析热门菜品json出错").sendToTarget();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        String message = "网络出错";
                        Message.obtain(handler, Constants.VIEW_CATERING_DETAIL_DISH_TOP, message).sendToTarget();
                        Log.e(getTAG(), "onErrorResponse=" + volleyError.getMessage());
                    }
                });
                //request.setRetryPolicy(new DefaultRetryPolicy(VOLLEY_TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(request);
                break;
            /**
             * 评论列表
             */
            case Constants.MODEL_CATERING_DETAIL_COMMENT:
                params_map = (HashMap<String, String>) changeStateMessage.obj;
                url = "http://www.icityto.com/X_UserLogic/yesicity2015/canyin_comm_Page/" + VolleyUtil.formatGetParams(params_map);
                requestQueue = Volley.newRequestQueue(mContext);
                request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            Gson gson = new Gson();
                            java.lang.reflect.Type type = new TypeToken<Resp_CateringDetailComment>() {}.getType();
                            Resp_CateringDetailComment info = gson.fromJson(s, type);
                            Message.obtain(handler, Constants.VIEW_CATERING_DETAIL_COMMENT, info).sendToTarget();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Message.obtain(handler, Constants.VIEW_CATERING_DETAIL_COMMENT, "解析评论列表json出错").sendToTarget();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        String message = "网络出错";
                        Message.obtain(handler, Constants.VIEW_CATERING_DETAIL_COMMENT, message).sendToTarget();
                        Log.e(getTAG(), "onErrorResponse=" + volleyError.getMessage());
                    }
                });
                //request.setRetryPolicy(new DefaultRetryPolicy(VOLLEY_TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(request);
                break;

            /**
             * 全部菜品
             */
            case Constants.MODEL_CATERING_DETAIL_ALL_DISHES:
                params_map = (HashMap<String, String>) changeStateMessage.obj;
                url = "http://www.icityto.com/X_UserLogic/yesicity2015/canyin_alldishes_Page/" + VolleyUtil.formatGetParams(params_map);
                requestQueue = Volley.newRequestQueue(mContext);
                request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            Gson gson = new Gson();
                            java.lang.reflect.Type type = new TypeToken<Resp_CateringDishesList>() {}.getType();
                            Resp_CateringDishesList info = gson.fromJson(s, type);
                            Message.obtain(handler, Constants.VIEW_CATERING_DETAIL_ALL_DISHES, info).sendToTarget();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Message.obtain(handler, Constants.VIEW_CATERING_DETAIL_ALL_DISHES, "解析全部菜品列表json出错").sendToTarget();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        String message = "网络出错";
                        Message.obtain(handler, Constants.VIEW_CATERING_DETAIL_ALL_DISHES, message).sendToTarget();
                        Log.e(getTAG(), "onErrorResponse=" + volleyError.getMessage());
                    }
                });
                //request.setRetryPolicy(new DefaultRetryPolicy(VOLLEY_TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(request);
                break;
            /**
             * 预订点餐 类别
             */
            case Constants.MODEL_CATERING_BOOK_CLASSIFY:
                params_map = (HashMap<String, String>) changeStateMessage.obj;
                url = "http://www.icityto.com/X_UserLogic/yesicity2015/order_Page/" + VolleyUtil.formatGetParams(params_map);
                requestQueue = Volley.newRequestQueue(mContext);
                request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            Gson gson = new Gson();
                            java.lang.reflect.Type type = new TypeToken<Resp_CateringBookClassify>() {}.getType();
                            Resp_CateringBookClassify info = gson.fromJson(s, type);
                            Message.obtain(handler, Constants.VIEW_CATERING_BOOK_CLASSIFY, info).sendToTarget();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Message.obtain(handler, Constants.VIEW_CATERING_BOOK_CLASSIFY, "解析预订点餐 类别json出错").sendToTarget();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        String message = "网络出错";
                        Message.obtain(handler, Constants.VIEW_CATERING_BOOK_CLASSIFY, message).sendToTarget();
                        Log.e(getTAG(), "onErrorResponse=" + volleyError.getMessage());
                    }
                });
                //request.setRetryPolicy(new DefaultRetryPolicy(VOLLEY_TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(request);
                break;

            /**
             * 预订点餐 通过类别搜索菜品
             */
            case Constants.MODEL_CATERING_BOOK_DISHES:
                params_map = (HashMap<String, String>) changeStateMessage.obj;
                url = "http://www.icityto.com/X_UserLogic/yesicity2015/dishesbyclass_Page/" + VolleyUtil.formatGetParams(params_map);
                requestQueue = Volley.newRequestQueue(mContext);
                request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            Gson gson = new Gson();
                            java.lang.reflect.Type type = new TypeToken<Resp_CateringDishesList>() {}.getType();
                            Resp_CateringDishesList info = gson.fromJson(s, type);
                            Message.obtain(handler, Constants.VIEW_CATERING_BOOK_DISHES, changeStateMessage.arg1,0,info).sendToTarget();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Message.obtain(handler, Constants.VIEW_CATERING_BOOK_DISHES, "解析预订点餐 通过类别搜索菜品json出错").sendToTarget();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        String message = "网络出错";
                        Message.obtain(handler, Constants.VIEW_CATERING_BOOK_DISHES,message).sendToTarget();
                        Log.e(getTAG(), "onErrorResponse=" + volleyError.getMessage());
                    }
                });
                //request.setRetryPolicy(new DefaultRetryPolicy(VOLLEY_TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(request);
                break;

            /**
             * 关注/取消
             */
            case Constants.MODEL_CATERING_DETAIL_ATTENTION:
                params_map = (HashMap<String, String>) changeStateMessage.obj;
                url = "http://a.ezhanyun.com/X_UserLogic/yesicity2015/canyinFollow_Edit/" + VolleyUtil.formatGetParams(params_map);
                requestQueue = Volley.newRequestQueue(mContext);
                jRequest = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String resultState = "";
                        String message= "";
                        try {
                            resultState = jsonObject.getString("resultState");
                            if("success".equals(resultState)){
                                Message.obtain(handler, Constants.VIEW_CATERING_DETAIL_ATTENTION, resultState).sendToTarget();
                            }else{
                                message = jsonObject.getString("message");
                                Message.obtain(handler, Constants.VIEW_CATERING_DETAIL_ATTENTION, message).sendToTarget();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.e(getTAG(), "关注/取消 onResponse:messgage=" + message);
                    }
                },new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        String message = "网络出错";
                        Message.obtain(handler, Constants.VIEW_CATERING_DETAIL_ATTENTION, message).sendToTarget();
                        Log.e(getTAG(), "onErrorResponse=" + volleyError.getMessage());
                    }
                });

                requestQueue.add(jRequest);
                break;

            /**
             *  新增评论
             */
            case Constants.MODEL_CATERING_DETAIL_COMMENT_ADD:
                params_map = (HashMap<String, String>) changeStateMessage.obj;
                url = "http://www.icityto.com/X_UserLogic/yesicity2015/canyin_trends_Add/"+ VolleyUtil.formatGetParams(params_map);
                requestQueue = Volley.newRequestQueue(mContext);
                jRequest = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String resultState = "";
                        String message= "";
                        try {
                            resultState = jsonObject.getString("resultState");
                            if("success".equals(resultState)){
                                Message.obtain(handler, Constants.VIEW_CATERING_DETAIL_COMMENT_ADD, resultState).sendToTarget();
                            }else{
                                message = jsonObject.getString("message");
                                Message.obtain(handler, Constants.VIEW_CATERING_DETAIL_COMMENT_ADD, message).sendToTarget();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.e(getTAG(), "新增评论 onResponse:messgage=" + message);
                    }
                },new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        String message = "网络出错";
                        Message.obtain(handler, Constants.VIEW_CATERING_DETAIL_COMMENT_ADD, message).sendToTarget();
                        Log.e(getTAG(), "onErrorResponse=" + volleyError.getMessage());
                    }
                });
                requestQueue.add(jRequest);
                break;

            /**
             * 商家详情活动资讯
             */
            case Constants.MODEL_CATERING_DETAIL_ACTIVITY:
                params_map = (HashMap<String, String>) changeStateMessage.obj;
                url = "http://www.icityto.com/X_UserLogic/yesicity2015/activebyresta_Page/" + VolleyUtil.formatGetParams(params_map);
                requestQueue = Volley.newRequestQueue(mContext);
                request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            Gson gson = new Gson();
                            java.lang.reflect.Type type = new TypeToken<Resp_CateringDetailActivity>() {}.getType();
                            Resp_CateringDetailActivity info = gson.fromJson(s, type);
                            Message.obtain(handler, Constants.VIEW_CATERING_DETAIL_ACTIVITY,info).sendToTarget();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Message.obtain(handler, Constants.VIEW_CATERING_DETAIL_ACTIVITY, "解析商家详情活动资讯json出错").sendToTarget();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        String message = "网络出错";
                        Message.obtain(handler, Constants.VIEW_CATERING_DETAIL_ACTIVITY,message).sendToTarget();
                        Log.e(getTAG(), "onErrorResponse=" + volleyError.getMessage());
                    }
                });
                requestQueue.add(request);
                break;

            /**
             * 预订点餐新增订单
             */
            case Constants.MODEL_CATERING_BOOK_ADD:
                params_map = (HashMap<String, String>) changeStateMessage.obj;
                url = "http://www.icityto.com/X_UserLogic/yesicity2015/dishorder_Add/"+ VolleyUtil.formatGetParams(params_map);
                Log.e("gdfgd fgdfg", url);
                requestQueue = Volley.newRequestQueue(mContext);
                jRequest = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String resultState = "";
                        String message= "";
                        try {
                            resultState = jsonObject.getString("resultState");
                            if("success".equals(resultState)){
                                Message.obtain(handler, Constants.VIEW_CATERING_BOOK_ADD, Integer.valueOf(jsonObject.getString("Id")),0,resultState).sendToTarget();
                            }else{
                                message = jsonObject.getString("message");
                                Message.obtain(handler, Constants.VIEW_CATERING_BOOK_ADD, message).sendToTarget();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.e(getTAG(), "预订点餐新增订单 onResponse:messgage=" + message);
                    }
                },new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        String message = "网络出错";
                        Message.obtain(handler, Constants.VIEW_CATERING_BOOK_ADD, message).sendToTarget();
                        Log.e(getTAG(), "onErrorResponse=" + volleyError.getMessage());
                    }
                });
                requestQueue.add(jRequest);
                break;

            /**
             * 外卖新增订单
             */
            case Constants.MODEL_CATERING_TAKE_OUT_ADD:
                params_map = (HashMap<String, String>) changeStateMessage.obj;
                url = "http://www.icityto.com/X_UserLogic/yesicity2015/dishorder_JsonAdd/"+ VolleyUtil.formatGetParams(params_map);
                Log.e("gdfgd fgdfg", url);
                requestQueue = Volley.newRequestQueue(mContext);
                jRequest = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String resultState = "";
                        String message= "";
                        try {
                            resultState = jsonObject.getString("resultState");
                            if("success".equals(resultState)){
                                Message.obtain(handler, Constants.VIEW_CATERING_TAKE_OUT_ADD, Integer.valueOf(jsonObject.getString("Id")),0,resultState).sendToTarget();
                            }else{
                                message = jsonObject.getString("message");
                                Message.obtain(handler, Constants.VIEW_CATERING_TAKE_OUT_ADD, message).sendToTarget();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.e(getTAG(), "预订点餐新增订单 onResponse:messgage=" + message);
                    }
                },new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        String message = "网络出错";
                        Message.obtain(handler, Constants.VIEW_CATERING_TAKE_OUT_ADD, message).sendToTarget();
                        Log.e(getTAG(), "onErrorResponse=" + volleyError.getMessage());
                    }
                });
                requestQueue.add(jRequest);
                break;
        }
    }
}
