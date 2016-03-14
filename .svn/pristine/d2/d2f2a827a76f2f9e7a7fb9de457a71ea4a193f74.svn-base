package com.yunchengke.app.ui.activity.daemon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yunchengke.app.R;
import com.yunchengke.app.bean.LoginInfo;
import com.yunchengke.app.bean.daemon.order.Resp_OrderTicketInfo;
import com.yunchengke.app.bean.daemon.order.Resp_OrderTicketQueryInfo;
import com.yunchengke.app.bean.daemon.ticket.Req_FlightInfo;
import com.yunchengke.app.bean.daemon.ticket.Req_PassengerInfo;
import com.yunchengke.app.interfaces.Commands;
import com.yunchengke.app.interfaces.ListViewListener;
import com.yunchengke.app.model.OrderModel;
import com.yunchengke.app.ui.adapter.daemon.order.OrderInsureAdapter;
import com.yunchengke.app.ui.adapter.daemon.order.OrderPassengerAdapter;
import com.yunchengke.app.ui.adapter.daemon.order.OrderTicketAdapter;
import com.yunchengke.app.utils.daemon.DialogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yunchengke.app.consts.Constants.KEY_CITY;
import static com.yunchengke.app.consts.Constants.KEY_INSURE_NAME;
import static com.yunchengke.app.consts.Constants.KEY_INSURE_PRICE;
import static com.yunchengke.app.consts.Constants.KEY_PARCELABLE;
import static com.yunchengke.app.consts.Constants.KEY_TYPE;
import static com.yunchengke.app.consts.Constants.KEY_TYPE_CABIN_POSITION;
import static com.yunchengke.app.consts.Constants.KEY_TYPE_CERT;
import static com.yunchengke.app.consts.Constants.KEY_TYPE_PASSENGER_CERT_POSITION;
import static com.yunchengke.app.consts.Constants.KEY_TYPE_TICKET_DISTRIBUTE;
import static com.yunchengke.app.consts.Constants.MODEL_ORDER_TICKET_ADD;
import static com.yunchengke.app.consts.Constants.MODEL_ORDER_TICKET_COMMIT;
import static com.yunchengke.app.consts.Constants.MODEL_TICKET_QUERY;
import static com.yunchengke.app.consts.Constants.REQUEST_CODE_CERTIFICATE;
import static com.yunchengke.app.consts.Constants.REQUEST_CODE_CITY;
import static com.yunchengke.app.consts.Constants.REQUEST_CODE_DISTRIBUTE;
import static com.yunchengke.app.consts.Constants.VIEW_ORDER_TICKET_ADD;
import static com.yunchengke.app.consts.Constants.VIEW_ORDER_TICKET_COMMIT;
import static com.yunchengke.app.consts.Constants.VIEW_TICKET_QUERY;

/**
 * 机票订单界面
 * @author 邓耀宁
 *
 */
public class OrderTicketEditActivity extends BaseActivity{
	/**
	 * 增加乘机人
	 */
	private Button btn_order_morePassenger;
	/**
	 * 配送方式按钮
	 */
	private Button btn_order_destribute;
	/**
	 * 城市选择
	 */
	private Button btn_order_city; 
	/**
	 * 保存各个乘机人的证件类型位置
	 */
	private SparseIntArray certType_positions;
	/**
	 * 记录配送方式位置
	 */
	private int position_destribute = 0;
	/**
	 * 乘机人适配器
	 */
	private OrderPassengerAdapter passengerAdapter;
	/**
	 * 如果选择快递配送。则显示此界面
	 */
	private LinearLayout linearLayout_order_destribute;
	/**
	 * 乘机人列表
	 */
	private ListView lv_order_passengerInfo;
	/**
	 * 空险列表
	 */
	private ListView lv_order_insure;
	/**
	 * 乘机人信息链表
	 */
	private ArrayList<Req_PassengerInfo> passenger_infos;
	
