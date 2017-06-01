package com.commonlib.entity;

import java.io.Serializable;

/**
 * 作　　者: guyj
 * 修改日期: 2016/12/9
 * 描　　述: 保存SharedPreferences时 使用的实体类
 * 备　　注:
 */
public class SPStringBean implements Serializable {

    public SPStringBean(String str, Long activeTime) {
        this.str = str;
        this.endTime = activeTime + System.currentTimeMillis();
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    private String str;
    private Long endTime;

}
