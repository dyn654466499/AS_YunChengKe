package com.yunchengke.app.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.WindowInsets;
import android.widget.FrameLayout;

/**
 * 名称: WindowInsetsFrameLayout <br/>
 * 描述: 解决状态栏透明后，adjustResize不起作用 <br/>
 * 参考：https://github.com/mikepenz/MaterialDrawer/issues/95 <br/>
 * 创建时间：2016/1/28 12:08
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class WindowInsetsFrameLayout extends FrameLayout {
    private int[] mInsets = new int[4];

    public WindowInsetsFrameLayout(Context context) {
        super(context);
    }

    public WindowInsetsFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WindowInsetsFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public WindowInsetsFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public final int[] getInsets() {
        return mInsets;
    }

    @Override
    protected final boolean fitSystemWindows(Rect insets) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            // Intentionally do not modify the bottom inset. For some reason,
            // if the bottom inset is modified, window resizing stops working.

            mInsets[0] = insets.left;
            mInsets[1] = insets.top;
            mInsets[2] = insets.right;

            insets.left = 0;
            insets.top = 0;
            insets.right = 0;
        }

        return super.fitSystemWindows(insets);
    }

    @Override
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            mInsets[0] = insets.getSystemWindowInsetLeft();
            mInsets[1] = insets.getSystemWindowInsetTop();
            mInsets[2] = insets.getSystemWindowInsetRight();
            return super.onApplyWindowInsets(insets.replaceSystemWindowInsets(0, 0, 0,
                    insets.getSystemWindowInsetBottom()));
        } else {
            return insets;
        }
    }
}
