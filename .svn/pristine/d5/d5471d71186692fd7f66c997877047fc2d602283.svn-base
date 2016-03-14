package com.yunchengke.app.bean.daemon.catering;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2016/1/17 0017.
 */
public class Resp_CateringDishesDetail implements Parcelable{
    public String total;
    public List<Rows> rows;


    protected Resp_CateringDishesDetail(Parcel in) {
        total = in.readString();
        rows = in.createTypedArrayList(Rows.CREATOR);
    }

    public static final Creator<Resp_CateringDishesDetail> CREATOR = new Creator<Resp_CateringDishesDetail>() {
        @Override
        public Resp_CateringDishesDetail createFromParcel(Parcel in) {
            return new Resp_CateringDishesDetail(in);
        }

        @Override
        public Resp_CateringDishesDetail[] newArray(int size) {
            return new Resp_CateringDishesDetail[size];
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

        protected Rows(Parcel in) {
            X6_Product_Id = in.readString();
            X6_Product_Pic = in.readString();
            Field_CPPM = in.readString();
        }
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(X6_Product_Id);
            dest.writeString(X6_Product_Pic);
            dest.writeString(Field_CPPM);
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
