package com.cyj.controller.login;

import com.alibaba.fastjson.JSONObject;
import com.cyj.dto.user.UserDto;
import com.cyj.dto.user.UserInfo;
import com.cyj.dto.user.WxUser;
import com.cyj.pojo.User;
import com.cyj.service.token.TokenService;
import com.cyj.service.user.UserService;
import com.cyj.utils.constant.Constants;
import com.cyj.utils.constant.WxConstant;
import com.cyj.utils.result.JsonObject;
import com.cyj.utils.wx.WxUtils;
import com.sun.jersey.core.util.Base64;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.annotations.Param;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Api(tags = {"微信登录注册"})
@RestController
@RequestMapping("/wxuser")
public class WXLoginController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    /**
     * 微信授权时录入用户数据
     */
    @ApiOperation(value = "微信授权登录",notes = "code必填，userinfo要给我nickname用户名，avatarUrl用户头像")
    @GetMapping(value = "wxLogin")
    @Transactional
//    HttpSession session,
    public Map<String,Object> insert(String code,  HttpServletResponse response1, UserInfo wxuser) throws ClientProtocolException, IOException {
        //返回map
        System.err.println("微信授权登录");
        System.err.println("code值： "+code);
        Map<String,Object> resultMap = new HashMap<>();
        String appid = WxConstant.APP_ID; //自己的APPID
        String secret = "1708b0314f18f420d3fe8128652af43c"; //自己小程序的SECRET
        String loginUrl="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
        try {
            CloseableHttpClient client = null;
            CloseableHttpResponse response = null;
            try {
                // 创建http GET请求
                HttpGet httpGet = new HttpGet(loginUrl);
                client = HttpClients.createDefault();
                // 执行请求
                response = client.execute(httpGet);
                HttpEntity entity = response.getEntity();//得到返回数据
                String result = EntityUtils.toString(entity);
                System.err.println("微信返回的结果"+result);
                resultMap.put("data", result);//进行封装
                System.err.println(resultMap);
                JSONObject json_test = JSONObject.parseObject(result);
                String wxOpenid = json_test.getString("openid");
                String sessionKey = json_test.getString("session_key");
                System.err.println("openid值: "+wxOpenid);//得到微信openID
                System.err.println("sessionKey值: "+sessionKey);
                //根据id数据库数据查询
                User user = userService.findUserByWxOpenId((wxOpenid));
                System.err.println("用户信息: "+user);
                if (user == null){//如果user等于null说明该用户第一次登录，数据库没有该用户信息。
                    resultMap.put("state", 200);
//                    注册用户
                    User newUser=new User();
                    newUser.setUsername(wxuser.getNickName());
                    newUser.setAvatar(wxuser.getAvatarUrl());
                    newUser.setWxOpenid( (wxOpenid));
                    userService.addWxUser(newUser);
                    User tokenUser=userService.findUserByWxOpenId(wxOpenid);
                    //获取token
                    String token = tokenService.getToken(tokenUser);
                    Map map=new HashMap();
//                    map.put("token",token);
                    Cookie cookie = new Cookie("token", token);
                    cookie.setPath("/");
                    response1.addCookie(cookie);
                    resultMap.put("token", token);
                    resultMap.put("message", "用户注册成功");
                    return resultMap;
                }else {
                    resultMap.put("state", 200);
//                    resultMap.put("data", wxOpenid);//查找的用户信息进行封装返回
//                    resultMap.put("sessionKey", sessionKey);
                    resultMap.put("user", user);//查找的用户信息进行封装返回
                    resultMap.put("message", "授权登录成功");
                    //获取token
                    String token = tokenService.getToken(user);
                    Map map=new HashMap();
//                    map.put("token",token);
                    Cookie cookie = new Cookie("token", token);
                    cookie.setPath("/");
                    response1.addCookie(cookie);
                    resultMap.put("token", token);
                    return resultMap;
                }
//                if(StringUtils.isEmpty(wxOpenid)){
//                    resultMap.put("state", 201);
//                    resultMap.put("message", "未获取到openid,请注册");
//                    return resultMap;
//                }
            } finally {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
//    /*保存微信用户信息*/
//    @ApiOperation("保存微信用户信息")
//    @PostMapping("/insertWxUser")
//    @ResponseBody
//    public JsonObject<WxUser> insertUser(WxUser user){
//        JsonObject<WxUser> jsonObject=new JsonObject<>();
//        System.out.println("微信用户信息保存");
//        String username = user.getUsername();
//        String avatar = user.getAvatar();
//        String wxOpenid = user.getWxOpenId()+"";
//        System.out.println("用户名: "+username);
//        System.out.println("头像: "+avatar);
//        System.out.println("openid: "+wxOpenid);
//        try {
//
//            User user1 = userService.findUserByWxOpenId( (wxOpenid));//根据id数据库数据查询
//            User newUser=new User();
//            newUser.setUsername(username);
//            newUser.setAvatar(avatar);
//            newUser.setTel(user.getTel());
//            newUser.setWxOpenid( (wxOpenid));
//            if (user1==null) {
//                userService.addWxUser(newUser);
//                jsonObject.setCode(Constants.OK_CODE);
//            }
//            else {
//                UserDto newUser1=new UserDto();
//                newUser1.setUsername(username);
//                newUser1.setAvatar(avatar);
////                newUser1.setWxOpenId( (wxOpenid));
//                userService.updateData(newUser1 );
//                jsonObject.setCode(Constants.OK_CODE);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            jsonObject.setMsg("保存用户信息失败，请重新尝试");
//        }
//        return jsonObject;
//    }
//    @ApiOperation("小程序获取解密手机号")
//    @RequestMapping(value = "/decodePhone", method = RequestMethod.POST)
//    public String decodePhone(String encryptedData,String iv,String sessionKey) {
//        byte[] resByte =
//                AppletsSignUtil.decryptOfDiyIV(Base64.decode(encryptedData),Base64.decode(sessionKey), Base64.decode(iv));
//        return new String(resByte);
//    }

}



