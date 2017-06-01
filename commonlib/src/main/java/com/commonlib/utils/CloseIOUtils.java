package com.commonlib.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * 作　　者: guyj
 * 修改日期: 2017/2/15
 * 描　　述: 关闭IO流工具
 * 备　　注:
 */
public class CloseIOUtils {
    /**
     * 关闭IO
     *
     * @param closeables closeable
     */
    public static void closeIO(Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    LogUtils.e("CloseIOUtils", e.getMessage());
                }
            }
        }
    }

    /**
     * 安静关闭IO
     *
     * @param closeables closeable
     */
    public static void closeIOQuietly(Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    LogUtils.e("CloseIOUtils", e.getMessage());
                }
            }
        }
    }
}
