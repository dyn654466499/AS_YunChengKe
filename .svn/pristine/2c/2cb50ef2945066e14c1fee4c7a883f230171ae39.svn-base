package com.yunchengke.app.dynamic.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunchengke.app.R;

/**
 * 标题设定
 *
 */
public class TitleActionBar
{
	private RelativeLayout app_title_left_button;// 左边按钮
	private ImageButton app_title_right_button;// 右边按钮
	private TextView app_title_right_button1;// 右边按钮
	private TextView app_top_title;// 标题
	private View app_title;// 标题
	private Window window;// window实例
	private ImageButton app_title_right_button2;

	public TitleActionBar(Window window)
	{
		this.window = window;
	}

	/**
	 * 获取左边按钮实例
	 * 
	 * @return
	 */
	public RelativeLayout getAppTitleLeftButton()
	{
		if (app_title_left_button == null && window != null)
		{
			app_title_left_button = (RelativeLayout) window
					.findViewById(R.id.app_title_left_button);
		}
		return app_title_left_button;
	}

	/**
	 * 设置左边按钮
	 * 
	 * @param drawable
	 * @param bg
	 * @param onClickListener
	 */
	public void setAppTitleLeftButton(int bg,
			OnClickListener onClickListener)
	{
		getAppTitleLeftButton().setVisibility(View.VISIBLE);
		getAppTitleLeftButton().setClickable(true);
//		getAppTitleLeftButton().setImageResource(drawable)
		if(bg!=0)getAppTitleLeftButton().setBackgroundResource(bg);
		getAppTitleLeftButton().setOnClickListener(onClickListener);
	}
	public void setAppTitleLeftButton(int drawable, int bg
					)
			{
				getAppTitleLeftButton().setVisibility(View.VISIBLE);
				getAppTitleLeftButton().setClickable(true);
//				getAppTitleLeftButton().setImageResource(drawable);
//				getAppTitleLeftButton().setBackgroundResource(bg);
				//getAppTitleLeftButton().setOnClickListener(onClickListener);
			}
			
	
	/**
	 * 设置左边按钮隐藏
	 */
	public void setAppTitleLeftButtonGone()
	{
		getAppTitleLeftButton().setVisibility(View.GONE);
	}

	/**
	 * 设置左边按钮显示
	 */
	public void setAppTitleLeftButtonVisible()
	{
		getAppTitleLeftButton().setVisibility(View.VISIBLE);
	}

	/**
	 * 获取头部标签
	 * 
	 * @return
	 */
	public TextView getAppTopTitle()
	{
		if (app_top_title == null && window != null)
		{
			app_top_title = (TextView) window.findViewById(R.id.app_top_title);
		}
		return app_top_title;
	}
	
	/**
	 * 整个标题
	 * @return
	 */
	public View getTopTitle()
	{
		if (app_title == null && window != null)
		{
			app_title = (View) window.findViewById(R.id.app_top_color);
		}
		return app_title;
	}

	/**
	 * 设置ActionBar名称
	 * 
	 * @param title
	 */
	public void setAppTopTitle(String title)
	{
		getAppTopTitle().setText(title); 
	}

	/**
	 * 设置ActionBar名称
	 * 
	 * @param titleid
	 */
	public void setAppTopTitle(int titleid)
	{
		getAppTopTitle().setText(titleid);
	}

	/**
	 * 获取右边按钮实例
	 * 
	 * @return
	 */
	public ImageButton getAppTitleRightButton()
	{
		if (app_title_right_button == null && window != null)
		{
			app_title_right_button = (ImageButton) window
					.findViewById(R.id.app_title_right_button);
		}
		return app_title_right_button;
	}
	public TextView getAppTitleRightButton1()
	{
		if (app_title_right_button1 == null && window != null)
		{
			app_title_right_button1 = (TextView) window
					.findViewById(R.id.app_title_right_button1);
		}
		return app_title_right_button1;
	}
	public ImageButton getAppTitleRightButton2()
	{
		if (app_title_right_button2 == null && window != null)
		{
			app_title_right_button2 = (ImageButton) window
					.findViewById(R.id.app_title_right_button2);
		}
		return app_title_right_button2;
	}
	
	/**
	 * 设置右边按钮
	 * 
	 * @param drawable
	 * @param bg
	 * @param onClickListener
	 */
	public void setAppTitleRightButton(int drawable, int bg,
			OnClickListener onClickListener)
	{
		getAppTitleRightButton().setVisibility(View.VISIBLE);
		getAppTitleRightButton().setClickable(true);
		getAppTitleRightButton().setImageResource(drawable);
//		getAppTitleRightButton().setBackgroundResource(bg);
		getAppTitleRightButton().setOnClickListener(onClickListener);
	}
	
	
	public void setAppTitleRightButton1(CharSequence title, int bg,
	                       			OnClickListener onClickListener)
	                       	{
	                       		getAppTitleRightButton1().setVisibility(View.VISIBLE);
	                       		getAppTitleRightButton1().setClickable(true);
	                       		getAppTitleRightButton1().setText(title);
//	                       		getAppTitleRightButton1().setTextColor(R.color.title_main_text);
	                       		//getAppTitleRightButton1().setImageResource(drawable);
	                       		if(bg!=0)getAppTitleRightButton1().setBackgroundResource(bg);
	                       		getAppTitleRightButton1().setOnClickListener(onClickListener);
	                       	}
	public void setAppTitleRightButton2(int drawable, int bg,OnClickListener onClickListener)
	{
		getAppTitleRightButton2().setVisibility(View.VISIBLE);
		getAppTitleRightButton2().setClickable(true);
		getAppTitleRightButton2().setImageResource(drawable);
//		getAppTitleRightButton2().setBackgroundResource(bg);
		getAppTitleRightButton2().setOnClickListener(onClickListener);
	}
	
	public void setAppTitleRightButton1(CharSequence title	)
		                       	{
		                       		getAppTitleRightButton1().setVisibility(View.VISIBLE);
		                       		getAppTitleRightButton1().setClickable(true);
		                       		getAppTitleRightButton1().setText(title);
		                       	}
	
	public void setAppTitleRightButton(int drawable, int bg
					)
			{
				getAppTitleRightButton().setVisibility(View.VISIBLE);
				getAppTitleRightButton().setClickable(true);
				getAppTitleRightButton().setImageResource(drawable);
//				getAppTitleRightButton().setBackgroundResource(bg);
			}

	/**
	 * 设置右边按钮隐藏
	 */
	public void setAppTitleRightButtonGone()
	{
		getAppTitleRightButton().setVisibility(View.GONE);
	}

	/**
	 * 设置右边按钮显示
	 */
	public void setAppTitleRightButtonVisible()
	{
		getAppTitleRightButton().setVisibility(View.VISIBLE);
	}
	
	
	/**
	 * 设置右边按钮隐藏
	 */
	public void setAppTitleRightButton1Gone()
	{
		getAppTitleRightButton1().setVisibility(View.GONE);
	}
	/**
	 * 设置右边按钮隐藏
	 */
	public void setAppTitleRightButton2Gone()
	{
		getAppTitleRightButton2().setVisibility(View.GONE);
	}

	/**
	 * 设置右边按钮显示
	 */
	public void setAppTitleRightButtonVisible1()
	{
		getAppTitleRightButton1().setVisibility(View.VISIBLE);
	}
}
