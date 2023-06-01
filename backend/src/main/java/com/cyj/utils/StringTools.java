package com.cyj.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringTools {
    public static void main(String[] args) {
        Date currentTime = new Date();//当前日期
        System.out.println("当前日期默认格式：" + currentTime);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义新的日期格式
        //format():将给定的 Date 格式化为日期/时间字符串。即：date--->String
        String dateString = formatter.format(currentTime);
        System.out.println("转换为String格式：" + dateString);
        try {
            Date date = formatter.parse(dateString);//parse():String--->date
            System.out.println("转化为date格式：" + date);
            System.out.println("--" + date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2022-04-27 08:20:11变2022年04月27日08:20:11
     *
     * @param value
     * @return
     */
    public static String changeDateFormat(Date myDate) {
        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String strDate4 = sdf4.format(myDate);
        System.out.println("===" + strDate4);
        return strDate4;
//        Date currentTime = time;//当前日期
//        Date date=null;
//        System.out.println("当前日期默认格式：" + currentTime);
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义新的日期格式
//        //format():将给定的 Date 格式化为日期/时间字符串。即：date--->String
//        String dateString = formatter.format(currentTime);
//        System.out.println("转换为String格式：" + dateString);
//        try {
//             date = formatter.parse(dateString);//parse():String--->date
//            System.out.println("转化为date格式：" + date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        String value=date.toString();
//        System.out.println("检测="+value);
////        String value="2022-04-27 08:20:11";
//        String [] arr=value.split("-");
//        String temp=arr[0]+"年"+arr[1]+"月"+arr[2];
//        String [] arr2=temp.split(" ");
//        String result=arr2[0]+"日" + arr2[1];
////        System.out.println(result);
//        return result;
    }

    //根据前端需求改的
    public static long changeIndex(long categoriesId) {
        if (categoriesId == 10l) {
            return 0l;
        } else if (categoriesId == 11l) {
            return 1;
        } else if (categoriesId == 12l) {
            return 2;
        } else {
            return 3;
        }
    }

}
