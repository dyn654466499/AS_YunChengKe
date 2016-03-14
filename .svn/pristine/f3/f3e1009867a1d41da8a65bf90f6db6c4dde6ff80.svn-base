package com.yunchengke.app.bean.daemon.catering;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2016/1/17 0017.
 */
public class Resp_CateringBookClassify implements Parcelable{
    public String total;
    public List<Rows> rows;


    protected Resp_CateringBookClassify(Parcel in) {
        total = in.readString();
        rows = in.createTypedArrayList(Rows.CREATOR);
    }

    public static final Creator<Resp_CateringBookClassify> CREATOR = new Creator<Resp_CateringBookClassify>() {
        @Override
        public Resp_CateringBookClassify createFromParcel(Parcel in) {
            return new Resp_CateringBookClassify(in);
        }

        @Override
        public Resp_CateringBookClassify[] newArray(int size) {
            return new Resp_CateringBookClassify[size];
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
         *  类别ID
         */
    public String X6_Product_Id;
        /**
         * 类别名
         */
       public String Field_CPFL;

        protected Rows(Parcel in) {
            X6_Product_Id = in.readString();
            Field_CPFL = in.readString();
        }
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(X6_Product_Id);
            dest.writeString(Field_CPFL);
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