	private TextView tv_order_total;
	/**
	 * 每个乘机人的单价总和
	 */
	private int ticket_unit_price = 0;
	private int insure_price = 40;

	private ArrayList<Req_FlightInfo> resqFlightInfos;
	private ArrayList<Integer> indexs ;

	private Resp_OrderTicketInfo resp_orderTicketInfo;

	private ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_ticket_edit);
		/**
		 * 自定义的框架
		 */
		setModelDelegate(new OrderModel(handler, this));
		setViewChangeListener(this);

		/**
		 * --------------------------------空险列表start---------------------------------
		 */
		lv_order_insure = (ListView)findViewById(R.id.lv_order_insure);
		String[] insures = getResources().getStringArray(R.array.TypeInsure);
		List<Map<String, String>> data = new ArrayList<Map<String,String>>();
		for (String insure : insures) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(KEY_INSURE_NAME, insure);
			map.put(KEY_INSURE_PRICE, String.valueOf(insure_price));
			data.add(map);
		}
		final OrderInsureAdapter insureAdapter = new OrderInsureAdapter(this, data);
		lv_order_insure.setAdapter(insureAdapter);
		lv_order_insure.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(insureAdapter.getPosition() == position){
					insureAdapter.setPosition(-1);
					insure_price = 0;
				}else{
					insureAdapter.setPosition(position);
					insure_price = 40;
				}
				int count = passenger_infos.size();
				tv_order_total = (TextView) findViewById(R.id.tv_order_total);
				tv_order_total.setText(String.format(
						getString(R.string.order_peopleAndPrice),
						String.valueOf(passenger_infos.size()),
						String.valueOf((ticket_unit_price+insure_price) * count)));

				insureAdapter.notifyDataSetChanged();
			}
		});
		/**
		 * --------------------------------空险列表end---------------------------------
		 */
		
		/**
		 * --------------------------------乘机人列表start---------------------------------
		 */
		passenger_infos = new ArrayList<Req_PassengerInfo>();
		indexs = new ArrayList<Integer>();
		for (int i = 0; i < 1; i++) {
			Req_PassengerInfo info = new Req_PassengerInfo();
			info.certNum="";
			info.certType="身份证";
			info.name="";
			passenger_infos.add(info);
			indexs.add(-1);
		}		
		lv_order_passengerInfo = (ListView)findViewById(R.id.lv_order_passengerInfo);
		passengerAdapter = new OrderPassengerAdapter(this, passenger_infos, certType_positions,indexs);
		lv_order_passengerInfo.setAdapter(passengerAdapter);
		passengerAdapter.setSizeChangeCommand(new Commands() {
			@Override
			public void executeCommand(Message msg_params) {
				int count = passenger_infos.size();
				tv_order_total = (TextView) findViewById(R.id.tv_order_total);
				tv_order_total.setText(String.format(
						getString(R.string.order_peopleAndPrice),
						String.valueOf(passenger_infos.size()),
						String.valueOf((ticket_unit_price+insure_price) * count)));
			}
		});
		lv_order_insure.requestFocus();
		passengerAdapter.setListViewListener(new ListViewListener() {
			@Override
			public void onItemDelete() {
				final ScrollView sv_order = (ScrollView) findViewById(R.id.sv_order);
				sv_order.post(new Runnable() {
					@Override
					public void run() {
						sv_order.smoothScrollBy(0, -lv_order_passengerInfo.getMeasuredHeight()/lv_order_passengerInfo.getCount());
					}
				});
			}
		});
		/**
		 * --------------------------------乘机人列表end---------------------------------
		 */
		
		/**
		 * --------------------------------航班信息列表start---------------------------------
		 */
		ListView lv_order_ticketInfo = (ListView)findViewById(R.id.lv_order_ticketInfo);
		resqFlightInfos = getIntent().getParcelableArrayListExtra(KEY_PARCELABLE);
		for (Req_FlightInfo info: resqFlightInfos) {
			ticket_unit_price +=Integer.valueOf(info.P)+Integer.valueOf(info.airPortBuildPrice)+Integer.valueOf(info.oilPrice);
		}
		OrderTicketAdapter orderTicketAdapter = new OrderTicketAdapter(this, resqFlightInfos);
		lv_order_ticketInfo.setAdapter(orderTicketAdapter);
		/**
		 * --------------------------------航班信息列表end---------------------------------
		 */

		btn_order_morePassenger = (Button) findViewById(R.id.btn_order_morePassenger);
		btn_order_morePassenger.setOnClickListener(this);
		
		btn_order_destribute = (Button) findViewById(R.id.btn_order_destribute);
		btn_order_destribute.setOnClickListener(this);
		
		linearLayout_order_destribute = (LinearLayout)findViewById(R.id.linearLayout_order_destribute);
		linearLayout_order_destribute.setVisibility(View.GONE);
		
		Button btn_order_commit = (Button) findViewById(R.id.btn_order_commit);
		btn_order_commit.setOnClickListener(this);
		
		TextView tv_title = (TextView)findViewById(R.id.tv_title);
		tv_title.setText(getString(R.string.title_order_edit));
		
		Button btn_back = (Button)findViewById(R.id.btn_title_back);
		btn_back.setOnClickListener(this);
		
		tv_order_total = (TextView)findViewById(R.id.tv_order_total);
		tv_order_total.setText(String.format(
				getString(R.string.order_peopleAndPrice),
				String.valueOf(passenger_infos.size()),
				String.valueOf(ticket_unit_price+insure_price)));

		handler.post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				/**
				 * 使scrollView向顶部滑动
				 */
				ScrollView sv_order = (ScrollView) findViewById(R.id.sv_order);
				sv_order.fullScroll(ScrollView.FOCUS_UP);
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		switch (v.getId()) {
		/**
		 * 增加乘客人
		 */
		case R.id.btn_order_morePassenger:
			indexs.add(-1);
			passenger_infos.add(new Req_PassengerInfo());
			passengerAdapter.notifyDataSetChanged();
			lv_order_passengerInfo.clearFocus();
			final ScrollView sv_order = (ScrollView) findViewById(R.id.sv_order);
			sv_order.post(new Runnable() {
				@Override
				public void run() {
					sv_order.smoothScrollBy(0, lv_order_passengerInfo.getMeasuredHeight()/lv_order_passengerInfo.getCount());
				}
			});
			break;
		/**	
		  * 返回
		  */
		case R.id.btn_title_back:
			DialogUtil.showDialog(OrderTicketEditActivity.this, getString(R.string.title_order_edit), getString(R.string.tips_exitOrder), new Commands() {

				@Override
				public void executeCommand(Message msg_params) {
					// TODO Auto-generated method stub
					finish();
				}
			});
			break;
		/**	
		 * 选择配送方式
		 */
		case R.id.btn_order_destribute:
			intent = new Intent(OrderTicketEditActivity.this, TypeSelectActivity.class);
			intent.putExtra(KEY_TYPE, KEY_TYPE_TICKET_DISTRIBUTE);
			intent.putExtra(KEY_TYPE_CABIN_POSITION, position_destribute);
			startActivityForResult(intent,REQUEST_CODE_DISTRIBUTE);
			overridePendingTransition(R.anim.activity_up, 0);
			break;
		/**
		 * 如有配送方式，选择城市
		 */
		case R.id.btn_order_city:
			intent = new Intent();
			intent.setClass(OrderTicketEditActivity.this, CitySearchActivity.class);
			startActivityForResult(intent, REQUEST_CODE_CITY);
			break;
		/**
		 * 提交订单	
		 */
		case R.id.btn_order_commit:
            if(resqFlightInfos.size()==1){
				String Name = "",IDCard = "";
				/**
				 * 获取每个editText
				 */
				for (int i = 0; i < lv_order_passengerInfo.getChildCount(); i++) {
					LinearLayout layout = (LinearLayout)lv_order_passengerInfo.getChildAt(i);// 获得子item的layout
					EditText et_order_passengers = (EditText) layout.findViewById(R.id.et_order_passengers);// 从layout中获得控件,根据其id
					final  EditText et_order_certNum = (EditText) layout.findViewById(R.id.et_order_certNum);//或者根据位置,在这我假设TextView在前，EditText在后
					TextView tv_order_certType = (TextView) layout.findViewById(R.id.tv_order_certType);
					Name += et_order_passengers.getText().toString()+"|";
					IDCard += "NI"+et_order_certNum.getText().toString()+"|";
					Log.e(getTAG(), "name="+Name+
							",cert_num ="+IDCard+
							",certType="+tv_order_certType.getText());
				}
				HashMap<String,String> params_map = new HashMap<String,String>();
				params_map.put("RateId", resqFlightInfos.get(0).ID);
				if(!TextUtils.isEmpty(resqFlightInfos.get(0).RID))params_map.put("PolicyId", resqFlightInfos.get(0).RID);
				params_map.put("Name",Name);
				params_map.put("UserName","wang87654321");
				params_map.put("IDCard",IDCard);
				params_map.put("Cabins", resqFlightInfos.get(0).cabinType);
				params_map.put("dotNum", resqFlightInfos.get(0).K);
				params_map.put("sCity", resqFlightInfos.get(0).Scity);
				params_map.put("eCity", resqFlightInfos.get(0).Ecity);
				params_map.put("sDate", resqFlightInfos.get(0).Sdate);
				params_map.put("AirChangedContact",((EditText)findViewById(R.id.et_order_phoneNum)).getText().toString());
				params_map.put("AutoPay", "F");
				params_map.put("Airline", resqFlightInfos.get(0).FlightNo);

				Log.e(getTAG(), ",ID=" + resqFlightInfos.get(0).ID + ",RID=" +
						resqFlightInfos.get(0).RID + ",cabinType=" +
						resqFlightInfos.get(0).cabinType + ",K=" +
						resqFlightInfos.get(0).K + ",Scity=" +
						resqFlightInfos.get(0).Scity + ",Ecity=" +
						resqFlightInfos.get(0).Ecity + ",Sdate=" +
						resqFlightInfos.get(0).Sdate + ",AirLine=" +
						resqFlightInfos.get(0).FlightNo);
				notifyModelChange(Message.obtain(handler, MODEL_ORDER_TICKET_COMMIT, params_map));
				String ss = params_map.toString();
				dialog = new ProgressDialog(OrderTicketEditActivity.this);
				dialog.setMessage("正在创建订单，请稍等...");
				dialog.setCancelable(false);
				dialog.show();
			}else{
				DialogUtil.showDialog(OrderTicketEditActivity.this, getString(R.string.title_order_edit), "暂时不支持往返创建订单！", new Commands() {
					@Override
					public void executeCommand(Message msg_params) {

					}
				});
			}

			break;
		
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
            DialogUtil.showDialog(OrderTicketEditActivity.this, getString(R.string.title_order_edit), getString(R.string.tips_exitOrder), new Commands() {
				
				@Override
				public void executeCommand(Message msg_params) {
					// TODO Auto-generated method stub
					finish();
				}
			});
			break;

		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			switch (requestCode) {
			/**
			 * 如果是证书请求码
			 */
			case REQUEST_CODE_CERTIFICATE:
				int type_position = data.getIntExtra(KEY_TYPE_CABIN_POSITION, 0);
				int view_position = data.getIntExtra(KEY_TYPE_PASSENGER_CERT_POSITION, 0);
				String certType = data.getStringExtra(KEY_TYPE_CERT);
				passenger_infos.get(view_position).certType = certType;
				passenger_infos.get(view_position).cert_position = type_position;

				//lv_order_insure.requestFocus();
				passengerAdapter.notifyDataSetChanged();
				break;
			/**
			  * 如果是配送请求码
			  */	
			case REQUEST_CODE_DISTRIBUTE:
				position_destribute = data.getIntExtra(KEY_TYPE_CABIN_POSITION, 0);
				if(position_destribute == 0){
					linearLayout_order_destribute.setVisibility(View.GONE);
				}else{
					linearLayout_order_destribute.setVisibility(View.VISIBLE);
					handler.post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							/**
							 * 使scrollView向底部滑动
							 */
							ScrollView sv_order = (ScrollView)findViewById(R.id.sv_order);
							sv_order.fullScroll(ScrollView.FOCUS_DOWN);
						}
					});
				}
				btn_order_destribute.setText(data.getStringExtra(KEY_TYPE_TICKET_DISTRIBUTE));
				
				btn_order_city = (Button) findViewById(R.id.btn_order_city);
				btn_order_city.setOnClickListener(this);
				break;
				/**
				 * 如果是城市请求码
				 */
			case REQUEST_CODE_CITY:
				btn_order_city.setTextColor(getResources().getColor(R.color.ticket_black));
				btn_order_city.setText(data.getStringExtra(KEY_CITY));
				break;
				
			default:
				break;
			}
			
		}
	}
	
	@Override
	public void onViewChange(Message msg) {
		// TODO Auto-generated method stub
		switch (msg.what){
			case VIEW_ORDER_TICKET_COMMIT:
                if(msg.obj instanceof String){
					if(dialog.isShowing())dialog.dismiss();
					DialogUtil.showDialog(OrderTicketEditActivity.this, getString(R.string.title_order_edit), (String)msg.obj, new Commands() {
						@Override
						public void executeCommand(Message msg_params) {

						}
					});
				}else{
					/**
					 * 将订单号添加到后台数据库
					 */
					resp_orderTicketInfo = (Resp_OrderTicketInfo)msg.obj;
					HashMap<String,String> params_map = new HashMap<String,String>();
					params_map.put("OrderId", resp_orderTicketInfo.OrderNo);
					params_map.put("UId", "yesicity2015");
					params_map.put("Field_YHID", LoginInfo.getInstance().getId());
					params_map.put("Yesicity","1");
					notifyModelChange(Message.obtain(handler,MODEL_ORDER_TICKET_ADD,params_map));
				}
				break;

			case VIEW_ORDER_TICKET_ADD:
						if(!"0".equals((String) msg.obj)){
							if(dialog.isShowing())dialog.dismiss();
							String message = (String)msg.obj;
							DialogUtil.showDialog(OrderTicketEditActivity.this, "提示", message, new Commands() {
								@Override
								public void executeCommand(Message msg_params) {

								}
							});
						}else{
							HashMap<String,String> params_map = new HashMap<String,String>();
							params_map.put("orderno", resp_orderTicketInfo.OrderNo);
							params_map.put("UserName","wang87654321");
							notifyModelChange(Message.obtain(handler,MODEL_TICKET_QUERY,params_map));
						}
				break;

			case VIEW_TICKET_QUERY:
				if(msg.obj instanceof String){
					if(dialog.isShowing())dialog.dismiss();
					DialogUtil.showDialog(OrderTicketEditActivity.this, getString(R.string.title_order_edit), (String)msg.obj, new Commands() {
						@Override
						public void executeCommand(Message msg_params) {

						}
					});
				}else{
					if(dialog.isShowing())dialog.dismiss();
					Resp_OrderTicketQueryInfo info = (Resp_OrderTicketQueryInfo)msg.obj;
					Intent intent = new Intent(OrderTicketEditActivity.this,OrderTicketDetailActivity.class);
					intent.putExtra(KEY_PARCELABLE,info);
					startActivity(intent);
					finish();
				}
				break;
		}
	}
	

}
