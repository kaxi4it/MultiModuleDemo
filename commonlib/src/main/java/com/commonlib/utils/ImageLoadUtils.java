package com.commonlib.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.commonlib.base.BaseConfig;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;


/**
 * 图片加载类的再封装Picasso
 */
public class ImageLoadUtils {

    public static final String mTAG = "picasso";

    /**
     * 普通加载，无默认图，无加载失败图片，无缓存，路径不能为空
     */
    public static boolean into(String path, ImageView imageView) {
        if (!TextUtils.isEmpty(path)) {
            Picasso.with(BaseConfig.application)
                    .load(path)
                    .tag(mTAG)
                    .config(Bitmap.Config.RGB_565)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .transform(getTransformation(imageView))
                    .into(imageView);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 普通加载，无默认图，无加载失败图片，无缓存，路径不能为空
     */
    public static boolean into(int resourceID, ImageView imageView) {
        if (resourceID != 0) {
            Picasso.with(BaseConfig.application)
                    .load(resourceID)
                    .tag(mTAG)
                    .config(Bitmap.Config.RGB_565)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .transform(getTransformation(imageView))
                    .into(imageView);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 加载，有默认图，有加载失败图片，无缓存，路径不能为空
     */
    public static boolean into(String path, ImageView imageView, int holderID, int errorID) {
        if (!TextUtils.isEmpty(path)) {
            Picasso.with(BaseConfig.application)
                    .load(path)
                    .placeholder(holderID)
                    .error(errorID)
                    .tag(mTAG)
                    .config(Bitmap.Config.RGB_565)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .transform(getTransformation(imageView))
                    .into(imageView);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 普通加载，无默认图，无加载失败图片，缓存，路径不能为空
     */
    public static boolean intoWithCache(String path, ImageView imageView) {
        if (!TextUtils.isEmpty(path)) {
            Picasso.with(BaseConfig.application)
                    .load(path)
                    .tag(mTAG)
                    .config(Bitmap.Config.RGB_565)
                    .transform(getTransformation(imageView))
                    .into(imageView);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 普通加载，无默认图，无加载失败图片，缓存，路径不能为空
     */
    public static boolean intoWithCache(int resourceID, ImageView imageView) {
        if (resourceID != 0) {
            Picasso.with(BaseConfig.application)
                    .load(resourceID)
                    .tag(mTAG)
                    .config(Bitmap.Config.RGB_565)
                    .transform(getTransformation(imageView))
                    .into(imageView);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 加载，有默认图，有加载失败图片，缓存，路径不能为空
     */
    public static boolean intoWithCache(String path, ImageView imageView, int holderID, int errorID) {
        if (!TextUtils.isEmpty(path)) {
            Picasso.with(BaseConfig.application)
                    .load(path)
                    .placeholder(holderID)
                    .error(errorID)
                    .config(Bitmap.Config.RGB_565)
                    .tag(mTAG)
                    .transform(getTransformation(imageView))
                    .into(imageView);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 加载URI，无默认图，无加载失败图片，无缓存，路径不能为空，等比例缩放图片，图片选择器专用
     */
    public static boolean intoForImagePicker(String path, ImageView imageView) {
        if (!TextUtils.isEmpty(path)) {
            Picasso.with(BaseConfig.application)
                    .load(new File(path))
                    .tag(mTAG)
                    .config(Bitmap.Config.RGB_565)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .transform(getTransformation(imageView))
                    .into(imageView);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 加载URI，有默认图，有加载失败图片，无缓存，路径不能为空，等比例缩放图片，图片选择器专用
     */
    public static boolean intoForImagePicker(String path, ImageView imageView, int holderID, int errorID) {
        if (!TextUtils.isEmpty(path)) {
            Picasso.with(BaseConfig.application)
                    .load(new File(path))
                    .placeholder(holderID)
                    .error(errorID)
                    .tag(mTAG)
                    .config(Bitmap.Config.RGB_565)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .transform(getTransformation(imageView))
                    .into(imageView);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 加载URI，有默认图，有加载失败图片，无缓存，路径不能为空，等比例缩放图片，图片选择器专用
     */
    public static boolean intoForImagePicker(String path, ImageView imageView, int holderID, int errorID, int width, int height) {
        if (!TextUtils.isEmpty(path)) {
            Picasso.with(BaseConfig.application)
                    .load(new File(path))
                    .placeholder(holderID)
                    .error(errorID)
                    .tag(mTAG)
                    .config(Bitmap.Config.RGB_565)
                    .resize(width, height)
                    .centerInside()
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .transform(getTransformation(imageView))
                    .into(imageView);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 加载URI，有默认图，有加载失败图片，无缓存，路径不能为空，等比例缩放图片，图片选择器专用
     */
    public static boolean intoForImagePicker(String path, ImageView imageView, Drawable holderID, Drawable errorID) {
        if (!TextUtils.isEmpty(path)) {
            Picasso.with(BaseConfig.application)
                    .load(new File(path))
                    .placeholder(holderID)
                    .error(errorID)
                    .tag(mTAG)
                    .config(Bitmap.Config.RGB_565)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .transform(getTransformation(imageView))
                    .into(imageView);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 加载URI，有默认图，有加载失败图片，无缓存，路径不能为空，等比例缩放图片，图片选择器专用
     */
    public static boolean intoForImagePicker(String path, ImageView imageView, Drawable holderID, Drawable errorID, int width, int height) {
        if (!TextUtils.isEmpty(path)) {
            Picasso.with(BaseConfig.application)
                    .load(new File(path))
                    .placeholder(holderID)
                    .error(errorID)
                    .tag(mTAG)
                    .config(Bitmap.Config.RGB_565)
                    .resize(width, height)
                    .centerInside()
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .transform(getTransformation(imageView))
                    .into(imageView);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 暂停加载图片
     */
    public static void pauseLoad() {
        Picasso.with(BaseConfig.application).cancelTag(mTAG);
    }

    /**
     * 恢复加载图片
     */
    public static void resumeLoad() {
        Picasso.with(BaseConfig.application).resumeTag(mTAG);
    }


    /**
     * 按照imageView设置的宽度比例来缩放图片
     */
    public static Transformation getTransformation(final ImageView imageView) {
        Transformation transformation = new Transformation() {

            @Override
            public Bitmap transform(Bitmap source) {

                int targetWidth = imageView.getWidth();
//                Logger.i("source.getHeight()="+source.getHeight()+",source.getWidth()="+source.getWidth()+",targetWidth="+targetWidth);

                if (source.getWidth() == 0) {
                    return source;
                }

                //如果图片小于设置的宽度，则返回原图
                if (source.getWidth() < targetWidth) {
                    return source;
                } else {
                    //如果图片大小大于等于设置的宽度，则按照设置的宽度比例来缩放
                    double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                    int targetHeight = (int) (targetWidth * aspectRatio);
                    if (targetHeight != 0 && targetWidth != 0) {
                        Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                        if (result != source) {
                            // Same bitmap is returned if sizes are the same
                            source.recycle();
                        }
                        return result;
                    } else {
                        return source;
                    }
                }

            }

            @Override
            public String key() {
                return "transformation" + " desiredWidth";
            }
        };
        return transformation;
    }
}
