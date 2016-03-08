package com.yunchengke.app.bean.daemon.catering;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2016/1/17 0017.
 */
public class Resp_CateringDetailActivity implements Parcelable{
    public String total;
    public List<Rows> rows;


    protected Resp_CateringDetailActivity(Parcel in) {
        total = in.readString();
        rows = in.createTypedArrayList(Rows.CREATOR);
    }

    public static final Creator<Resp_CateringDetailActivity> CREATOR = new Creator<Resp_CateringDetailActivity>() {
        @Override
        public Resp_CateringDetailActivity createFromParcel(Parcel in) {
            return new Resp_CateringDetailActivity(in);
        }

        @Override
        public Resp_CateringDetailActivity[] newArray(int size) {
            return new Resp_CateringDetailActivity[size];
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
         *  活动ID
         */
    public String X6_Product_Id;
        /**
         * 商家ID
         */
       public String Field_PId;
        /**
         * 活动名称
         */
        public String Field_HDMC;

        protected Rows(Parcel in) {
            X6_Product_Id = in.readString();
            Field_PId = in.readString();
            Field_HDMC = in.readString();
        }
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(X6_Product_Id);
            dest.writeString(Field_PId);
            dest.writeString(Field_HDMC);
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
