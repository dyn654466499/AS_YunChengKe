package com.yunchengke.app.bean.daemon.catering;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2016/1/17 0017.
 */
public class Resp_CateringList implements Parcelable{
    public String total;
    public List<Rows> rows;
    public String PageCount;
    public String page;
    public String time;

    protected Resp_CateringList(Parcel in) {
        total = in.readString();
        rows = in.createTypedArrayList(Rows.CREATOR);
        PageCount = in.readString();
        page = in.readString();
        time = in.readString();
    }

    public static final Creator<Resp_CateringList> CREATOR = new Creator<Resp_CateringList>() {
        @Override
        public Resp_CateringList createFromParcel(Parcel in) {
            return new Resp_CateringList(in);
        }

        @Override
        public Resp_CateringList[] newArray(int size) {
            return new Resp_CateringList[size];
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
         * 商铺图片
         */
        public String X6_Product_Pic;
        /**
         * 商家名称
         */
        public String Field_DPMC;
        /**
         * 店铺地址
         */
    public String Field_DPDZ;
        /**
         *  店铺营业时间
         */
    public String Field_DPYYSJ;

        protected Rows(Parcel in) {
            X6_Product_Id = in.readString();
            X6_Product_Pic = in.readString();
            Field_DPMC = in.readString();
            Field_DPDZ = in.readString();
            Field_DPYYSJ = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(X6_Product_Id);
            dest.writeString(X6_Product_Pic);
            dest.writeString(Field_DPMC);
            dest.writeString(Field_DPDZ);
            dest.writeString(Field_DPYYSJ);

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
