package com.yhdemo.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取当前系统时间
 * @author wyh
 * @date 2022/11/21 15:16
 */
public class DateUtils {

    /**
     * 获取时间
     * @return 当前时间
     */
    public static Date getPresentTime() {
        return new Date();
    }

    public static Date parseToDate(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String parseToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        return simpleDateFormat.format(date);
    }
}
