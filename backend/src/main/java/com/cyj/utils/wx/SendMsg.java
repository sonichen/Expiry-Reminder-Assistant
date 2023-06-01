package com.cyj.utils.wx;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cyj.pojo.Record;
import com.cyj.pojo.Task;
import com.cyj.utils.DateFormatTest;
import com.cyj.utils.constants.WXPayConstants;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

public class SendMsg {
//    public static void main(String[] args) {
//        //1:获取token（接口调用凭证）
//        String token = queryToken();
//        //2:发送订阅消息
//        System.out.println(send(token));;
//    }
public static void main(String[] args) {
    Record record=new Record();
    record.setCloseTime("2022-06-02 15:56:40");
    record.setExpireTime("2022-06-02 15:57:00");
   Task task=new Task();
   task.setModule("expire");
    record.setItemName("足球赛");
    record.setDescription("抢座位");
    System.out.println(sendMsg("oxHuo5Be1wnQMkAyZQr_AFQ1W2x4",record,task));;
//    System.out.println(dealWithStr("shh "));
}

    public static    String  sendMsg(String openId, Record record, Task task){
        //1:获取token（接口调用凭证）
        String token = queryToken();
        //2:发送订阅消息
        String result=(send(token,openId,record,task));;
        return result;
    }

    // 1: 获取 access_token  (2h过期)
    public  static String queryToken(){
        String tokenUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
        tokenUrl = tokenUrl + "&appid=" + "wxf340e62e1f0a39bd" + "&secret=" + "1708b0314f18f420d3fe8128652af43c";
        String result = HttpUtil.get(tokenUrl);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        String token = jsonObject.get("access_token").toString();
        return token;
    }

    public static String send(String token, String openId, Record record,Task task){
        String msgUrl="https://api.weixin.qq.com/cgi-bin/message/subscribe/send";
        msgUrl = msgUrl + "?access_token=" + token;
//        System.out.println("msgUrl="+msgUrl);
        // 设置模板参数
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("touser", openId);                 // 接收方
        paramMap.put("template_id", "CeAvOfonLLCKeyrXDEJbWnxv5yDA6pT5cx1eCGQzHsQ");        // 模板id
        paramMap.put("page","pages/index/index");         // 消息中要跳转的页面
        // 设置data 模板内容
        HashMap<String, Object> data = new HashMap<>();
//        data.put("thing1", formatParam("赛事名称"));
        data.put("thing1", formatParam(dealWithStr(record.getItemName())));//备忘录事件
        data.put("thing3", formatParam(dealWithStr(record.getDescription())));//温馨提示
        if(task.getModule().equals("close")){
            data.put("thing5", formatParam("剩余"+ DateFormatTest.calculateTimeGap(record.getCloseTime(),record.getExpireTime())));//温馨提示
        }else if (task.getModule().equals("expire")){
            data.put("thing5", formatParam("本次提醒任务已结束"));//温馨提示
        }else{
            data.put("thing5", formatParam("感谢您的使用"));//温馨提示
        }
        paramMap.put("data", data);
        // 转json字符串
        String jsonObject = JSONUtil.toJsonStr(paramMap);
        String result= HttpUtil.post(msgUrl, jsonObject);
        return (result);
    }

    public static HashMap<String, Object> formatParam(String value){
        HashMap<String, Object> data = new HashMap<>();
        data.put("value", value);
        return data;
    }
    public static String dealWithStr(String str){
        if(str.length()<=8){
            return str;
        }else{
            return str.substring(0,5)+"...";
        }
    }


}
