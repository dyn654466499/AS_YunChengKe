package com.yunchengke.app.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类名：DateTimeUtils <br/>
 * 描述：时间格式化工具类
 * 创建时间：2016/01/13 21:44
 *
 * @author hanter
 * @version 1.0
 */
public class DateTimeUtils {

    public static String getIntervalTime(String datetime) {

        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                    java.util.Locale.getDefault());

            Date date = df.parse(datetime);


            long interval = System.currentTimeMillis() - date.getTime();

            if (interval <= 0) {
                return "刚才";
            }

            interval /= 1000;

            if (interval < 60) {
                // 秒
                return interval + "秒前";
            }


            interval /= 60;

            if (interval < 60) {
                return interval + "分前";
            }

            interval /= 60;

            if (interval < 24) {
                return interval + "小时前";
            }

            interval /= 24;

            if (interval < 7) {
                return interval + "天前";
            }

            if ((interval / 7) < 4) {
                return interval / 7 + "周前";
            }

            interval /= 30;

            if (interval < 12) {
                return interval + "月前";
            }

            interval /= 12;

            return interval + "年前";

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

}
