package com.commonlib.utils;

import android.widget.EditText;
import android.widget.Toast;

import com.commonlib.base.BaseConfig;

/**
 * Toast工具类，对重复显示进行了优化，第二个toast不用等第一个toast消失即可显示
 */
public class ToastUtils {

    private static Toast toast;

    /**
     * 显示短时间的Toast
     *
     * @param msg 输出信息
     */
    public static void showToast(String msg) {
        if (msg == null) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(BaseConfig.application, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    /**
     * 显示短时间toast
     *
     * @param resId 输入string.xml的id
     */
    public static void showToast(int resId) {
        if (resId == 0) {
            return;
        }
        showToast(BaseConfig.application.getResources().getString(resId));
    }

    /**
     * 显示长时间的Toast
     *
     * @param msg
     */
    public static void showLongToast(String msg) {
        if (msg == null) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(BaseConfig.application, msg, Toast.LENGTH_LONG);
        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_LONG);
        }
        toast.show();
    }

    /**
     * 作用：提示输入错误,让输入框获得光标
     */
    public static void alertInputError(String msg, EditText et) {
        if (msg == null) {
            return;
        }
        showLongToast(msg);
        et.requestFocus();
    }

    /**
     * 显示长时间toast
     *
     * @param resId 输入string.xml的id
     */
    public static void showLongToast(int resId) {
        if (resId == 0) {
            return;
        }
        showLongToast(BaseConfig.application.getResources().getString(resId));
    }
}
