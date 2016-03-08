package com.yunchengke.app.ui.activity.daemon;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunchengke.app.R;
import com.yunchengke.app.bean.daemon.ticket.Req_FlightInfo;
import com.yunchengke.app.bean.daemon.ticket.Resp_CabinInfo;
import com.yunchengke.app.bean.daemon.ticket.Resp_FlightContainerInfo;
import com.yunchengke.app.bean.daemon.ticket.Resp_FlightInfo;
import com.yunchengke.app.interfaces.Commands;
import com.yunchengke.app.model.FlightResultModel;
import com.yunchengke.app.ui.adapter.daemon.ticket.FlightResultAdapter;
import com.yunchengke.app.utils.daemon.AutoLoadingUtil;
import com.yunchengke.app.utils.daemon.CommonUtil;
import com.yunchengke.app.utils.daemon.SPUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import static com.yunchengke.app.consts.Constants.KEY_CITY_ARRIVE;
import static com.yunchengke.app.consts.Constants.KEY_CITY_LEAVE;
import static com.yunchengke.app.consts.Constants.KEY_DATE_ARRIVE;
import static com.yunchengke.app.consts.Constants.KEY_DATE_LEAVE;
import static com.yunchengke.app.consts.Constants.KEY_PARCELABLE;
import static com.yunchengke.app.consts.Constants.KEY_TITLE;
import static com.yunchengke.app.consts.Constants.KEY_TYPE_CABIN;
import static com.yunchengke.app.consts.Constants.KEY_USERNAME;
import static com.yunchengke.app.consts.Constants.MODEL_FLIGHT_SEARCH;
import static com.yunchengke.app.consts.Constants.VIEW_FLIGHT_SEARCH;
/**
 * 机票航班查询结果
 * @author 邓耀宁
 */
