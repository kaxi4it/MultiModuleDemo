package com.commonlib.base;


import android.app.Application;

import com.commonlib.utils.AppInitUtils;
import com.google.gson.Gson;

/**
 * 由于工具类中为了简化使用代码，直接把application写入，未避免工具类多了以后，替换包名不方便的问题，把几个关键变量在这里进行替换和修改包名
 */
public class BaseConfig {
    public static Application application = AppInitUtils.getInstance().getApplication();
    public static boolean isDebug = AppInitUtils.getInstance().getDebug();
    public static Gson gson = AppInitUtils.getInstance().getGson();
    public static String httpTag = "HttpUtils";
}
