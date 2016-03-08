package com.yunchengke.app.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.yunchengke.app.bean.MediaList;
import com.yunchengke.app.bean.NewsDetail;
import com.yunchengke.app.bean.dynamic.DynamicDetailsEntity;
import com.yunchengke.app.consts.Constants;
import com.yunchengke.app.ui.activity.city.CityDetailsActivity;
import com.yunchengke.app.ui.activity.city.ToPayActivity;
import com.yunchengke.app.ui.activity.city.YCKAddCommentActivity;
import com.yunchengke.app.ui.activity.city.YCKCommentActivity;
import com.yunchengke.app.ui.activity.city.YCKJoinedActivity;
import com.yunchengke.app.ui.activity.city.YCKPhotoActivity;
import com.yunchengke.app.ui.activity.common.UserInfoActivity;
import com.yunchengke.app.ui.activity.common.ZoomGalleryActivity;
import com.yunchengke.app.ui.activity.daemon.CateringDetailActivity;
import com.yunchengke.app.ui.activity.daemon.EditPersonInfoActivity;
import com.yunchengke.app.ui.activity.dynamic.DynamicDetailActivity;
import com.yunchengke.app.ui.activity.dynamic.FansListActivity;
import com.yunchengke.app.ui.activity.dynamic.FollowListActivity;
import com.yunchengke.app.ui.activity.group.CategoryDetailActivity;
import com.yunchengke.app.ui.activity.group.CreateGroupActivity;
import com.yunchengke.app.ui.activity.group.CreateTopicActivity;
import com.yunchengke.app.ui.activity.group.GroupDetailActivity;
import com.yunchengke.app.ui.activity.group.GroupMemberActivity;
import com.yunchengke.app.ui.activity.group.SelectCategoryActivity;
import com.yunchengke.app.ui.activity.group.TopicDetailActivity;
import com.yunchengke.app.ui.activity.headline.InstantNewsActivity;
import com.yunchengke.app.ui.activity.headline.MediaDetailActivity;
import com.yunchengke.app.ui.activity.headline.MediaMemberActivity;
import com.yunchengke.app.ui.activity.headline.NewsDetailActivity;
import com.yunchengke.app.ui.activity.headline.PastNewsActivity;

import java.util.ArrayList;

import ui.SelectPicActivity;

/**
 * 类名：UIHelper <br/>
 * 描述：跳转Activity工具类
 * 创建时间：2016/01/10 16:52
 *
 * @author hanter
 * @version 1.0
 */
public class UIHelper {

    public static void gotoTopicDetailActivity(Context context, long topicId) {
        Intent intent = new Intent(context, TopicDetailActivity.class);
        intent.putExtra(TopicDetailActivity.EXTRA_TOPIC_ID, topicId);
        context.startActivity(intent);
    }

    public static void gotoGroupDetailActivity(Context context, long groupId) {
        Intent intent = new Intent(context, GroupDetailActivity.class);
        intent.putExtra(GroupDetailActivity.EXTRA_GROUP_ID, groupId);
        context.startActivity(intent);
    }

    public static void gotoCreateTopicActivity(Activity activity, long groupId) {
        Intent intent = new Intent(activity, CreateTopicActivity.class);
        intent.putExtra(CreateTopicActivity.EXTRA_GROUP_ID, groupId);
        activity.startActivityForResult(intent, GroupDetailActivity.REQUEST_CREATE_TOPIC);
    }

    public static void gotoGroupMemberActivity(Context context, long groupId) {
        Intent intent = new Intent(context, GroupMemberActivity.class);
        intent.putExtra(GroupMemberActivity.EXTRA_GROUP_ID, groupId);
        context.startActivity(intent);
    }

    public static void gotoCreateGroupActivity(Activity activity, int categoryId, String categoryName) {
        Intent intent = new Intent(activity, CreateGroupActivity.class);
        intent.putExtra(CreateGroupActivity.EXTRA_CATEGORY_ID, categoryId);
        intent.putExtra(CreateGroupActivity.EXTRA_CATEGORY_NAME, categoryName);
        activity.startActivityForResult(intent, CreateGroupActivity.REQUEST_CREATE_GROUP);
    }

