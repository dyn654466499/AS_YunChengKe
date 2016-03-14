package com.yunchengke.app.extern.wheelview;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

import com.yunchengke.app.R;
import com.yunchengke.app.extern.wheelview.adapter.NumericWheelAdapter;


public class TimePop implements OnClickListener {

	private Dialog pop;
	private OnClickListener mOkListener;
	private OnClickListener mCancelListener;
	private Context mContext;
	private int mYear=1996;
	
	
	private WheelView year;
	private WheelView month;
	private WheelView day;
	private WheelView min;
	private WheelView sec;
	private II mII;
	private int norYear;
	public TimePop(Context context, final OnClickListener okListener,
			final OnClickListener cancelListener,II  time0,String text) {
		mContext = context;
		mII = time0;
		mOkListener = okListener;
		mCancelListener = cancelListener;
		pop = new Dialog(context, R.style.dialog);
		long millies;
		if(!"".equals(text)){
			millies = getDataTime(text);
		}else {
			millies = System.currentTimeMillis();
		}
		Window window = pop.getWindow();  
		window.getDecorView().setPadding(0, 0, 0, 0);
	        WindowManager.LayoutParams lp = window.getAttributes();
	        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
	        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
	        window.setAttributes(lp);
	        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置  
	        window.setWindowAnimations(R.style.pop);  //添加动画  
		Calendar c = Calendar.getInstance();
		norYear = c.get(Calendar.YEAR);
		c.setTimeInMillis(millies);
		mYear = c.get(Calendar.YEAR) - norYear;
		int curMonth = c.get(Calendar.MONTH) + 1;//通过Calendar算出的月数要+1
		int curDate = c.get(Calendar.DATE);
		int curYear = mYear;
		int curMin = c.get(Calendar.HOUR_OF_DAY);
		int curS = c.get(Calendar.MINUTE);
		
		View view = LayoutInflater.from(context).inflate(R.layout.extra_pop_time_picker,
				null);

		year = (WheelView) view.findViewById(R.id.year);
		NumericWheelAdapter numericWheelAdapter1=new NumericWheelAdapter(context,norYear, norYear+10); 
		numericWheelAdapter1.setLabel("年");
		year.setCurrentItem(0);
		year.setViewAdapter(numericWheelAdapter1);
		year.setCyclic(true);//是否可循环滑动
		year.addScrollingListener(scrollListener);
		
		month = (WheelView) view.findViewById(R.id.month);
		NumericWheelAdapter numericWheelAdapter2=new NumericWheelAdapter(context,1, 12, "%02d"); 
		numericWheelAdapter2.setLabel("月");
		month.setViewAdapter(numericWheelAdapter2);
		month.setCyclic(true);
		month.addScrollingListener(scrollListener);
		
		day = (WheelView) view.findViewById(R.id.day);
		initDay(c.get(Calendar.YEAR),curMonth);
		day.setCyclic(true);
		day.addScrollingListener(scrollListener);
		
		min = (WheelView) view.findViewById(R.id.min);
		NumericWheelAdapter numericWheelAdapter3=new NumericWheelAdapter(context,0, 23, "%02d");
		numericWheelAdapter3.setLabel("时");
		min.setViewAdapter(numericWheelAdapter3);
		min.setCyclic(true);
		min.addScrollingListener(scrollListener);
		
		sec = (WheelView) view.findViewById(R.id.sec);
		NumericWheelAdapter numericWheelAdapter4=new NumericWheelAdapter(context,0, 59, "%02d"); 
		numericWheelAdapter4.setLabel("分");
		sec.setViewAdapter(numericWheelAdapter4);
		sec.setCyclic(true);
		sec.addScrollingListener(scrollListener);
		
		
		year.setVisibleItems(7);//设置显示行数
		month.setVisibleItems(7);
		day.setVisibleItems(7);
		min.setVisibleItems(7);
		sec.setVisibleItems(7);
		
		year.setCurrentItem(curYear);
		month.setCurrentItem(curMonth-1);
		day.setCurrentItem(curDate-1);
		min.setCurrentItem(curMin);
		sec.setCurrentItem(curS);
		pop.setContentView(view);
	}
	
	OnWheelScrollListener scrollListener = new OnWheelScrollListener() {
		@Override
		public void onScrollingStarted(WheelView wheel) {

		}

		@Override
		public void onScrollingFinished(WheelView wheel) {
			int n_year = year.getCurrentItem() + norYear;//年
			int n_month = month.getCurrentItem();//月
			initDay(n_year,n_month);
			int n_day = day.getCurrentItem()+1;
			int n_min = min.getCurrentItem();
			int n_sec = sec.getCurrentItem();
		
			Calendar c = Calendar.getInstance();
			c.set(n_year, n_month, n_day, n_min, n_sec,0);
			mII.updateData(c.getTimeInMillis());
		}
	};

	/**
	 */
	private void initDay(int arg1, int arg2) {
		NumericWheelAdapter numericWheelAdapter=new NumericWheelAdapter(mContext,1, getDay(arg1, arg2), "%02d");
		numericWheelAdapter.setLabel("日");
		day.setViewAdapter(numericWheelAdapter);
	}

	/**
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	private int getDay(int year, int month) {
		int day = 30;
		boolean flag = false;
		switch (year % 4) {
		case 0:
			flag = true;
			break;
		default:
			flag = false;
			break;
		}
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day = 31;
			break;
		case 2:
			day = flag ? 29 : 28;
			break;
		default:
			day = 30;
			break;
		}
		return day;
	}

	
	public static String getDateTime(long time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		String dateTime = sdf.format(time);
		return dateTime;
	}

	
	private long getDataTime(String text){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		try {
			Date time = sdf.parse(text);
			return time.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0L;
	}
	public void show() {
		if (pop != null) {
			pop.show();
		}
	}

	public void di() {
		if (pop != null) {
			pop.dismiss();
		}
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}
	
}
