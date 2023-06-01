package com.cyj.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatTest {
    /**
     * 字符串转date
     * @param time
     * @return
     * @throws ParseException
     */
    public static Date Str2Date(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
    public static String Date2Str(Date  date){

        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate3 = sdf3.format(date);
        return strDate3;
    }
    /**
     * 计算两天的时间差
     * @param dateStart
     * @param dateStop
     * @return
     */
    public static String calculateTimeGap(String dateStart,String dateStop){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
            long diff = d2.getTime() - d1.getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            String result=diffDays+"天"+diffHours+"小时"+diffMinutes+"分钟"+diffSeconds+"秒";
            return result;
//            System.out.print("两个时间相差：");
//            System.out.print(diffDays + " 天, ");
//            System.out.print(diffHours + " 小时, ");
//            System.out.print(diffMinutes + " 分钟, ");
//            System.out.print(diffSeconds + " 秒.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "临期提醒";
    }
    /**
     * 计算两天的时间差
     * @param dateStart
     * @param dateStop
     * @return
     */
    public static String calculateTimeGapOnlyHour(String dateStart,String dateStop){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
            long diff = d2.getTime() - d1.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            return diffDays+"";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }
    public static void main(String[] args) throws ParseException {
        String dateStart = "2013-02-19 09:29:58";

        String dateStop = "2013-02-20 11:31:48";
        System.out.println(Date2Str(new Date()));
        System.out.println(calculateTimeGapOnlyHour(dateStart,dateStop));

    }

}