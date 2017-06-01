package com.commonlib.utils;


import java.text.DecimalFormat;
import java.util.List;

/**
 * String的格式化内容都可以往下添加 记得写注释
 * 银行卡只显示最后四位（**** 1234），
 * 格式化价格显示（1.00），
 * 通过list拼接string字符串，
 * 手机号中间四位隐藏，
 */
public class StringFormatUtils {

    /**
     * 格式化银行卡信息，只显示最后4位，之前的显示*号
     *
     * @param number
     * @return
     */
    public static String formatBankCard(String number) {
        if (number.isEmpty()) return "";
        if (number.length() < 5) throw new NumberFormatException("formatBankCard传入的参数小于5位");
        return "**** " + number.substring(number.length() - 4, number.length());
    }

    /**
     * 格式化价格
     *
     * @param money
     * @return
     */
    public static String doubleToStringMoney(double money) {
        return String.format("%.2f", money);
    }

    /**
     * 作用：不使用 科学计数法来 显示大数
     *
     * @param doubleStr
     * @return
     */
    public static String getBigDouble(double doubleStr) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(doubleStr);
    }


    /**
     * 作用：将String集合 拼接到一起
     *
     * @param strings
     * @return
     */
    public static String linkStrings(List<String> strings, char spliter) {
        if (strings == null || strings.size() <= 0) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strings.size(); i++) {
            sb.append(strings.get(i) + spliter);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


    /**
     * 手机号中间四位隐藏
     *
     * @param tel
     * @return
     */
    public static String phoneNumberHide(String tel) {
        if (tel.length() != 11) throw new NumberFormatException("phoneNumberHide传入的参数不等于11位");
        tel = tel.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return tel;
    }


}
