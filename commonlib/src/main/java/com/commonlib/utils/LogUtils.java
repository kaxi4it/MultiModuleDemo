package com.commonlib.utils;

import android.util.Log;

import com.commonlib.base.BaseConfig;
import com.orhanobut.logger.Logger;

/**
 * log管理类
 * Log是系统自带的普通Log
 * Logger是比较详细的日志打印三方库，需在application中进行配置，打印内容包括 线程，代码位置，输出内容；
 */
public class LogUtils {
    public static void d(String tag, String msg) {
        if (BaseConfig.isDebug)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (BaseConfig.isDebug)
            Log.e(tag, msg);
    }

    public static void dLoger(String msg) {
        if (BaseConfig.isDebug)
            Logger.d(msg);
    }

    public static void eLoger(String msg) {
        if (BaseConfig.isDebug)
            Logger.e(msg);
    }

    public static void jsonLoger(String json) {
        if (BaseConfig.isDebug)
            Logger.json(json);
    }
}
