package com.mmd;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.commonlib.base.BaseApplication;

/**
 * 作　　者: guyj
 * 修改日期: 2017/5/18
 * 描　　述:
 * 备　　注:
 */

public class AllApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }
}
