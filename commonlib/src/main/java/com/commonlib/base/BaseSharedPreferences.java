package com.commonlib.base;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.ImageView;


import com.commonlib.entity.SPStringBean;
import com.commonlib.utils.LogUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * 作　　者: guyj
 * 修改日期: 2016/11/16
 * 描　　述: 基础SharedPreferences类，封装了save/load String/Object/BitmapDrawable;remove指定key以及clear所有
 * 备　　注:
 */
public abstract class BaseSharedPreferences {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private String strBase64;
    private SPStringBean cacheBean;
    private byte[] base64Bytes;

    protected BaseSharedPreferences() {
        preferences = BaseConfig.application.getSharedPreferences("AppInfo", Activity.MODE_PRIVATE);
        editor = preferences.edit();
    }

    protected void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    protected void clear() {
        editor.clear();
        editor.commit();
    }

    protected boolean saveStrCache(String key, String value, Long activeMillisecond) {
        return saveObj(key, new SPStringBean(value, activeMillisecond));
    }

    protected String loadStrCache(String key) {
        cacheBean = (SPStringBean) loadObj(key);
        if (cacheBean.getEndTime() < System.currentTimeMillis()) {//已过期
            return "";
        } else {
            return cacheBean.getStr();
        }
    }

    protected boolean saveString(String key, String value) {
        return editor.putString(key, value).commit();
    }

    protected String loadString(String key) {
        return preferences.getString(key, "");
    }

    /**
     * @param key
     * @param obj
     * @return
     * @Author guyj
     * @date 2015年11月10日 下午4:20:41
     */
    protected boolean saveObj(String key, Object obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
        } catch (IOException e) {
            LogUtils.e("spException", e.getMessage());
            return false;
        }
        // 将Product对象转换成byte数组，并将其进行base64编码
        strBase64 = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
        // 将编码后的字符串写到xml文件中
        editor.putString(key, strBase64);
        editor.commit();
        if (oos != null) {
            try {
                oos.close();
            } catch (IOException e) {
                LogUtils.e("spException", e.getMessage());
            }
        }
        return true;
    }

    /**
     * @param key
     * @return
     * @Author guyj
     * @date 2015年11月10日 下午4:45:00
     */
    protected Object loadObj(String key) {
        Object obj = null;
        strBase64 = preferences.getString(key, "");
        if (TextUtils.isEmpty(strBase64)) {
            return "";
        }
        // 对Base64格式的字符串进行解码
        base64Bytes = Base64.decode(strBase64.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(bais);
            // 从ObjectInputStream中读取Product对象
            obj = ois.readObject();
        } catch (StreamCorruptedException e) {
            LogUtils.e("spException", e.getMessage());
        } catch (IOException e) {
            LogUtils.e("spException", e.getMessage());
        } catch (ClassNotFoundException e) {
            LogUtils.e("spException", e.getMessage());
        }
        if (ois != null) {
            try {
                ois.close();
            } catch (IOException e) {
                LogUtils.e("spException", e.getMessage());
            }
        }
        return obj;
    }

    /**
     * @param key
     * @param img
     * @return
     * @Author guyj
     * @date 2015年11月11日 下午3:17:33
     */
    protected boolean saveImage(String key, ImageView img) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 将ImageView组件中的图像压缩成JPEG格式，并将压缩结果保存在ByteArrayOutputStream对象中
        ((BitmapDrawable) img.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.PNG, 100, baos);
        strBase64 = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
        // 保存由图像字节流转换成的Base64格式字符串
        editor.putString(key, strBase64);
        editor.commit();
        if (baos != null) {
            try {
                baos.close();
            } catch (IOException e) {
                LogUtils.e("spException", e.getMessage());
            }
        }
        return false;
    }

    /**
     * @param key
     * @return
     * @Author guyj
     * @date 2015年11月11日 下午3:34:06
     */
    protected Bitmap loadImage(String key) {
        strBase64 = preferences.getString(key, "");
        base64Bytes = Base64.decode(strBase64.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
        Bitmap bitmap = BitmapFactory.decodeStream(bais);
//		Drawable drawable = Drawable.createFromStream(bais, name);
//		Drawable drawable = new BitmapDrawable(bitmap);
        if (bais != null) {
            try {
                bais.close();
            } catch (IOException e) {
                LogUtils.e("spException", e.getMessage());
            }
        }
        return bitmap;
    }
}

/*
子类继承base类 实现单例，设置常量KEY，再override父类方法，就可以使用
public class SPUtils extends BaseSharedPreference {



    */
/*静态内部类方式单例，实现了线程安全，又避免了同步带来的性能影响，保证了单例对象的唯一性，同时延迟了单例的实例化。*//*

    private static class Single {
        private static final SPUtils SINGLE = new SPUtils(BaseConfig.application);
    }

    public static SPUtils getInstance() {
        return Single.SINGLE;
    }

    private SPUtils(Context context) {
        super(context);
    }
}*/
