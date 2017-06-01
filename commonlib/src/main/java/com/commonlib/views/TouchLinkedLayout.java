package com.commonlib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.commonlib.utils.LogUtils;

/**
 * 作　　者: guyj
 * 修改日期: 2016/11/29
 * 描　　述: 触摸事件传递给所有子views
 * 备　　注:
 */
public class TouchLinkedLayout extends FrameLayout {
    public TouchLinkedLayout(Context context) {
        super(context);
    }

    public TouchLinkedLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchLinkedLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            try {
                child.dispatchTouchEvent(ev);
            } catch (Exception e) {
                LogUtils.e("TouchLinkedLayout", e.getMessage());
            }
        }
        return true;
    }
}