    public static void gotoCategoryDetailActivity(Context context, int categoryId) {
        Intent intent = new Intent(context, CategoryDetailActivity.class);
        intent.putExtra(CategoryDetailActivity.EXTRA_CATEGORY_ID, categoryId);
        context.startActivity(intent);
    }

    public static void gotoSelectCategoryActivity(Context context) {
        Intent intent = new Intent(context, SelectCategoryActivity.class);
        context.startActivity(intent);
    }

    /**
     * 新闻详情
     * @param context Context
     */
    public static void gotoNewsDetailActivity(Context context, NewsDetail.RowsEntity newsDetail) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(NewsDetailActivity.EXTRA_NEWS_DETAIL, newsDetail);
        context.startActivity(intent);
    }

    public static void gotoNewsDetailActivity(Context context, long newsId) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(NewsDetailActivity.EXTRA_NEWS_ID, newsId);
        context.startActivity(intent);
    }

    /**
     * 跳转到媒体详情
     * @param context context
     * @param mediaInfo 媒体信息
     */
    public static void gotoMediaDetailActivity(Context context, MediaList.RowsEntity mediaInfo) {
        Intent intent = new Intent(context, MediaDetailActivity.class);
        intent.putExtra(MediaDetailActivity.EXTRA_MEDIA_INFO, mediaInfo);
        context.startActivity(intent);
    }

    /**
     * 跳转到媒体详情
     * @param context context
     * @param mediaId 媒体ID
     */
    public static void gotoMediaDetailActivity(Context context, long mediaId) {
        Intent intent = new Intent(context, MediaDetailActivity.class);
        intent.putExtra(MediaDetailActivity.EXTRA_MEDIA_ID, mediaId);
        context.startActivity(intent);
    }

    /**
     * 媒体成员列表
     * @param context Context
     * @param mediaId 媒体ID
     */
    public static void gotoMediaMemberActivity(Context context, long mediaId) {
        Intent intent = new Intent(context, MediaMemberActivity.class);
        intent.putExtra(MediaMemberActivity.EXTRA_MEDIA_ID, mediaId);
        context.startActivity(intent);
    }

    /**
     * 跳到现场/及时新闻列表
     * @param context Context
     * @param type 类型，1：现场，0：即时
     * @param mediaId 媒体ID
     */
    public static void gotoInstantNewsActivity(Context context, int type, long mediaId) {
        Intent intent = new Intent(context, InstantNewsActivity.class);
        intent.putExtra(InstantNewsActivity.EXTRA_MEDIA_ID, mediaId);
        intent.putExtra(InstantNewsActivity.EXTRA_TYPE, type);
        context.startActivity(intent);
    }

    /**
     * 跳转到往期新闻
     * @param context Context
     * @param mediaId 媒体ID
     */
    public static void gotoPastNewsActivity(Context context, long mediaId) {
        Intent intent = new Intent(context, PastNewsActivity.class);
        intent.putExtra(PastNewsActivity.EXTRA_MEDIA_ID, mediaId);
        context.startActivity(intent);
    }

    /**
     * 跳到关注列表
     * @param context Context
     * @param userId 用户ID
     */
    public static void gotoFollowListActivity(Context context, long userId) {
        Intent intent = new Intent(context, FollowListActivity.class);
        intent.putExtra(FollowListActivity.EXTRA_USER_ID, userId);
        context.startActivity(intent);
    }

    /**
     * 跳到粉丝列表
     * @param context Context
     * @param userId 用户ID
     */
    public static void gotoFansListActivity(Context context, long userId) {
        Intent intent = new Intent(context, FansListActivity.class);
        intent.putExtra(FansListActivity.EXTRA_USER_ID, userId);
        context.startActivity(intent);
    }

    /**
     * 用户信息
     * @param context Context
     * @param userId 用户ID
     */
    public static void gotoUserInfoActivity(Context context, long userId) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        intent.putExtra(UserInfoActivity.EXTRA_USER_ID, userId);
        context.startActivity(intent);
    }

    /**
     * 动态详情
     * @param context Context
     * @param entity 动态详情
     */
    public static void gotoDynamicDetailActivity(Context context, DynamicDetailsEntity entity) {
        Intent intent = new Intent(context, DynamicDetailActivity.class);
        intent.putExtra(DynamicDetailActivity.EXTRA_DYNAMIC_DETAIL, entity);
        context.startActivity(intent);
    }

    /**
     * 缩放查看图片的页面
     * @param context Context
     * @param imageUrls 图片链接链接
     */
    public static void gotoZoomGalleryActivity(Context context, ArrayList<String> imageUrls, int position) {
        Intent intent = new Intent(context, ZoomGalleryActivity.class);
        intent.putExtra(ZoomGalleryActivity.EXTRA_IMAGE_LIST, imageUrls);
        intent.putExtra(ZoomGalleryActivity.EXTRA_IMAGE_POSITION, position);
        context.startActivity(intent);
    }

    /**
     * 跳转到活动详情
     * @param context Context
     */
    public static void gotoCityDetailsActivity(Context context,  int city_id) {
        Intent intent = new Intent(context, CityDetailsActivity.class);
        intent.putExtra(CityDetailsActivity.EXTRA_CITY_ID, city_id);
        context.startActivity(intent);
    }


    public static void gotoYCKJoinedActivity(Context context,  int city_id) {
        Intent intent = new Intent(context, YCKJoinedActivity.class);
        intent.putExtra(YCKJoinedActivity.EXTRA_CITY_ID, city_id);
        context.startActivity(intent);
    }


    public static void gotoYCKCommentActivity(Context context,  int city_id) {
        Intent intent = new Intent(context, YCKCommentActivity.class);
        intent.putExtra(YCKCommentActivity.EXTRA_CITY_ID, city_id);
        context.startActivity(intent);
    }

    public static void gotoYCKAddCommentActivity(Activity context,  int city_id) {
        Intent intent = new Intent(context, YCKAddCommentActivity.class);
        intent.putExtra(YCKCommentActivity.EXTRA_CITY_ID, city_id);
        context.startActivityForResult(intent, 10086);
    }

    public static void gotoYCKPhotoActivity(Context context,  String pictures) {
        Intent intent = new Intent(context, YCKPhotoActivity.class);
        intent.putExtra(YCKPhotoActivity.EXTRA_PICTURE, pictures);
        context.startActivity(intent);
    }


    public static void gotoToPayActivity(Context context, String price, String time, String x6_product_id) {
        Intent intent = new Intent(context, ToPayActivity.class);
        intent.putExtra(ToPayActivity.EXTRA_PRICE, price);
        intent.putExtra(ToPayActivity.EXTRA_TIME, time);
        intent.putExtra(ToPayActivity.EXTRA_ID, x6_product_id);
        intent.putExtra("aa", "aa");
        context.startActivity(intent);
    }



    public static void gotoSelectPicActivity(Activity context,int num) {
        Intent intent = new Intent(context, SelectPicActivity.class);
        intent.putExtra(SelectPicActivity.REQUEST_NUMBER, num);
        context.startActivityForResult(intent, SelectPicActivity.REQUEST_SELECT_PIC);
    }

    public static void gotoEditPersonInfoActivity(Context context){
        Intent intent = new Intent(context, EditPersonInfoActivity.class);
//        intent.putExtra(UserInfoActivity.EXTRA_USER_ID, userId);
        context.startActivity(intent);
    }

    /**
     * 跳转到商家详情
     * @param context
     */
    public static void gotoCateringDetailActivity(Context context,long merchant_id){
        Intent intent = new Intent(context, CateringDetailActivity.class);
        intent.putExtra(Constants.KEY_ID, merchant_id);
        context.startActivity(intent);
    }
}
