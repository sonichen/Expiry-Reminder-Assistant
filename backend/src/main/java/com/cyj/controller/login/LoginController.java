//package com.cyj.controller.login;
//
//import com.cyj.annotation.UserLoginToken;
//import com.cyj.dto.user.UserDto;
//import com.cyj.pojo.User;
//import com.cyj.service.token.TokenService;
//import com.cyj.service.user.UserService;
//import com.cyj.utils.constant.Constants;
//import com.cyj.utils.result.ObjectData;
//import com.cyj.utils.result.R;
//import com.cyj.utils.secret.Encrypt;
//import com.cyj.utils.secret.MobileUtil;
//import com.cyj.utils.secret.TokenUtil;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletResponse;
//import java.util.HashMap;
//import java.util.Map;
//
//@CrossOrigin
//@Api(tags = {"登录注册（可使用）"})
//@RestController
//@RequestMapping("/user")
//public class LoginController {
//    @Autowired
//    UserService userService;
//    @Autowired
//    TokenService tokenService;
//
//    /**
//     * 登录
//     * @param tel
//     * @param password
//     * @param response
//     * @return
//     */
//    @ApiOperation(value = "登陆")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "tel", value = "手机号码"),
//            @ApiImplicitParam(name = "password", value = "密码"),
//    })
//    @RequestMapping(value = "/login" ,method = RequestMethod.GET)
//    public Object login(String tel,String password, HttpServletResponse response) {
//        ObjectData objectData=new ObjectData();
//      try{
//
//          User userForBase=userService.queryUserByTelAndPassword(tel, Encrypt.md5AndSha(password));
//          System.out.println("check==="+userForBase);
//          if(userForBase==null){
//              objectData.setData(Constants.searchNullUser);
//              objectData.setCode(Constants.FAIL_CODE);
//          } else {
//              String token = tokenService.getToken(userForBase);
//              Map map=new HashMap();
//              map.put("token",token);
//              Cookie cookie = new Cookie("token", token);
//              cookie.setPath("/");
//              response.addCookie(cookie);
//              /**
//               * 存入用户
//               */
//              UserDto returnUser=userService.findById(userForBase.getId());
//              map.put("user",returnUser);
//              objectData.setCode(Constants.OK_CODE);
//              objectData.setData(map);
//              objectData.setMsg(Constants.OK_MSG);
//
//          }
//
//      }catch (Exception e){
//          e.printStackTrace();
//          objectData.setCode(Constants.FAIL_CODE);
//
//      }
//        return objectData;
//    }
//
//    @UserLoginToken
//    @ApiOperation(value = "测试用token是否能获取信息")
//    @RequestMapping(value = "/getMessage" ,method = RequestMethod.GET)
//    public String getMessage() {
//        // 取出token中带的用户id 进行操作
//        System.out.println(TokenUtil.getTokenUserId());
//        return "您已经通过验证";
//    }
//    @ApiOperation("注册")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "tel", value = "手机号码"),
//            @ApiImplicitParam(name = "password", value = "密码"),
//    })
//    @PostMapping("/register")
//    public R register(String tel, String password){
//        System.out.println(tel);
//        System.out.println(MobileUtil.checkPhone(tel));
//        if(!MobileUtil.checkPhone(tel)){
//            return R.fail("手机号码格式不正确");
//        }
//        try{
//            User user=new User(tel,Encrypt.md5AndSha(password));
//            userService.add(user);
//            return R.ok();
//        }catch (Exception e){
//            return R.fail(e.getMessage());
//        }
//    }
//
//}
