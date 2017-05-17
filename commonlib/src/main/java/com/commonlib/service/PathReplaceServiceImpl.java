package com.commonlib.service;

import android.content.Context;
import android.net.Uri;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.PathReplaceService;

/**
 * 作　　者: guyj
 * 修改日期: 2017/5/18
 * 描　　述: fix log "W/ARouter::: ARouter::No postcard![ ]"
 * 这个Log正常的情况下也会打印出来，如果您的代码中没有实现DegradeService和PathReplaceService的话，
 * 因为ARouter本身的一些功能也依赖 自己提供的Service管理功能，
 * ARouter在跳转的时候会尝试寻找用户实现的PathReplaceService，用于对路径进行重写(可选功能)，
 * 所以如果您没有 实现这个服务的话，也会抛出这个日志
 * 推荐在app中实现DegradeService、PathReplaceService
 * 备　　注:
 */
@Route(path = "/sdk/service")
public class PathReplaceServiceImpl implements PathReplaceService {
    @Override
    public String forString(String path) {
        return path;
    }

    @Override
    public Uri forUri(Uri uri) {
        return uri;
    }

    @Override
    public void init(Context context) {

    }
}
