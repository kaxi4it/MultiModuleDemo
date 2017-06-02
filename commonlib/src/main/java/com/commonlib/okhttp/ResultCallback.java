package com.commonlib.okhttp;

import android.support.annotation.Nullable;

import com.commonlib.base.BaseConfig;
import com.commonlib.utils.DateFormatUtils;
import com.commonlib.utils.LogUtils;
import com.google.gson.internal.$Gson$Types;
import com.lzy.okhttputils.callback.AbsCallback;
import com.lzy.okhttputils.request.BaseRequest;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

/**
 * 作　　者: guyj
 * 修改日期: 2016/11/16
 * 描　　述: 自定义Callback，网络请求返回 解析为实体
 * 备　　注:
 */
public abstract class ResultCallback<T> extends AbsCallback<T> {
    Type mType;

    public ResultCallback() {
        this.mType = getSuperClassTypeParameter(getClass());
    }

    private static Type getSuperClassTypeParameter(Class<?> subClass) {
        Type superclass = subClass.getGenericSuperclass();
        if (superclass instanceof Class) {
            try {
                throw new Exception("缺少实体");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ParameterizedType parameterizedType = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
    }


    @Override
    public T parseNetworkResponse(Response response) throws IOException {
        String string = response.body().string();
        return BaseConfig.gson.fromJson(string, mType);
    }

//    @Override
//    public abstract void onBefore(BaseRequest request);

    @Override
    public abstract void onAfter(@Nullable T t, @Nullable Exception e);

    @Override
    public abstract void onCacheSuccess(T t, Call call);

    public abstract void onBefored(BaseRequest request);

    public abstract void onErrored(Call call, Response response, Exception e);

    @Override
    public void onBefore(BaseRequest request) {
        onBefored(request);
    }

    @Override
    public void onError(Call call, Response response, Exception e) {
        if (null != response) {
            LogUtils.eLoger("url:" + response.request().url()
                    + "\nheader:" + response.request().headers()
                    + "content:" + bodyToString(response.request())
                    + "\nreceived-time:" + DateFormatUtils.long2StringByFormat(response.receivedResponseAtMillis(), DateFormatUtils.getHHmmssSSSChinaFormat())
                    + "\nmsg:" + e.getMessage());
        }
        onErrored(call, response, e);
    }

    private String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "something error when show requestBody.";
        }
    }
}
