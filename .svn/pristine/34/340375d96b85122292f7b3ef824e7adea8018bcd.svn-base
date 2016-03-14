package com.yunchengke.app.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.yunchengke.app.R;

import butterknife.ButterKnife;

/**
 * 类名：CompoundView <br/>
 * 描述：符合View的Demo <br/>
 * 创建时间：2015年11月19日 下午1:57:56
 * @author 709835509@qq.com
 * @version 1.0
 */
public abstract class BaseCompoundView extends RelativeLayout {

	public BaseCompoundView(Context context) {
		this(context, null);
	}

	public BaseCompoundView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public BaseCompoundView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs, defStyleAttr, 0);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public BaseCompoundView(Context context, AttributeSet attrs, int defStyleAttr,
							int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);		
		init(context, attrs, defStyleAttr, defStyleRes);
	}

	protected void init(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {

		setBackgroundResource(R.color.transparent);

		// 这里所有使用RelativeLayout
		LayoutInflater.from(getContext()).inflate(getContentLayoutResourceId(), this, true);

		ButterKnife.bind(this);

		initViews();
	}

	protected abstract void initViews();

	public abstract int getContentLayoutResourceId();
}
