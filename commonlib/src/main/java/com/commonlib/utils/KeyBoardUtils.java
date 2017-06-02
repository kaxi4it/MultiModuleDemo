package com.commonlib.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.commonlib.base.BaseConfig;


/**
 * 软键盘工具类
 */
public class KeyBoardUtils {
    /**
     * 打开软键盘
     *
     * @param mEditText 输入框
     */
    public static void openKeybord(EditText mEditText) {
        InputMethodManager imm = (InputMethodManager) BaseConfig.application
                .getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
//        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
//                InputMethodManager.HIDE_IMPLICIT_ONLY);
        imm.showSoftInput(mEditText, 0);
    }

    /**
     * 关闭软键盘
     *
     * @param mEditText 输入框
     */
    public static void closeKeybord(EditText mEditText) {
        InputMethodManager imm = (InputMethodManager) BaseConfig.application
                .getSystemService(Context.INPUT_METHOD_SERVICE);

//        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

    /**
     * 成对使用，显示隐藏都可用这个
     */
    public static void toggleKeybord() {
        InputMethodManager imm = (InputMethodManager) BaseConfig.application.getApplicationContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
}