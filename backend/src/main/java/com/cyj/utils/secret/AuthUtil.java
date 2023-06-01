//package com.cyj.utils.secret;
//
//
//import net.sf.json.JSONObject;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.util.EntityUtils;
//
//import java.io.IOException;
//
//public class AuthUtil {
//    public static final String APPID = "wx63da592a554cc82b";
//    public static final String APPSECRET = "94f391d306875101822ffa1b2c3cff09";
//
//    //回调地址
//    public static final String backUrl="http://8.130.8.164:1234/";//如果你没有在application.yml文件中设置 context-path: /api，那么api就去掉
//    //    public static final String backUrl="http://localhost:8080/callBack";
//    public static JSONObject doGetJson(String url) throws ClientProtocolException, IOException {
//        JSONObject jsonObject = null;
//
//        // 创建HttpClient实例
//        HttpClient client =  HttpClientBuilder.create().build();
//        // 根据URL创建HttpGet实例
//        HttpGet get = new HttpGet(url);
//        // 执行get请求，得到返回体
//        HttpResponse response = client.execute(get);
//        System.out.println(response);
//        //从response里面拿自己想要的结果
//        HttpEntity entity = response.getEntity();
//        if(entity != null){
//            String result = EntityUtils.toString((HttpEntity) entity,"UTF-8");
//            jsonObject = jsonObject.fromObject(result);
//        }
//        //把链接释放掉
////        HttpGet.releaseConnection();
//        return jsonObject;
//    }
//}
//
//
