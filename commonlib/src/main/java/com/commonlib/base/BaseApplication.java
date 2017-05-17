package com.commonlib.base;

import android.app.Application;
import android.content.Context;

/**
 * 作　　者: guyj
 * 修改日期: 2017/5/18
 * 描　　述:
 * 备　　注:
 */

public class BaseApplication extends Application{

    private static BaseApplication baseApplication;

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        context = this.getApplicationContext();

    }
}
