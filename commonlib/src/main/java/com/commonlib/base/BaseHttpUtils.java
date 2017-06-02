package com.commonlib.base;

import com.commonlib.okhttp.ResultCallback;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;
import com.lzy.okhttputils.callback.FileCallback;
import com.lzy.okhttputils.model.HttpHeaders;
import com.lzy.okhttputils.model.HttpParams;

import java.io.File;

/**
 * 作　　者: guyj
 * 修改日期: 2016/11/16
 * 描　　述: 基本HTTP管理类，普通GET POST请求，已经cancel取消请求
 * 备　　注:
 */
public abstract class BaseHttpUtils<T> {
    protected String TAG = BaseConfig.httpTag;

    /**
     * 停止http调用
     */
    protected void cancelPost() {
        OkHttpUtils.getInstance().cancelTag(TAG);
    }

    /**
     * GET请求，缓存自定，头文件自定
     */
    protected void httpGet(String host, String url, HttpParams params, HttpHeaders headers, boolean hasCache, ResultCallback<T> callback) {
        OkHttpUtils
                .get(host + url)
                .headers(headers)
                .params(params)
                .tag(TAG)
                .cacheKey(host + url)
                .cacheMode(hasCache ? CacheMode.FIRST_CACHE_THEN_REQUEST : CacheMode.NO_CACHE)
                .execute(callback);
    }

    /**
     * GET请求，缓存自定，默认头文件
     */
    protected void httpGet(String host, String url, HttpParams params, boolean hasCache, ResultCallback<T> callback) {
        httpGet(host, url, params, null, hasCache, callback);

    }

    /**
     * GET请求,默认无缓存，默认头文件
     */
    protected void httpGet(String host, String url, HttpParams params, ResultCallback<T> callback) {
        httpGet(host, url, params, null, false, callback);

    }

    /**
     * POST请求，缓存自定，头文件自定
     */
    protected void httpPost(String host, String url, HttpParams params, HttpHeaders headers, boolean hasCache, ResultCallback<T> callback) {
        OkHttpUtils
                .post(host + url)
                .headers(headers)
                .params(params)
                .tag(TAG)
                .cacheKey(host + url)
                .cacheMode(hasCache ? CacheMode.FIRST_CACHE_THEN_REQUEST : CacheMode.NO_CACHE)
                .execute(callback);
    }

    /**
     * POST请求，缓存自定，默认头文件
     */
    protected void httpPost(String host, String url, HttpParams params, boolean hasCache, ResultCallback<T> callback) {
        httpPost(host, url, params, null, hasCache, callback);
    }

    /**
     * POST请求,默认无缓存，默认头文件
     */
    protected void httpPost(String host, String url, HttpParams params, ResultCallback<T> callback) {
        httpPost(host, url, params, null, false, callback);
    }

    /**
     * POST请求,默认无缓存，头文件自定
     */
    protected void httpPost(String host, String url, HttpParams params, HttpHeaders headers, ResultCallback<T> callback) {
        httpPost(host, url, params, headers, false, callback);
    }

    /**
     * POST请求上传json格式，缓存自定，头文件自定
     */
    protected void httpPostJson(String host, String url, String params, HttpHeaders headers, boolean hasCache, ResultCallback<T> callback) {
        OkHttpUtils
                .post(host + url)
                .headers(headers)
                .upJson(params)
                .tag(TAG)
                .cacheKey(host + url)
                .cacheMode(hasCache ? CacheMode.FIRST_CACHE_THEN_REQUEST : CacheMode.NO_CACHE)
                .execute(callback);
    }

    /**
     * POST请求上传json格式，缓存自定，默认头文件
     */
    protected void httpPostJson(String host, String url, String params, boolean hasCache, ResultCallback<T> callback) {
        httpPostJson(host, url, params, null, hasCache, callback);
    }

    /**
     * POST请求上传json格式,默认无缓存，默认头文件
     */
    protected void httpPostJson(String host, String url, String params, ResultCallback<T> callback) {
        httpPostJson(host, url, params, null, false, callback);
    }

    /**
     * POST请求上传File格式，头文件自定
     */
    protected void httpPostFile(String host, String url, HttpParams params, File fileParams, HttpHeaders headers, FileCallback callback) {
        OkHttpUtils
                .post(host + url)
                .headers(headers)
                .params(params)
                .params(fileParams.getName(), fileParams)
                .tag(TAG)
                .cacheMode(CacheMode.NO_CACHE)
                .execute(callback);
    }

    /**
     * POST请求上传File格式，默认无表单内容，头文件自定
     */
    protected void httpPostFile(String host, String url, File fileParams, HttpHeaders headers, FileCallback callback) {
        httpPostFile(host, url, null, fileParams, headers, callback);
    }

    /**
     * POST请求上传File格式，默认无表单内容，默认头文件
     */
    protected void httpPostFile(String host, String url, File fileParams, FileCallback callback) {
        httpPostFile(host, url, null, fileParams, null, callback);
    }
}
