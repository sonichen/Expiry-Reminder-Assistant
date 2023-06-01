package com.cyj.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * desc: 百度逆地理编码  (通过经纬度获取具体位置)
 *
 * @author qts
 * @date 2021/7/7 0007 下午 4:12
 */
public class BaiduMapGeocode {

    private final static String AK = "自己的密钥";// 百度地图密钥

    public static void main(String[] args) {
        String lng="39.93";//116.42 39.93
        String lat="116.42";
        reverseGeocode(lng,lat);
    }

    /**
     *
     * @param lng 经度
     * @param lat 纬度
     * @return
     */
    public static String reverseGeocode(String lng,String lat){
        String location=lng+","+lat;
        String url="http://api.map.baidu.com/reverse_geocoding/v3/?ak="+AK+"&output=json&coordtype=wgs84ll&location="+location;
        System.out.println(url);
        String res=doGet(url);
        String Addresslocation= JSON.parseObject(res).getJSONObject("result").getString("formatted_address");
        System.out.println(Addresslocation);
        return Addresslocation;
    }

    public static String doGet(String url) {
        //创建一个Http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //创建一个get请求
        HttpGet httpGet = new HttpGet(url);
        //响应模型
        CloseableHttpResponse response = null;
        try {
            //由客户端发送get请求
            response = httpClient.execute(httpGet);
            //从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                return EntityUtils.toString(responseEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
