package com.commonlib.utils;

import android.text.TextUtils;

import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具
 * 验证手机号码，银行卡，3-10位数字，邮箱，邮编，昵称，小数点后两位金钱，中文数字英文下划线，密码长度，两次密码是否相同
 * 根据日期获取年龄，集合是否为空，数组是否存在空值
 */

public class TextValidateUtils {
    private static Pattern mPattern;
    /**
     * 手机号码的正则表达式
     */
    private static final String REG_PHONE = "^(\\+)?(86)?0?(13[0-9]|14[0-9]|15[0-9]|18[0-9]|17[0-9]|16[0-9])[0-9]{8}$";
    /**
     * 数字长度16位或者19位，信用卡银行卡
     */
    private static final String REG_BANKCARDNUMBER = "^(\\d{16}|\\d{19})$";
    /**
     * 数字长度3-10位
     */
    public static final String REG_NUM_THREE = "^(\\d{3})$";
    public static final String REG_NUM_FOUR = "^(\\d{4})$";
    public static final String REG_NUM_FIVE = "^(\\d{5})$";
    public static final String REG_NUM_SIX = "^(\\d{6})$";
    public static final String REG_NUM_SEVEN = "^(\\d{7})$";
    public static final String REG_NUM_EIGHT = "^(\\d{8})$";
    public static final String REG_NUM_NINE = "^(\\d{9})$";
    public static final String REG_NUM_TEN = "^(\\d{10})$";
    /**
     * 邮箱正则表达式
     */
    private static final String REG_EMAIL = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
    /**
     * 邮政编码正则表达式
     */
    private static final String REG_POSTALCODE = "[0-9]\\d{5}(?!\\d)";
    /**
     * 昵称汉字数字英文大小写下划线 2-12位数
     */
    private static final String REG_NICKNAME = "^[\u4e00-\u9fa5_0-9A-Za-z]{2,12}$";
    /**
     * 输入金额，小数点后2位
     */
    private static final String REG_MONEY = "^(([0-9]|([1-9][0-9]{0,9}))((\\.[0-9]{1,2})?))$";
    /**
     * 中文，数字，下划线，字母,不限长度
     */
    private static final String REG_CN_NUM_EN = "^\\w+|\u4e00-\u9fa5$";

    /**
     * 验证字符串长度
     *
     * @param num
     * @param REG
     * @return
     */
    public static boolean isNumLength(String num, String REG) {
        if (TextUtils.isEmpty(num)) throw new NumberFormatException("isNumLength传入的字符串为空");
        mPattern = Pattern.compile(REG);
        Matcher matcher = mPattern.matcher(num);
        return matcher.find();
    }

    /**
     * 验证是否银行卡16/19位
     *
     * @param cardNum
     * @return
     */
    public static boolean isCardNum(String cardNum) {
        mPattern = Pattern.compile(REG_BANKCARDNUMBER);
        Matcher matcher = mPattern.matcher(cardNum);
        return matcher.find();
    }

    /**
     * 判断是否是手机号码
     *
     * @param phoneNum
     * @return
     */
    public static boolean isPhoneNum(String phoneNum) {
        mPattern = Pattern.compile(REG_PHONE);
        Matcher matcher = mPattern.matcher(phoneNum);
        return matcher.find();
    }

    /**
     * 验证是否是邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        mPattern = Pattern.compile(REG_EMAIL);
        Matcher matcher = mPattern.matcher(email);
        return matcher.find();
    }

    /**
     * 验证集合是否为空
     *
     * @param pByte
     * @return
     */
    public static boolean isArrayEmpty(List pByte) {
        return pByte == null || pByte.size() == 0;
    }

    /**
     * 作用：数组中是否有空值
     *
     * @return
     */
    public static boolean arrayHasNull(Object[] data) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * 作用：根据生日的时间戳 获取 年龄
     *
     * @param birthDate
     * @return 年龄
     */
    public static int getAge(long birthDate) {
        Calendar calendar = Calendar.getInstance();
        int now = calendar.get(Calendar.YEAR);
        calendar.setTimeInMillis(birthDate);
        return now - calendar.get(Calendar.YEAR);
    }

    /**
     * 作用：是否是 邮政编码
     *
     * @param postalcode
     * @return
     */
    public static boolean isPostalcode(String postalcode) {
        mPattern = Pattern.compile(REG_POSTALCODE);
        Matcher matcher = mPattern.matcher(postalcode);
        return matcher.find();
    }


    /**
     * 作用：验证昵称是否合法(2-12位)
     *
     * @param nickname
     * @return
     */
    public static boolean isNickname(String nickname) {
        mPattern = Pattern.compile(REG_NICKNAME);
        Matcher matcher = mPattern.matcher(nickname);
        return matcher.find();
    }

    /**
     * 作用：验证金钱数字
     *
     * @param money
     * @return
     */
    public static boolean isMoney(String money) {
        mPattern = Pattern.compile(REG_MONEY);
        Matcher matcher = mPattern.matcher(money);
        return matcher.find();
    }

    /**
     * 作用：是否常见字符，中文，字母，数字，下划线
     *
     * @param str
     * @return
     */
    public static boolean isCommonStr(String str) {
        mPattern = Pattern.compile(REG_CN_NUM_EN);
        Matcher matcher = mPattern.matcher(str);
        return matcher.find();
    }


    /**
     * 作用：判断字符串是否为密码
     *
     * @param pwd
     * @return
     */
    public static boolean isPwd(String pwd) {
        if (TextUtils.isEmpty(pwd)) {
            ToastUtils.showToast("密码不能为空");
            return false;
        }
        if ((pwd.length() > 16 || pwd.length() < 6)) {
            ToastUtils.showToast("密码长度为6-16位");
            return false;
        }
        return true;
    }

    /**
     * 作用：检验两次输入的密码是否相同
     *
     * @param pwd1
     * @param pwd2
     * @return
     */
    public static boolean checkPwd(String pwd1, String pwd2) {
        // 第一次密码为空
        if (TextUtils.isEmpty(pwd1)) {
            ToastUtils.showToast("密码不能为空");
            return false;
        }
        // 不符合标准
        if ((pwd1.length() > 16 || pwd1.length() < 6)) {
            ToastUtils.showToast("密码长度为6-16位");
            return false;
        }
        // 第二次输入的密码为空
        if (TextUtils.isEmpty(pwd2)) {
            ToastUtils.showToast("请输入确认密码");
            return false;
        }
        // 两次密码不同
        if (!pwd1.equals(pwd2)) {
            ToastUtils.showToast("两次输入的密码不一致");
            return false;
        }
        return true;
    }

}
