package com.yunchengke.app.pay.icbcpay;

import android.app.Activity;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.yunchengke.app.utils.ToastUtils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ICBCPay {

	static Date date=new Date();
	static long Timess=new Date().getTime();  //获取时间戳

	public static  String  timeul= Timess+"";//转换时间戳
	public static  String Times =timeul.substring(0, 10);//截取前十位

	//static SimpleDateFormat wtime = new SimpleDateFormat("yyyyMMddHHmmss");
	//static SimpleDateFormat wtimes = new SimpleDateFormat("yyyyMMdd");
	//public static  String windtime=wtime.format(new Date());

	public static  String PackageIDs= "FCK123" + Times;//指令编码
	//public static  String SendTimes = windtime;//发送时间
	public static  String CISs="390190001501002";//集团ID
	public static  String BankCodes="102";//银行编码
	public static  String IDs="tykj.y.3901";//ID
	//public static  String TranDates=wtimes.format(new Date());//日期
	public static String TranTimes=""+Times;//时间戳
	public static String fSeqnos=PackageIDs;

	//服务器地址
	//public static String urls="http://122.227.164.126:448/servlet/ICBCCMPAPIReqServlet?userID=tykj.y.3901&PackageID="+PackageIDs+"&SendTime="+SendTimes;

	//服务器地址
	public static String urls = "http://122.227.164.126:8989/";
	/*
	 * 下面这块很重要实现功能的必须！XML银行的提供使用
	 * -----------------------------------------------------
	 */
	public static String QrySerialNo="";//要查询的交易数据
	public static String QryfSeqno="";//要查询的交易数据
	//XML DATA
	public static String XML_in_Apply_data =
			"<SignTime>"+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date)+"</SignTime>" +
			"<PayChannel>1</PayChannel>" +
			"<SubmitTime>"+new SimpleDateFormat("yyyyMMddHHmmss").format(date)+"</SubmitTime>" +
			"<OrderNo>"+PackageIDs+"</OrderNo>" +
			"<OrderAmt>10</OrderAmt>" +
			"<PayStage>1</PayStage>" +
			"<PayStageFlag>0</PayStageFlag>" +
			"<ShopAcctNo>3901140019200086326</ShopAcctNo>" +
			"<GoodsNo>1001</GoodsNo>" +
			"<GoodsName>12222</GoodsName>" +
			"<GoodsNumber>1</GoodsNumber>" +
			"<TransferAmt>0</TransferAmt>" +
			"<JUnionFlag>1</JUnionFlag>" +
			"<Language1>ZH_CN</Language1>" +
			"<CurrType>001</CurrType>" +
			"<ShopCode>3901EC24606822</ShopCode>" +
			"<CardFlag>2</CardFlag>" +
			"<NotifyType>HS</NotifyType>" +
			"<RSendType>0</RSendType>" +
			"<GoodsType>1</GoodsType>" +
			"<Phone>18606861666</Phone>" +
			"<CusAcctNo>228988</CusAcctNo>" +
			"<CusAlias>bieming</CusAlias>" +
			"<ThirdFlag>0</ThirdFlag>" +
			"<AgentShopCode></AgentShopCode>" +
			"<AgentShopName></AgentShopName>" +
			"<AgentOrderFlag></AgentOrderFlag>" +
			"<BuyerID>kevin</BuyerID>" +
			"<Recer>kevin</Recer>" +
			"<RecAddr>777777</RecAddr>" +
			"<RecPhone>18606861666</RecPhone>";



	//public static String QEPAYSUB="<?xml version='1.0' encoding='GBK'?><CMS><eb><pub><TransCode>QEPAYSUB</TransCode><CIS>"+CISs+"</CIS><BankCode>"+BankCodes+"</BankCode><ID>"+IDs+"</ID><TranDate>"+TranDates+"</TranDate><TranTime>"+TranTimes+"</TranTime><fSeqno>"+fSeqnos+"</fSeqno></pub><in><QrySerialNo>"+QrySerialNo+"</QrySerialNo><QryfSeqno>"+QryfSeqno+"</QryfSeqno></in></eb></CMS>";
	//public static String QEPAYSUB="<?xml version='1.0' encoding='GBK'?><CMS><eb><pub><TransCode>EPAYAPPLY</TransCode><CIS>"+CISs+"</CIS><BankCode>"+BankCodes+"</BankCode><ID>"+IDs+"</ID><TranDate>"+TranDates+"</TranDate><TranTime>"+TranTimes+"</TranTime><fSeqno>"+fSeqnos+"</fSeqno></pub>"+ XML_in_Apply_data +"</eb></CMS>";
	/*
	 * -------------------------------------------------------
	 */

	//发送数据组合
	//public static String postdata="Version=0.0.1.0&TransCode=EPAYAPPLY&BankCode=102&GroupCIS="+CISs+"&ID="+IDs+"&PackageID="+PackageIDs+"&Cert=&reqData="+QEPAYSUB;
	//public static String postdata="PackageIDs="+PackageIDs+"&Times="+timeul.substring(0, 11)+"&CMD=EPAYAPPLY&XML_in="+Base64.encodeToString(XML_in_Apply_data.getBytes(), Base64.DEFAULT)+"&keys=asdasdKJHSAKBasdbjzxNAHG23HJBDBJXC";

	private static String createPackageIDs(){
		long time=new Date().getTime();
		String PackageIDs= "FCK123" + String.valueOf(time).substring(0, 11);
		return PackageIDs;
	}

	private static String createXML_in_Apply_data(String PackageIDs,String phone,String payCardId,String price){
		Date date=new Date();
		String XML_in_Apply_data =
				"<SignTime>"+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date)+"</SignTime>" +
						"<PayChannel>1</PayChannel>" +
						"<SubmitTime>"+new SimpleDateFormat("yyyyMMddHHmmss").format(date)+"</SubmitTime>" +
						"<OrderNo>"+PackageIDs+"</OrderNo>" +
						"<OrderAmt>"+price+"</OrderAmt>" +
						"<PayStage>1</PayStage>" +
						"<PayStageFlag>0</PayStageFlag>" +
						"<ShopAcctNo>3901140019200086326</ShopAcctNo>" +
						"<GoodsNo>10001</GoodsNo>" +
						"<GoodsName>12222</GoodsName>" +
						"<GoodsNumber>1</GoodsNumber>" +
						"<TransferAmt>0</TransferAmt>" +
						"<JUnionFlag>0</JUnionFlag>" +
						"<Language1>ZH_CN</Language1>" +
						"<CurrType>001</CurrType>" +
						"<ShopCode>3901EC24606822</ShopCode>" +
						"<CardFlag>0</CardFlag>" +
						"<NotifyType>HS</NotifyType>" +
						"<RSendType>0</RSendType>" +
						"<GoodsType>1</GoodsType>" +
						"<Phone>"+phone+"</Phone>" +
						"<CusAcctNo>"+payCardId+"</CusAcctNo>" +
						"<CusAlias>biemin</CusAlias>" +
						"<ThirdFlag>0</ThirdFlag>" +
						"<AgentShopCode></AgentShopCode>" +
						"<AgentShopName></AgentShopName>" +
						"<AgentOrderFlag></AgentOrderFlag>" +
						"<BuyerID>kevin</BuyerID>" +
						"<Recer>kevin</Recer>" +
						"<RecAddr>777777</RecAddr>" +
						"<RecPhone>"+phone+"</RecPhone>";
		return XML_in_Apply_data;
	}

	/**
	 * 进行支付申请
	 * @param context
	 */
	public static void startPayApply(final Activity context, final String phone, final String payCardId,final  String price){
		final String PackageIDs = createPackageIDs();

		//发送 POST 请求
		RequestQueue requestQueue = Volley.newRequestQueue(context);

		StringRequest stringRequest = new StringRequest(Request.Method.POST,urls,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Log.e("startPayApply.onResponse", "response -> " + response);
						System.out.println("-------------------------------------------------------------------");
						System.out.println("请求的连接：" + urls);

                        String result = response.substring(response.indexOf("<RetMsg>")+"<RetMsg>".length(),response.indexOf("</RetMsg>"));
						Log.e("startPayApply.result", "result -> " + result);
						if(result.equals("成功")){
							ToastUtils.show(context, "发送成功");
						}else{
							ToastUtils.show(context, result);
						};
						 SignTime = response.substring(response.indexOf("<SignTime>")+"<SignTime>".length(),response.indexOf("</SignTime>"));
						 PaySerialNo = response.substring(response.indexOf("<PaySerialNo>")+"<PaySerialNo>".length(),response.indexOf("</PaySerialNo>"));
						 SSTime = response.substring(response.indexOf("<SubmitTime>")+"<SubmitTime>".length(),response.indexOf("</SubmitTime>"));
						 OrderNo = response.substring(response.indexOf("<OrderNo>")+"<OrderNo>".length(),response.indexOf("</OrderNo>"));
						 ShopCode = response.substring(response.indexOf("<ShopCode>")+"<ShopCode>".length(),response.indexOf("</ShopCode>"));
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("startPayApply.onErrorResponse", error.getMessage(), error);
				ToastUtils.show(context, "发送失败");
			}
		}) {
			@Override
			protected Response<String> parseNetworkResponse(NetworkResponse response) {
				try {
					String string = new String(response.data, "GBK");
					return Response.success(string,
							HttpHeaderParser.parseCacheHeaders(response));
				} catch (UnsupportedEncodingException e) {
					return Response.error(new ParseError(e));
				} catch (Exception e) {
					return Response.error(new ParseError(e));
				}
			}

			@Override
			protected Map<String, String> getParams() {
				//在这里设置需要post的参数
				Map<String, String> params = new HashMap<String, String>();
				params.put("accept", "*/*");
				params.put("connection", "Keep-Alive");
				params.put("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
				params.put("PackageIDs", PackageIDs);
				params.put("Times", String.valueOf(new Date().getTime()).substring(0, 11));
				params.put("CMD", "EPAYAPPLY");
				params.put("XML_in", Base64.encodeToString(createXML_in_Apply_data(PackageIDs,phone,payCardId,price).getBytes(), Base64.DEFAULT));
				params.put("keys", "asdasdKJHSAKBasdbjzxNAHG23HJBDBJXC");
				return params;
			}
		};
//		stringRequest.setRetryPolicy(new DefaultRetryPolicy( 500000,//默认超时时间，应设置一个稍微大点儿的，例如本处的500000
//				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,//默认最大尝试次数
//				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT ));
		requestQueue.add(stringRequest);
	}

	/**
	 * 进行支付提交
	 * @param context
	 */
	public static void startPayPost(final Activity context,String password){
		final String mPackageIDs = createPackageIDs();
		 final String XML_in_Pay_data =
				"<SignTime>"+SignTime+"</SignTime>" +
				"<PaySerialNo>"+PaySerialNo+"</PaySerialNo>" +
				"<SSTime>"+SSTime+"</SSTime>" +
				"<OrderNo>"+OrderNo+"</OrderNo>"+
				"<ShopCode>"+ShopCode+"</ShopCode>" +
				"<Password>"+password+"</Password>";

		System.out.println("SignTimejj：" + SignTime);
		System.out.println("PaySerialNjjo：" + PaySerialNo);

		Log.e("startPayPost","XML_in_Pay_data="+XML_in_Pay_data);
		//发送 POST 请求
		RequestQueue requestQueue = Volley.newRequestQueue(context);
		StringRequest stringRequest = new StringRequest(Request.Method.POST,urls,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Log.d("dddddd", "response -> " + response);
						System.out.println("-------------------------------------------------------------------");
						System.out.println("请求的连接：" + urls);
						String result = response.substring(response.indexOf("<RetMsg>")+"<RetMsg>".length(),response.indexOf("</RetMsg>"));
						Log.e("startPayApply.result", "result -> " + result);
						if(result.equals("成功")){
							ToastUtils.show(context, "支付成功");
							//context.startActivity(new Intent(context,MyConsumeActivity.class));
							Intent data = new Intent();
							data.putExtra("pay_result","success");
							context.setResult(context.RESULT_OK, data);
							context.finish();
						}else{
							ToastUtils.show(context, result);
//							Intent data = new Intent();
//							data.putExtra("pay_result","fail");
//							context.setResult(context.RESULT_OK, data);
//							context.finish();
						};


					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("ssssss", error.getMessage(), error);
			}
		}) {
			@Override
			protected Response<String> parseNetworkResponse(NetworkResponse response) {
				try {
					String string = new String(response.data, "GBK");
					return Response.success(string,
							HttpHeaderParser.parseCacheHeaders(response));
				} catch (UnsupportedEncodingException e) {
					return Response.error(new ParseError(e));
				} catch (Exception e) {
					return Response.error(new ParseError(e));
				}
			}
			@Override
			protected Map<String, String> getParams() {
				//在这里设置需要post的参数
				Map<String, String> params = new HashMap<String, String>();
				params.put("accept", "*/*");
				params.put("connection", "Keep-Alive");
				params.put("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
				params.put("PackageIDs", mPackageIDs);
				params.put("Times", String.valueOf(new Date().getTime()).substring(0, 11));
				params.put("CMD", "EPAYSUB");
				params.put("XML_in", Base64.encodeToString(XML_in_Pay_data.getBytes(), Base64.DEFAULT));
				params.put("keys", "asdasdKJHSAKBasdbjzxNAHG23HJBDBJXC");
				return params;
			}
		};
		requestQueue.add(stringRequest);
	}

	// 将 BASE64 编码的字符串 s 进行解码 
//	public static String getFromBASE64(String s)
//	{
//
//		String s1 = s.replaceFirst("reqData=","");//去除reqData=
//
//		if (s == null)
//			return null;
//		//BASE64Decoder decoder = new BASE64Decoder();
//
//		try
//		{
//			//byte[] b = decoder.decodeBuffer(s1);
//			byte[] b = Base64.decode(s1,Base64.DEFAULT);
//			return new String(b,"GBK");
//		} catch (Exception e)
//		{
//			return null;
//		}
//	}

	static String SignTime ;
	static String PaySerialNo ;
	static String SSTime;
	static String OrderNo ;
	static String ShopCode;



}


