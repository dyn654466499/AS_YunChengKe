package com.yunchengke.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：Tinchy
 * 创建时间：2016/1/13 14:33
 * 描述：
 * 版本：1.0
 */
public class AccountCheckUtil {
    /**
     * 判断手机号是否合法
     *
     * @param num
     * @return
     */
    public static boolean isMobileNum(String num) {
        String str = "^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(num);
        return m.matches();
    }
    /**
     * 判断密码是否包含特殊字符
     *
     * @param num
     * @return
     */
    public static boolean isPasswordOnly(String num) {
        if (num.replaceAll("[\u4e00-\u9fa5]*[a-z]*[A-Z]*\\d*\\s*", "").length() == 0) {
            //如果不包含特殊字符
            return true;
        }
        return false;
    }
    /**
     * 判断验证码
     *
     * @param str
     * @return
     */
    public static boolean isVerfiyCode(String str) {
        Pattern pattern = Pattern.compile("[0-9]{6}");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
