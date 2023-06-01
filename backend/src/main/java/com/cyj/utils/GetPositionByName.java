package com.cyj.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetPositionByName {
    public static void main(String[] args) {
        String lng="119.296784";
        String lat="26.082408";
        //java环境中文传值时，需特别注意字符编码问题
        String httpUrl = "http://api.tianapi.com/geoconvert/index?key=8fdc1508564b6690bf5550f56eb7bf16&lng="+lng+"&lat="+lat;
        String jsonResult = request(httpUrl);
        System.out.println(jsonResult);
//        String url="https://apis.map.qq.com/ws/geocoder/v1/?location=39.984154,116.307490&key=WAPBZ-Y3CK2-UHMUP-CIF2S-7CUSF-P3BEQ&get_poi=1";
//        System.out.println(request(url));
    }


    public static String request(String httpUrl) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
//        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * 处理接口
         */

        return result;
    }
}
