package com.commonlib.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * 作　　者: guyj
 * 修改日期: 2016/11/29
 * 描　　述: 此TextView添加了2次点击区间的无效时间，防止短时间内多次点击，设置lazyTime控制，单位毫秒
 * 备　　注:
 */
public class LazyTextView extends TextView {

    Long lazyTime = 500l;
    Long clickTime = 0l;
    Long lastClickTime = 0l;

    public LazyTextView(Context context) {
        super(context);
    }

    public LazyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LazyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LazyTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                clickTime = System.currentTimeMillis();
                if (clickTime - lastClickTime < lazyTime) {
                    this.setClickable(false);
                } else {
                    this.setClickable(true);
                }
                lastClickTime = clickTime;
                break;
        }
        return super.onTouchEvent(event);
    }
}