public class FlightResultActivity extends BaseActivity{
	/**
	 * 航班的可展开列表
	 */
    private ExpandableListView elv_flight_result;
    private List<Req_FlightInfo> resqFlightInfos_group;
    private List<List<Req_FlightInfo>> flightInfos_child;
	String Scity;
	String Ecity;
	String cabin;
	String date_leave;
	Req_FlightInfo resqFlightInfo_goAndBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flight_result);
		LinearLayout linearLayout_flight_result = (LinearLayout) findViewById(R.id.linearLayout_flight_result);
		AutoLoadingUtil.setAutoLoadingView(linearLayout_flight_result);
		/**
		 * 自定义的框架
		 */
		setModelDelegate(new FlightResultModel(handler, this));
		setViewChangeListener(this);

		SharedPreferences sp_three_word = SPUtil.getThreeWord(this);//getSharedPreferences(KEY_SP_THREE_WORD, Context.MODE_PRIVATE);
		SharedPreferences sp_cabin = SPUtil.getCabin(this);//getSharedPreferences(KEY_SP_CABIN, Context.MODE_PRIVATE);

		if(getIntent().hasExtra(KEY_PARCELABLE))
			resqFlightInfo_goAndBack = getIntent().getParcelableExtra(KEY_PARCELABLE);

		TextView tv_ticket_result_leaveDate = (TextView)findViewById(R.id.tv_ticket_result_leaveDate);


		   Scity = sp_three_word.getString(getIntent().getStringExtra(KEY_CITY_LEAVE), "");
		    Ecity = sp_three_word.getString(getIntent().getStringExtra(KEY_CITY_ARRIVE), "");
		    cabin = sp_cabin.getString(getIntent().getStringExtra(KEY_TYPE_CABIN), "");
		    date_leave = CommonUtil.getFormatDate(getIntent().getLongExtra(KEY_DATE_LEAVE, System.currentTimeMillis()));
			TextView tv_title = (TextView)findViewById(R.id.tv_title);
			tv_title.setText(String.format(getString(R.string.title_flight_result),
					getIntent().getStringExtra(KEY_CITY_LEAVE),
					getIntent().getStringExtra(KEY_CITY_ARRIVE),
					getIntent().getStringExtra(KEY_TITLE)
			));
			tv_ticket_result_leaveDate.setText(CommonUtil.getFormatDateOnlyYear(getIntent().getLongExtra(KEY_DATE_LEAVE, System.currentTimeMillis())));

		Button btn_title_back = (Button)findViewById(R.id.btn_title_back);
		btn_title_back.setOnClickListener(this);



		/**
		 * 封装请求参数传给model
		 */
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(KEY_CITY_LEAVE, Scity);//三字码
		map.put(KEY_CITY_ARRIVE, Ecity);
		map.put(KEY_USERNAME, "wangjunyi");
		map.put(KEY_DATE_LEAVE, date_leave);//yyyy-mm-dd
		map.put(KEY_TYPE_CABIN, cabin);//字母
		map.put("IsShowSpecial", "F");//是否显示特价
		notifyModelChange(Message.obtain(handler, MODEL_FLIGHT_SEARCH, map));



	}

	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_title_back:
			finish();
			break;

		default:
			break;
		}
	}

	@Override
	public void onViewChange(Message msg) {
		// TODO Auto-generated method stub
		switch (msg.what){
			case VIEW_FLIGHT_SEARCH:
				if(!isDestroyed) {
				AutoLoadingUtil.cancelAutoLoadingView();
				if(msg.obj instanceof  String){
					if(isActive)
                   new AlertDialog.Builder(FlightResultActivity.this)
						   .setTitle("哎呀，出错了！")
						   .setMessage((String)msg.obj)
						   .create()
						   .show();
				}else {

						Resp_FlightContainerInfo container = (Resp_FlightContainerInfo) msg.obj;
						SharedPreferences sp_airLine = SPUtil.getAirLine(this);//getSharedPreferences(KEY_SP_AIR_LINE,Context.MODE_PRIVATE);
						SharedPreferences sp_airPort = SPUtil.getAirPort(this);//getSharedPreferences(KEY_SP_AIR_PORT,Context.MODE_PRIVATE);

						resqFlightInfos_group = new ArrayList<Req_FlightInfo>();
						flightInfos_child = new ArrayList<List<Req_FlightInfo>>();
						for (Resp_FlightInfo reInfo : container.infos) {
							ArrayList<Req_FlightInfo> resqFlightInfos_child_ = new ArrayList<Req_FlightInfo>();
							String[] airTerminal = reInfo.AirTerminal.split(",");
							/**
							 * 筛选过的舱位
							 */
							List<Resp_CabinInfo> respCabinInfo_condition = new ArrayList<Resp_CabinInfo>();
							Comparator comp = new Comparator() {
								public int compare(Object o1, Object o2) {
									Resp_CabinInfo p1 = (Resp_CabinInfo) o1;
									Resp_CabinInfo p2 = (Resp_CabinInfo) o2;
									if (Integer.valueOf(p1.D) < Integer.valueOf(p2.D))
										return -1;
									else if (Integer.valueOf(p1.D) == Integer.valueOf(p2.D))
										return 0;
									else if (Integer.valueOf(p1.D) > Integer.valueOf(p2.D))
										return 1;
									return 0;
								}
							};
							Collections.sort(reInfo.respCabinInfo,comp);
							respCabinInfo_condition.add(reInfo.respCabinInfo.get(0));
							// 判断经济舱是否添加100折扣率的
							boolean Y_flag = false;
							// 判断商务舱是否添加折扣率最接近100的
							boolean C_flag = false;
							for (int i = 1;i<reInfo.respCabinInfo.size();i++) {
								// 头等舱
								if("F".equals(reInfo.respCabinInfo.get(i).L) && i == reInfo.respCabinInfo.size()-1){
									respCabinInfo_condition.add(reInfo.respCabinInfo.get(i));
								}
								// 公务舱
								else if("C".equals(reInfo.respCabinInfo.get(i).L)){
									if(!C_flag && Integer.valueOf(reInfo.respCabinInfo.get(i).D)>100){
										C_flag = true;
										respCabinInfo_condition.add(reInfo.respCabinInfo.get(i));
									}
								}
								// 经济舱
								else if("Y".equals(reInfo.respCabinInfo.get(i).L)){
									if(!Y_flag && Integer.valueOf(reInfo.respCabinInfo.get(i).D)==100){
										Y_flag = true;
										respCabinInfo_condition.add(reInfo.respCabinInfo.get(i));
									}
								}
							}

							Req_FlightInfo info = new Req_FlightInfo();
							info.N = respCabinInfo_condition.get(0).N;
							info.D = CommonUtil.getFormatDiscount(respCabinInfo_condition.get(0).D);
							info.P = respCabinInfo_condition.get(0).P;

							info.ariLinesIcon = getResources().getDrawable(R.drawable.submit_edit_clear_normal);
							info.AirLine = reInfo.AirLine;
							info.Ecity = sp_airPort.getString(reInfo.Ecity, "") + airTerminal[1];
							info.Etime = reInfo.Etime;
							info.Scity = sp_airPort.getString(reInfo.Scity, "") + airTerminal[0];
							info.Stime = reInfo.Stime;
							info.FlightNo = reInfo.FlightNo;
							info.FlightType = reInfo.FlightType;
							info.planeSize = "";



							for (Resp_CabinInfo respCabinInfo : respCabinInfo_condition) {
								Req_FlightInfo childInfo = new Req_FlightInfo();
								childInfo.Sdate = reInfo.Sdate;
								childInfo.D = CommonUtil.getFormatDiscount(respCabinInfo.D);
								childInfo.P = respCabinInfo.P;
								childInfo.cabinType = respCabinInfo.L;
								childInfo.Change = respCabinInfo.Change;
								childInfo.Return = respCabinInfo.Return;
								childInfo.RID = respCabinInfo.RID;
								childInfo.ID = respCabinInfo.ID;
								childInfo.K = respCabinInfo.K;

								childInfo.ariLinesIcon = getResources().getDrawable(R.drawable.submit_edit_clear_normal);
								childInfo.oilPrice = reInfo.Fees;
								childInfo.airPortBuildPrice = reInfo.AirTax;
								childInfo.AirLine = reInfo.AirLine;//sp_airLine.getString(reInfo.AirLine, "");
								childInfo.Ecity = reInfo.Ecity;//sp_airPort.getString(reInfo.Ecity, "") + airTerminal[1];
								childInfo.Etime = reInfo.Etime;
								childInfo.Scity = reInfo.Scity;//sp_airPort.getString(reInfo.Scity, "") + airTerminal[0];
								childInfo.Stime = reInfo.Stime;
								childInfo.FlightNo = reInfo.FlightNo;
								childInfo.AirTerminal = reInfo.AirTerminal;
								resqFlightInfos_child_.add(childInfo);
							}

							resqFlightInfos_group.add(info);
							flightInfos_child.add(resqFlightInfos_child_);
						}


						elv_flight_result = (ExpandableListView) findViewById(R.id.elv_flight_result);
						final FlightResultAdapter adapter = new FlightResultAdapter(this, resqFlightInfos_group, flightInfos_child);
						adapter.setExpandableListView(elv_flight_result);
						elv_flight_result.setAdapter(adapter);

						adapter.setTicketBookCommands(new Commands() {
							@Override
							public void executeCommand(Message msg_params) {
								if (getIntent().hasExtra(KEY_DATE_ARRIVE) && resqFlightInfo_goAndBack == null) {
									/**
									 * 如果是往返，再跳回查询结果界面
									 */
									Intent intent = new Intent(FlightResultActivity.this, FlightResultActivity.class);
									ArrayList<Req_FlightInfo> resqFlightInfos = (ArrayList<Req_FlightInfo>) msg_params.obj;
									intent.putExtra(KEY_PARCELABLE, resqFlightInfos.get(0));

									intent.putExtra(KEY_CITY_LEAVE, getIntent().getStringExtra(KEY_CITY_ARRIVE));
									intent.putExtra(KEY_CITY_ARRIVE, getIntent().getStringExtra(KEY_CITY_LEAVE));
									intent.putExtra(KEY_DATE_LEAVE, getIntent().getLongExtra(KEY_DATE_ARRIVE, System.currentTimeMillis()));
									intent.putExtra(KEY_TYPE_CABIN, getIntent().getStringExtra(KEY_TYPE_CABIN));
									intent.putExtra(KEY_TITLE, getIntent().getStringExtra(KEY_TITLE));

									startActivity(intent);
								} else {
									/**
									 * 如果不是往返，直接跳到订单界面
									 */
									Intent intent = new Intent(FlightResultActivity.this, OrderTicketEditActivity.class);
									ArrayList<Req_FlightInfo> resqFlightInfos = (ArrayList<Req_FlightInfo>) msg_params.obj;
									if (resqFlightInfo_goAndBack != null) {
										resqFlightInfos.add(resqFlightInfo_goAndBack);
										Collections.reverse(resqFlightInfos);
									}
									intent.putExtra(KEY_PARCELABLE, resqFlightInfos);
									startActivity(intent);
									//resqFlightInfo_goAndBack = null;
								}

							}
						});
					}
				}
				break;

		}
	}

}
