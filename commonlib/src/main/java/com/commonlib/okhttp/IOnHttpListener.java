package com.commonlib.okhttp;

import okhttp3.Response;

/**
 * 作　　者: guyj
 * 修改日期: 2016/11/27
 * 描　　述:
 * 备　　注:
 */

public interface IOnHttpListener<T> {
    void onSuccess(T t);

    void onError(Response response, Exception e);

    void onCache(T t);

    void onAfter();

    void onBefore();
}
