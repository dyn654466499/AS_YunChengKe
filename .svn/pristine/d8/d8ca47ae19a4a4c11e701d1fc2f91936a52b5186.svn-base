package com.yunchengke.app.utils;

/**
 * 类名：StringUtil <br/>
 * 描述：TODO
 * 创建时间：2016/01/09 0:07
 *
 * @author hanter
 * @version 1.0
 */
public class StringUtil {

    public static String GbToUnicode(String str) {
        char[] chararray = str.toCharArray();
        return GbToUnicode(chararray);
    }

    public static String GbToUnicode(char[] chararray) {
        StringBuffer unisb = new StringBuffer();
        for(int i = 0; i < chararray.length; i++) {
            int c = (int) chararray[i];
            if(c > 0xff) {
                unisb.append("\\u");
                unisb.append(Integer.toHexString((int) chararray[i]));
            } else {
                unisb.append(chararray[i]);
            }
        }
        return unisb.toString();
    }
}
