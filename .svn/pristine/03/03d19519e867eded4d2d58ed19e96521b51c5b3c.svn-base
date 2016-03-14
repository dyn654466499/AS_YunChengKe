package com.yunchengke.app.bean.daemon.catering;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2016/1/17 0017.
 */
public class Resp_CateringDetailComment implements Parcelable{
    public String total;
    public List<Rows> rows;
    public String PageCount;
    public String page;
    public String time;


    protected Resp_CateringDetailComment(Parcel in) {
        total = in.readString();
        rows = in.createTypedArrayList(Rows.CREATOR);
        PageCount = in.readString();
        page = in.readString();
        time = in.readString();
    }

    public static final Creator<Resp_CateringDetailComment> CREATOR = new Creator<Resp_CateringDetailComment>() {
        @Override
        public Resp_CateringDetailComment createFromParcel(Parcel in) {
            return new Resp_CateringDetailComment(in);
        }

        @Override
        public Resp_CateringDetailComment[] newArray(int size) {
            return new Resp_CateringDetailComment[size];
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
         *  评论ID
         */
        public String X6_Product_Id;
        /**
         *   评论用户Id，显示头像/单击时用
         */
        public String X6_Product_Admin;
        /**
         * 评论用户
         */
        public String Field_PLYH;
        /**
         * 评论时间
         */
        public String Field_PLSJ;
        /**
         * 评论内容
         */
        public String Field_PLNR;
        /**
         * 忽略
         */
        public String TonyX_Pager_RowRank;

        protected Rows(Parcel in) {
            X6_Product_Id = in.readString();
            X6_Product_Admin = in.readString();
            Field_PLYH = in.readString();
            Field_PLSJ = in.readString();
            Field_PLNR = in.readString();
            TonyX_Pager_RowRank = in.readString();
        }
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(X6_Product_Id);
            dest.writeString(X6_Product_Admin);
            dest.writeString(Field_PLYH);
            dest.writeString(Field_PLSJ);
            dest.writeString(Field_PLNR);
            dest.writeString(TonyX_Pager_RowRank);
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
