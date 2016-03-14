package com.yunchengke.app.ui.widget;


import android.annotation.TargetApi;
import android.os.Build;
import android.widget.GridView;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.yunchengke.app.R;

/**
 * 名称: SplitLineGridView <br/>
 * 描述: 具有分割线的GridView <br/>
 * 创建时间：2016/1/15 14:43
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */

public class SplitLineGridView extends GridView {

    private static int DEFAULT_COLUMN = 3;

    boolean expanded = true;

    public SplitLineGridView(Context context) {
        super(context);
    }

    public SplitLineGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SplitLineGridView(Context context, AttributeSet attrs,
                               int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public SplitLineGridView(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        if (!isInEditMode()) {

            View localView = getChildAt(0);

            int column;
            if (localView != null) {
                column = getWidth() / localView.getWidth();
            } else {
                column = DEFAULT_COLUMN;
            }

            int childCount = getChildCount();
            Paint localPaint;
            localPaint = new Paint();
            localPaint.setStyle(Paint.Style.STROKE);
            localPaint.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.split_line_height));
            localPaint.setColor(getContext().getResources().getColor(
                    R.color.grid_view_split_line_color));

            for (int i = 0; i < childCount; i++) {
                View cellView = getChildAt(i);
                if ((i + 1) % column == 0) { // 第一行
                    canvas.drawLine(cellView.getLeft(), cellView.getBottom(),
                            cellView.getRight(), cellView.getBottom(),
                            localPaint);
                } else if ((i + 1) > (childCount - (childCount % column))) {
                    canvas.drawLine(cellView.getRight(), cellView.getTop(),
                            cellView.getRight(), cellView.getBottom(),
                            localPaint);
                } else {
                    canvas.drawLine(cellView.getRight(), cellView.getTop(),
                            cellView.getRight(), cellView.getBottom(),
                            localPaint);
                    canvas.drawLine(cellView.getLeft(), cellView.getBottom(),
                            cellView.getRight(), cellView.getBottom(),
                            localPaint);
                }

                // 第一行顶部是否需要分割线
                if (i < column) {
                    canvas.drawLine(cellView.getLeft(), cellView.getTop(),
                            cellView.getRight(), cellView.getTop(),
                            localPaint);
                }
            }

            if (childCount % column != 0) {
                for (int j = 0; j < (column - childCount % column); j++) {
                    View lastView = getChildAt(childCount - 1);
                    canvas.drawLine(lastView.getRight() + lastView.getWidth()
                                    * j, lastView.getTop(), lastView.getRight()
                                    + lastView.getWidth() * j, lastView.getBottom(),
                            localPaint);
                }
            }

        }
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isExpanded()) {
            try {
                int expandSpec = MeasureSpec.makeMeasureSpec(
                        Integer.MAX_VALUE >> 2,
                        MeasureSpec.AT_MOST);
                super.onMeasure(widthMeasureSpec, expandSpec);
            } catch (Exception e) {
                // 捕捉异常
            }
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}

