package com.commonlib.utils;

import android.app.Activity;
import android.app.Application;
import android.graphics.drawable.Drawable;

import com.antfortune.freeline.FreelineCore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheEntity;
import com.lzy.okhttputils.cache.CacheMode;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.PauseOnScrollListener;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * 作　　者: guyj
 * 修改日期: 2016/11/16
 * 描　　述: 单例模式，链式调用初始化application中所需配置的库
 * 备　　注:
 */
public class AppInitUtils {

    private static class SingleTon {
        private static final AppInitUtils INSTANCE = new AppInitUtils();
    }

    public static AppInitUtils getInstance() {
        return SingleTon.INSTANCE;
    }

    private AppInitUtils() {
    }

    private Gson gson;
    private FunctionConfig functionConfig;
    private String QiNiuExternalLinks;
    private Application application;

    private boolean isDebug;//项目debug/release区分标识
    private boolean isInitGson;
    private boolean isInitFreelineCore;
    private boolean isInitLeakCanary;
    private boolean isInitLogger;
    private boolean isInitOkHttp;
    private boolean isInitImagePicker;
    private boolean isInitQiNiu;

    /**
     * 开放获取gson对象
     */
    public Gson getGson() {
        return gson;
    }

    /**
     * 开放获取application对象
     */
    public Application getApplication() {
        return application;
    }

    /**
     * 开放获取debug对象
     */
    public boolean getDebug() {
        return isDebug;
    }

    /**
     * 实际初始化的地方
     */
    public AppInitUtils create() {
        if (null == application) {
            throw new RuntimeException("AppInitUtils初始化时请调用setApplication()");
        } else {
            realInitGson();
            realInitFreelineCore();
            realInitLeakCanary();
            realInitLogger();
            realInitOkHttp();
            realInitImagePicker();
            realInitQiNiuUpLoad();
        }
        return this;
    }

    /**
     * 配置类型debug/release
     */
    public AppInitUtils isDebug() {
        isDebug = true;
        return this;
    }

    /**
     * 配置application
     */
    public AppInitUtils setApplication(Application application) {
        this.application = application;
        return this;
    }

    /**
     * 配置gson
     */
    public AppInitUtils initGson() {
        isInitGson = true;
        return this;
    }

    private void realInitGson() {
        if (isInitGson)
            gson = new GsonBuilder().create();
    }

    /**
     * 配置增量编译by Alibaba
     */
    public AppInitUtils initFreelineCore() {
        isInitFreelineCore = true;
        return this;
    }

    private void realInitFreelineCore() {
        if (isDebug && isInitFreelineCore)
            FreelineCore.init(application);
    }

    /**
     * 配置检测内存溢出库
     */
    public AppInitUtils initLeakCanary() {
        isInitLeakCanary = true;
        return this;
    }

    private void realInitLeakCanary() {
        if (isDebug && isInitLeakCanary)
            LeakCanary.install(application);
    }

    /**
     * 配置logger日志库
     */
    public AppInitUtils initLogger() {
        isInitLogger = true;
        return this;
    }

    private void realInitLogger() {
        if (isInitLogger) {
            if (isDebug) {
                Logger.init("log").logLevel(LogLevel.FULL);
            } else {
                Logger.init("log").logLevel(LogLevel.NONE);
            }
        }
    }

    /**
     * 配置lzy基于okhttp封装的okgo
     */
    public AppInitUtils initOkHttp() {
        isInitOkHttp = true;
        return this;
    }

    private void realInitOkHttp() {
        if (isInitOkHttp) {
            OkHttpUtils.init(application);
            if (isDebug) {
                OkHttpUtils.getInstance().debug("okhttp")
                        .setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                        .setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                        .setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)    //全局的写入超时时间
                        .setCacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)     //可以全局统一设置缓存模式,默认就是Default,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy0216/
                        .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)         //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                ;
            } else {
                OkHttpUtils.getInstance()
                        .setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                        .setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                        .setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)    //全局的写入超时时间
                        .setCacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)     //可以全局统一设置缓存模式,默认就是Default,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy0216/
                        .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)         //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                ;
            }
        }
    }

    /**
     * 配置galleryfinal的图库选择器
     */
    public AppInitUtils initImagePicker(FunctionConfig functionConfig) {
        isInitImagePicker = true;
        this.functionConfig = functionConfig;
        return this;
    }

    public void realInitImagePicker() {
        if (isInitImagePicker) {
            //设置主题
            ThemeConfig theme = ThemeConfig.ORANGE;
//        ThemeConfig theme = new ThemeConfig.Builder()
//                .build();自定义主题
            //配置功能
//        functionConfig = new FunctionConfig.Builder()
//                .setEnableCamera(true)
//                .setEnableEdit(true)
//                .setEnableCrop(true)
//                .setEnableRotate(false)
//                .setCropSquare(false)
//                .setEnablePreview(true)
//                .setMutiSelectMaxSize(9)
//                .build();
            CoreConfig coreConfig = new CoreConfig.Builder(application, new ImageLoader() {

                @Override
                public void displayImage(Activity activity, String path, GFImageView imageView, Drawable defaultDrawable, int width, int height) {
                    ImageLoadUtils.intoForImagePicker(path, imageView, defaultDrawable, defaultDrawable, width, height);
                }

                @Override
                public void clearMemoryCache() {

                }
            }, theme)
                    .setFunctionConfig(functionConfig)
                    .setPauseOnScrollListener(new PauseOnScrollListener(true, true) {
                        @Override
                        public void resume() {
                            ImageLoadUtils.resumeLoad();
                        }

                        @Override
                        public void pause() {
                            ImageLoadUtils.pauseLoad();
                        }
                    })
                    .setNoAnimcation(true)
                    .build();
            GalleryFinal.init(coreConfig);

        }
    }

    /**
     * 配置七牛图片云存储
     */
    public AppInitUtils initQiNiuUpLoad(String QiNiuExternalLinks) {
        isInitQiNiu = true;
        this.QiNiuExternalLinks = QiNiuExternalLinks;
        return this;
    }

    public void realInitQiNiuUpLoad() {
        if (isInitQiNiu) {
            QiNiuUpLoadUtils.externalLinks = QiNiuExternalLinks;
        }
    }

}
