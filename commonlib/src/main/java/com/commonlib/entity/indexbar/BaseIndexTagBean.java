package com.commonlib.entity.indexbar;

/**
 * 介绍：索引类的标志位的实体基类
 */

public class BaseIndexTagBean {
    private String baseIndexTag;//所属的分类（城市的汉语拼音首字母）

    public String getBaseIndexTag() {
        return baseIndexTag;
    }

    public void setBaseIndexTag(String baseIndexTag) {
        this.baseIndexTag = baseIndexTag;
    }
}
