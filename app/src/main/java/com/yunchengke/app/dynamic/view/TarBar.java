package com.yunchengke.app.dynamic.view;

import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunchengke.app.R;

public class TarBar {
	private TextView top_tab_text1;
	private TextView top_tab_text2;
	private TextView top_tab_text3;
	private TextView top_tab_text4;
	private Window window;
	private ImageView imageView;
	private TextView top_tab_number1;
	private TextView top_tab_number2;
	private TextView top_tab_number3;
	private TextView top_tab_number4;
	private RelativeLayout top_tab_rl1;
	private RelativeLayout top_tab_rl2;
	private RelativeLayout top_tab_rl3;
	private RelativeLayout top_tab_rl4;

	public TarBar(Window window) {
		this.window = window;
	}

	/**
	 * 设置头部标签
	 * 
	 * @param tabBarNames
	 */
	public void setTabButton(String tabBarNames[]) {
		if (tabBarNames != null && tabBarNames.length == 2) {
			getTopTabRl1().setVisibility(View.VISIBLE);
			getTopTabText1().setText(tabBarNames[0]);
			getTopTabRl2().setVisibility(View.VISIBLE);
			getTopTabText2().setText(tabBarNames[1]);
		} else if (tabBarNames != null && tabBarNames.length == 3) {
			getTopTabRl1().setVisibility(View.VISIBLE);
			getTopTabText1().setText(tabBarNames[0]);
			getTopTabRl2().setVisibility(View.VISIBLE);
			getTopTabText2().setText(tabBarNames[1]);
			getTopTabRl3().setVisibility(View.VISIBLE);
			getTopTabText3().setText(tabBarNames[2]);
		} else if (tabBarNames != null && tabBarNames.length == 4) {
			getTopTabRl1().setVisibility(View.VISIBLE);
			getTopTabText1().setText(tabBarNames[0]);
			getTopTabRl2().setVisibility(View.VISIBLE);
			getTopTabText2().setText(tabBarNames[1]);
			getTopTabRl3().setVisibility(View.VISIBLE);
			getTopTabText3().setText(tabBarNames[2]);
			getTopTabRl4().setVisibility(View.VISIBLE);
			getTopTabText4().setText(tabBarNames[3]);
		}
	}

	/**
	 * 设置第一个tab提示个数
	 * 
	 * @param number
	 */
	public void setTopTabNumber1(String number) {
		if (number != null && !"".equals(number)) {
			getTopTabNumber1().setVisibility(View.VISIBLE);
			getTopTabNumber1().setText(number);
		}
	}

	/**
	 * 设置第二个tab提示个数
	 * 
	 * @param number
	 */
	public void setTopTabNumber2(String number) {
		if (number != null && !"".equals(number)) {
			getTopTabNumber2().setVisibility(View.VISIBLE);
			getTopTabNumber2().setText(number);
		}
	}

	/**
	 * 设置第三个tab提示个数
	 * 
	 * @param number
	 */
	public void setTopTabNumber3(String number) {
		if (number != null && !"".equals(number)) {
			getTopTabNumber3().setVisibility(View.VISIBLE);
			getTopTabNumber3().setText(number);
		}
	}

	/**
	 * 设置第三个tab提示个数
	 * 
	 * @param number
	 */
	public void setTopTabNumber4(String number) {
		if (number != null && !"".equals(number)) {
			getTopTabNumber4().setVisibility(View.VISIBLE);
			getTopTabNumber4().setText(number);
		}
	}

	/**
	 * 获取头部标签线
	 * 
	 * @return
	 */
	public ImageView getTabImage() {
		if (imageView == null && window != null) {
			imageView = (ImageView) window.findViewById(R.id.image);
		}
		return imageView;
	}

	/**
	 * 获取tab1
	 * 
	 * @return
	 */
	public TextView getTopTabText1() {
		if (top_tab_text1 == null && window != null) {
			top_tab_text1 = (TextView) window.findViewById(R.id.top_tab_text1);
		}
		return top_tab_text1;
	}

	/**
	 * 获取tab2
	 * 
	 * @return
	 */
	public TextView getTopTabText2() {
		if (top_tab_text2 == null && window != null) {
			top_tab_text2 = (TextView) window.findViewById(R.id.top_tab_text2);
		}
		return top_tab_text2;
	}

	/**
	 * 获取tab2
	 * 
	 * @return
	 */
	public TextView getTopTabText3() {
		if (top_tab_text3 == null && window != null) {
			top_tab_text3 = (TextView) window.findViewById(R.id.top_tab_text3);
		}
		return top_tab_text3;
	}

	/**
	 * 获取tab4
	 * 
	 * @return
	 */
	public TextView getTopTabText4() {
		if (top_tab_text4 == null && window != null) {
			top_tab_text4 = (TextView) window.findViewById(R.id.top_tab_text4);
		}
		return top_tab_text4;
	}

	/**
	 * 获取显示数字1
	 * 
	 * @return
	 */
	public TextView getTopTabNumber1() {
		if (top_tab_number1 == null && window != null) {
			top_tab_number1 = (TextView) window.findViewById(R.id.top_tab_number1);
		}
		return top_tab_number1;
	}

	/**
	 * 获取显示数字2
	 * 
	 * @return
	 */
	public TextView getTopTabNumber2() {
		if (top_tab_number2 == null && window != null) {
			top_tab_number2 = (TextView) window.findViewById(R.id.top_tab_number2);
		}
		return top_tab_number2;
	}

	/**
	 * 获取显示数字3
	 * 
	 * @return
	 */
	public TextView getTopTabNumber3() {
		if (top_tab_number3 == null && window != null) {
			top_tab_number3 = (TextView) window.findViewById(R.id.top_tab_number3);
		}
		return top_tab_number3;
	}

	/**
	 * 获取显示数字4
	 * 
	 * @return
	 */
	public TextView getTopTabNumber4() {
		if (top_tab_number4 == null && window != null) {
			top_tab_number4 = (TextView) window.findViewById(R.id.top_tab_number4);
		}
		return top_tab_number4;
	}
	public RelativeLayout getTopTabRl1(){
		if (top_tab_rl1 == null && window != null) {
			top_tab_rl1 = (RelativeLayout) window.findViewById(R.id.top_tab_rl1);
		}
		return top_tab_rl1;
	}
	public RelativeLayout getTopTabRl2(){
		if (top_tab_rl2 == null && window != null) {
			top_tab_rl2 = (RelativeLayout) window.findViewById(R.id.top_tab_rl2);
		}
		return top_tab_rl2;
	}
	public RelativeLayout getTopTabRl3(){
		if (top_tab_rl3 == null && window != null) {
			top_tab_rl3 = (RelativeLayout) window.findViewById(R.id.top_tab_rl3);
		}
		return top_tab_rl3;
	}
	public RelativeLayout getTopTabRl4(){
		if (top_tab_rl4 == null && window != null) {
			top_tab_rl4 = (RelativeLayout) window.findViewById(R.id.top_tab_rl4);
		}
		return top_tab_rl4;
	}
}
