package com.cyj.quartz;

import com.cyj.utils.DateFormatTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QuartzCronDateUtils {
    /***
     * 功能描述：日期转换cron表达式时间格式
     *
     * @param date
     * @param dateFormat
     *            :
     * @return
     */
    public static String formatDateByPattern(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    /***
     * convert Date to cron
     *
     * @param date:时间点
     * @return
     */
    public static String getCron(Date date) {
        String dateFormat = "ss mm HH dd MM ? yyyy";
        return formatDateByPattern(date, dateFormat);
    }

    public static String getCronByStr(String date) throws ParseException {
        return getCron(DateFormatTest.Str2Date(date));
    }
    public static void main(String[] args) throws ParseException {
        System.out.println(getCronByStr("2022-06-01 09:30:37"));
        System.out.println(new Date());
        System.out.println(getCron(new Date()));
    }

}
