package com.yunchengke.app.bean.daemon.catering;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2016/1/17 0017.
 */
public class Resp_CateringActivityList implements Parcelable{
    public String total;
    public List<Rows> rows;
    public String PageCount;
    public String page;
    public String time;

    protected Resp_CateringActivityList(Parcel in) {
        total = in.readString();
        rows = in.createTypedArrayList(Rows.CREATOR);
        PageCount = in.readString();
        page = in.readString();
        time = in.readString();
    }

    public static final Creator<Resp_CateringActivityList> CREATOR = new Creator<Resp_CateringActivityList>() {
        @Override
        public Resp_CateringActivityList createFromParcel(Parcel in) {
            return new Resp_CateringActivityList(in);
        }

        @Override
        public Resp_CateringActivityList[] newArray(int size) {
            return new Resp_CateringActivityList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(total);
        dest.writeTypedList(rows);
        dest.writeString(PageCount);
        dest.writeString(page);
        dest.writeString(time);
    }


    public static class Rows implements Parcelable{
        /**
         *  活动ID
         */
    public String X6_Product_Id;
        /**
         * 活动图片
         */
        public String X6_Product_Pic;
        /**
         * 活动名称
         */
        public String Field_HDMC;
        /**
         * 活动地址
         */
    public String Field_HDDD;
        /**
         *  商家Id
         */
    public String Field_SJID;
        /**
         *  忽略
         */
        public String Field_HDFL1;
        /**
         *  活动关注人数
         */
        public String gzrs;
        /**
         *  报名人数限制
         */
        public String Field_HDBMRSXZ;
        /**
         *  已报名人数
         */
        public String Field_HDBMRS;
        /**
         *  报名费用
         */
        public String Field_HDBMFY;
        /**
         *  活动电话
         */
        public String Field_HDDH;
        /**
         *  活动简介
         */
        public String Field_HDJJ;
        /**
         *  活动分类
         */
        public String Field_HDFL;
        /**
         *  忽略
         */
        public String TonyX_Pager_RowRank;
        /**
         *  活动时间
         */
        public String Field_HDSJ;

        protected Rows(Parcel in) {
            X6_Product_Id = in.readString();
            X6_Product_Pic = in.readString();
            Field_HDMC = in.readString();
            Field_HDDD = in.readString();
            Field_SJID = in.readString();

            Field_HDFL1 = in.readString();
            gzrs = in.readString();
            Field_HDBMRSXZ = in.readString();
            Field_HDBMRS = in.readString();
            Field_HDBMFY = in.readString();
            Field_HDDH = in.readString();
            Field_HDJJ = in.readString();
            Field_HDFL = in.readString();
            TonyX_Pager_RowRank = in.readString();
            Field_HDSJ = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(X6_Product_Id);
            dest.writeString(X6_Product_Pic);
            dest.writeString(Field_HDMC);
            dest.writeString(Field_HDDD);
            dest.writeString(Field_SJID);

            dest.writeString(Field_HDFL1);
            dest.writeString(gzrs);
            dest.writeString(Field_HDBMRSXZ);
            dest.writeString(Field_HDBMRS);
            dest.writeString(Field_HDBMFY);
            dest.writeString(Field_HDDH);
            dest.writeString(Field_HDJJ);
            dest.writeString(Field_HDFL);
            dest.writeString(TonyX_Pager_RowRank);
            dest.writeString(Field_HDSJ);
        }
        public static final Creator<Rows> CREATOR = new Creator<Rows>() {
            @Override
            public Rows createFromParcel(Parcel in) {
                return new Rows(in);
            }

            @Override
            public Rows[] newArray(int size) {
                return new Rows[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }


    }

}
