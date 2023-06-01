package com.cyj.utils.api;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.cyj.utils.constants.DevelopConstants;

//import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class APIUtils {
    //    public static void main(String[] args) {
//       String value=(getData());
//        Gson gson = new Gson();
//
//        Map map = new HashMap();
//
//        map = gson.fromJson(value, map.getClass());
//
//        System.out.println(map.toString());
//        String data= map.get("data").toString();
//        System.out.println(data );
//        ////////////////////
//        Map map1=new HashMap();
//        map1=gson.fromJson(data,map1.getClass());
//        System.out.println(map1.toString());

//    }
public static void main(String[] args) {
    System.out.println(getGoodsDetails("6902538005141"));
    String mark="JXU4RkQ5JXU5MUNDJXU2NjJGJXU4OTgxJXU1MkEwJXU1QkM2JXU3Njg0JXU1MTg1JXU1QkI5JXVGRjAxaHR0cHMlM0EvL2Jsb2cuY3Nkbi5uZXQvd2VpeGluXzQ1ODIyMTcxL2FydGljbGUvZGV0YWlscy8xMTQ0MDA2ODElM0ZzcG0lM0QxMDAxLjIxMDEuMzAwMS42NjUwLjMlMjZ1dG1fbWVkaXVtJTNEZGlzdHJpYnV0ZS5wY19yZWxldmFudC5ub25lLXRhc2stYmxvZy0yJTI1N0VkZWZhdWx0JTI1N0VDVFJMSVNUJTI1N0VkZWZhdWx0LTMtMTE0NDAwNjgxLWJsb2ctODEwMjI4MzIucGNfcmVsZXZhbnRfZG93bmxvYWRibGFja2xpc3R2MSUyNmRlcHRoXzEtdXRtX3NvdXJjZSUzRGRpc3RyaWJ1dGUucGNfcmVsZXZhbnQubm9uZS10YXNrLWJsb2ctMiUyNTdFZGVmYXVsdCUyNTdFQ1RSTElTVCUyNTdFZGVmYXVsdC0zLTExNDQwMDY4MS1ibG9nLTgxMDIyODMyLnBjX3JlbGV2YW50X2Rvd25sb2FkYmxhY2tsaXN0djElMjZ1dG1fcmVsZXZhbnRfaW5kZXglM0Q1";
    String url="https://www.mxnzp.com/api/shortlink/create?url="+mark+"==&app_id="+ DevelopConstants.APP_ID+"&app_secret="+DevelopConstants.APP_SECRET;
    System.out.println(getData(url));
}
    public static String getVerifyCode(){
        String theUrl = " https://www.mxnzp.com/api/verifycode/code?len=5&type=0&app_id="+ DevelopConstants.APP_ID+"&app_secret="+DevelopConstants.APP_SECRET;
        return getData(theUrl);
    }
    public static String getGoodsDetails(String code){
    String url=" https://www.mxnzp.com/api/barcode/goods/details?barcode="+code+"&app_id="+ DevelopConstants.APP_ID+"&app_secret="+DevelopConstants.APP_SECRET;
    return getData(url);
}
    public static String getData(String theUrl) {
//        String theUrl = "https://www.mxnzp.com/api/ip/self?app_id="+ DevelopConstants.APP_ID +"&app_secret="+DevelopConstants.APP_SECRET ;
        try {
            // 1. 得到访问地址的URL
            URL url = new URL(theUrl);
            // 2. 得到网络访问对象java.net.HttpURLConnection
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            /* 3. 设置请求参数（过期时间，输入、输出流、访问方式），以流的形式进行连接 */
            // 设置是否向HttpURLConnection输出
            connection.setDoOutput(false);
            // 设置是否从httpUrlConnection读入
            connection.setDoInput(true);
            // 设置请求方式
            connection.setRequestMethod("GET");
            // 设置是否使用缓存
            connection.setUseCaches(true);
            // 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向
            connection.setInstanceFollowRedirects(true);
            // 设置超时时间
            connection.setConnectTimeout(3000);
//            设置请求
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)");

            // 连接
            connection.connect();
            // 4. 得到响应状态码的返回值 responseCode

            int code = connection.getResponseCode();
            // 5. 如果返回值正常，数据在网络中是以流的形式得到服务端返回的数据
            String msg = "";
            if (code == 200) { // 正常响应
                // 从流中读取响应信息
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String line = null;

                while ((line = reader.readLine()) != null) { // 循环从流中读取
                    msg += line + "\n";
//                    System.out.println(msg);
                }
                reader.close(); // 关闭流
            }
            // 6. 断开连接，释放资源
            connection.disconnect();
            return msg;
            // 显示响应结果`
//            System.out.println(msg);
        } catch (IOException e) {
            System.out.println("报错");
            e.printStackTrace();
        }
        return null;
    }

    public static String readFileContent(File file) {
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }


    public static String getCityId(String cityName){
//        String cityName="福州";
        File file=new File("src/main/resources/static/json/cityID.json");
        String   json=readFileContent(file);
        List<HashMap> list = JSON.parseArray(json, HashMap.class);
        for (int i=0;i<list.size();i++){
            HashMap<String,String> map=list.get(i);
            String name=(map.get("name"));
            if(name.equals(cityName)){
                return map.get("id");
            }
        }
        return null;
    }
}
