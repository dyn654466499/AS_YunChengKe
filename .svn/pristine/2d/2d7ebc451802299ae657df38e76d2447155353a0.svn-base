package com.yunchengke.app.bean.daemon.catering;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/1/17 0017.
 */
public class Resp_CateringDishesList implements Parcelable{
    public String total;
    public ArrayList<Rows> rows;
    public String PageCount;
    public String page;
    public String time;

    protected Resp_CateringDishesList(Parcel in) {
        total = in.readString();
        rows = in.createTypedArrayList(Rows.CREATOR);
        PageCount = in.readString();
        page = in.readString();
        time = in.readString();
    }

    public static final Creator<Resp_CateringDishesList> CREATOR = new Creator<Resp_CateringDishesList>() {
        @Override
        public Resp_CateringDishesList createFromParcel(Parcel in) {
            return new Resp_CateringDishesList(in);
        }

        @Override
        public Resp_CateringDishesList[] newArray(int size) {
            return new Resp_CateringDishesList[size];
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
         *  订单ID
         */
    public String X6_Product_Id;
        /**
         *   菜品图片
         */
        public String X6_Product_Pic;
        /**
         * 菜品名称
         */
       public String Field_CPPM;
        /**
         * 菜品单价
         */
        public String Field_CPDJ;
        /**
         *  忽略
         */
        public String TonyX7_Pager_RowRank;

        protected Rows(Parcel in) {
            X6_Product_Id = in.readString();
            X6_Product_Pic = in.readString();
            Field_CPPM = in.readString();
            Field_CPDJ = in.readString();
            TonyX7_Pager_RowRank = in.readString();
        }
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(X6_Product_Id);
            dest.writeString(X6_Product_Pic);
            dest.writeString(Field_CPPM);
            dest.writeString(Field_CPDJ);
            dest.writeString(TonyX7_Pager_RowRank);
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
