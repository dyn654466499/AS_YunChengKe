package com.yunchengke.app.bean.daemon.ticket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Resp_FlightInfo implements Parcelable {
	/**
	 * 起飞日期
	 */
	@SerializedName("@Sdate")
	public String Sdate;
	/**
	 * 起飞时间
	 */
	@SerializedName("@Stime")
	public String Stime;
	/**
	 * 降落时间
	 */
	@SerializedName("@Etime")
	public String Etime;
	/**
	 * 起飞机场三字码
	 */
	@SerializedName("@Scity")
	public String Scity;
	/**
	 * 降落机场三字码
	 */
	@SerializedName("@Ecity")
	public String Ecity;
	/**
	 *
	 */
	@SerializedName("@Stop")
	public String Stop;
	/**
	 * 航空公司
	 */
	@SerializedName("@AirLine")
	public String AirLine;
	/**
	 * 航班号
	 */
	@SerializedName("@FlightNo")
	public String FlightNo;
	/**
	 * 价格
	 */
	@SerializedName("@EPiao")
	public String EPiao;
	/**
	 * 折扣
	 */
	@SerializedName("@Tax")
	public String Tax;
	/**
	 *
	 */
	@SerializedName("@AirTax")
	public String AirTax;
	/**
	 *
	 */
	@SerializedName("@Fees")
	public String Fees;
	/**
	 * 飞机型号
	 */
	@SerializedName("@FlightType")
	public String FlightType;
	/**
	 *
	 */
	@SerializedName("@AirTerminal")
	public String AirTerminal;

	/**
	 *  Cabin千万别用@
	 */
	@SerializedName("Cabin")
	public List<Resp_CabinInfo> respCabinInfo;


	protected Resp_FlightInfo(Parcel in) {
		Sdate = in.readString();
		Stime = in.readString();
		Etime = in.readString();
		Scity = in.readString();
		Ecity = in.readString();
		Stop = in.readString();
		AirLine = in.readString();
		FlightNo = in.readString();
		EPiao = in.readString();
		Tax = in.readString();
		AirTax = in.readString();
		Fees = in.readString();
		FlightType = in.readString();
		AirTerminal = in.readString();
		respCabinInfo = in.createTypedArrayList(Resp_CabinInfo.CREATOR);
	}

	public static final Creator<Resp_FlightInfo> CREATOR = new Creator<Resp_FlightInfo>() {
		@Override
		public Resp_FlightInfo createFromParcel(Parcel in) {
			return new Resp_FlightInfo(in);
		}

		@Override
		public Resp_FlightInfo[] newArray(int size) {
			return new Resp_FlightInfo[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(Sdate);
		dest.writeString(Stime);
		dest.writeString(Etime);
		dest.writeString(Scity);
		dest.writeString(Ecity);
		dest.writeString(Stop);
		dest.writeString(AirLine);
		dest.writeString(FlightNo);
		dest.writeString(EPiao);
		dest.writeString(Tax);
		dest.writeString(AirTax);
		dest.writeString(Fees);
		dest.writeString(FlightType);
		dest.writeString(AirTerminal);
		dest.writeTypedList(respCabinInfo);
	}
}
