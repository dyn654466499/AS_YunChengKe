package com.yunchengke.app.bean.daemon.catering;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2016/1/17 0017.
 */
public class Resp_CateringDetail implements Parcelable{
    public String total;
    public List<Rows> rows;


    protected Resp_CateringDetail(Parcel in) {
        total = in.readString();
        rows = in.createTypedArrayList(Rows.CREATOR);
    }

    public static final Creator<Resp_CateringDetail> CREATOR = new Creator<Resp_CateringDetail>() {
        @Override
        public Resp_CateringDetail createFromParcel(Parcel in) {
            return new Resp_CateringDetail(in);
        }

        @Override
        public Resp_CateringDetail[] newArray(int size) {
            return new Resp_CateringDetail[size];
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
         *  商家ID
         */
    public String X6_Product_Id;
        /**
         *  店铺负责人Id
         */
        public String X6_Product_Product;
        /**
         * 是否关注
         */
    public String isFollow;
        /**
         * 店铺简介
         */
    public String Field_DPJJ;
        /**
         * 店铺地址
         */
    public String Field_DPDZ;
        /**
         * 店铺头图
         */
        public String Field_Pic;
        /**
         * 店铺网址
         */
    public String Field_DPWZ;

        /**
         * 店铺名称
         */
        public String Field_DPMC;
        /**
         * 店铺营业时间
         */
        public String Field_DPYYSJ;
        /**
         * 公交信息
         */
        public String Field_GJXX;

        /**
         * 店铺分类
         */
        public String Field_DPFL;

        /**
         * 店铺电话
         */
        public String Field_DPDH;

        /**
         * 店铺小图
         */
        public String X6_Product_Pic;

        /**
         * 是否外卖
         */
        public String Field_SFWM;
        /**
         *
         *  商家坐标
         */
        public String Field_DPZB;

        protected Rows(Parcel in) {
            X6_Product_Id = in.readString();
            X6_Product_Product = in.readString();
            isFollow = in.readString();
            Field_DPJJ = in.readString();
            Field_DPDZ = in.readString();
            Field_Pic = in.readString();
            Field_DPWZ = in.readString();
            Field_DPMC = in.readString();
            Field_DPYYSJ = in.readString();
            Field_GJXX = in.readString();
            Field_DPFL= in.readString();
            Field_DPDH= in.readString();
            X6_Product_Pic= in.readString();
            Field_SFWM= in.readString();
            Field_DPZB= in.readString();
        }
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(X6_Product_Id);
            dest.writeString(X6_Product_Product);
            dest.writeString(isFollow);
            dest.writeString(Field_DPJJ);
            dest.writeString(Field_DPDZ);
            dest.writeString(Field_Pic);
            dest.writeString(Field_DPWZ);
            dest.writeString(Field_DPMC);
            dest.writeString(Field_DPYYSJ);
            dest.writeString(Field_GJXX);

            dest.writeString(Field_DPFL);
            dest.writeString(Field_DPDH);
            dest.writeString(X6_Product_Pic);
            dest.writeString(Field_SFWM);
            dest.writeString(Field_DPZB);
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
