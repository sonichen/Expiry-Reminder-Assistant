//package com.cyj;
//
//
//import cn.hutool.core.util.StrUtil;
//import com.cyj.utils.ObjectData;
//import com.cyj.utils.TipResult;
//import com.cyj.utils.TokenResult;
//import com.cyj.utils.constants.WXPayConstants;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//class DemoApplicationTests {
//
//    @Test
//    void contextLoads() throws JsonProcessingException {
//        TokenResult tokenResult = get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WXPayConstants.APP_ID+"&secret="+ WXPayConstants.SECRET);
//
//        String token = tokenResult.getAccess_token();
//        System.out.println("token="+token);
//        String data = "{\"touser\": \"orgCu5UONbG70W002r_-379xmzkk\",\"template_id\": \"xCeROvtAKfla4ixapztRgKA9xQClByFH4YlvaqJsHME\",\"page\": \"/pages/test/test/test\",\"miniprogram_state\": \"developer\",\"lang\": \"zh_CN\",\"data\": {\"thing1\": {\"value\": \"个人会员\"},\"date2\": {\"value\": \"2015年01月05日\"},\"thing3\": {\"value\": \"您的会员即将到期\"}}}";
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        TipResult tipResult = null;
//        ///
//        HashMap hashMap=new HashMap();
//        hashMap.put("")
//
//
//        ///
//        try {
//            tipResult = post("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + token,map);
////            tipResult = post("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + token,objectMapper.readValue(data, Map.class));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        System.out.println(tipResult);
//    }
//    //post
//    public TipResult post(String url, Map<String,Object> data){
//        System.out.println("data="+data);
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity entity = new HttpEntity(data, headers);
//        TipResult tipResult = restTemplate.postForObject(url, entity,TipResult.class);
//        return tipResult;
//    }
//    //get
//    public TokenResult get(String url){
//        RestTemplate restTemplate = new RestTemplate();
//        TokenResult tokenResult = restTemplate.getForObject(url, TokenResult.class);
//        return tokenResult;
//    }
////    /**
////     * 发送订阅消息
////     *
////     * @param subscribeMsg
////     * @param openid
////     * @return
////     */
////    public static ObjectData<String> sendSubscribeMsg(BaseSubscribeMsg subscribeMsg, String openid) {
//////        logger.info("发送订阅消息: openid: {}, subscribeMsg: {}", openid, subscribeMsg);
////
////        String token = subscribeMsg.getToken();
////        if (StrUtil.isBlank(openid)) {
////            return ObjectData.ok("不明确的接收人");
////        }
////        if (StrUtil.isBlank(token)) {
////            return ObjectData.ok("微信请求异常，请稍后重试");
////        }
////
////        String sendResult = null;
////        try {
////            HashMap<String, Object> requestParam = TaobaoRequest.requestFormParam();
////            requestParam.put("touser", openid);
////            requestParam.put("template_id", subscribeMsg.getTemplatId());
////            requestParam.put("data", subscribeMsg.getData());
////            String page = subscribeMsg.getPage();
////            if (StrUtil.isNotBlank(page)) {
////                requestParam.put("page", page);
////            }
////            sendResult = HttpUtil.post(BaseSubscribeMsg.REQUEST_URL+"?access_token="+token, JSONObject.fromObject(requestParam).toString());
////            JSONObject sendResultJO = JSONObject.fromObject(sendResult);
////            if (sendResultJO == null || !sendResultJO.has("errcode") || !"0".equals(sendResultJO.getString("errcode"))) {
////                logger.warn("发送订阅消息失败" + sendResultJO.toString());
////                return BaseResponse.bad(sendResultJO.toString());
////            }
////        } catch (Exception e) {
////            logger.error("发送订阅消息异常: {}, {}", sendResult, e);
////        }
////        return BaseResponse.ok();
////    }
//
//
//}
