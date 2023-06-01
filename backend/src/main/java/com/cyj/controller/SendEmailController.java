package com.cyj.controller;

import com.cyj.annotation.UserLoginToken;
import com.cyj.pojo.User;
import com.cyj.service.email.EmailServer;
import com.cyj.service.user.UserService;
import com.cyj.utils.R;
import com.cyj.utils.SendMail;
import com.cyj.utils.TokenUtil;
import com.cyj.utils.constant.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@Api(tags = {"数据导出，发送邮件"})
@RestController
@RequestMapping("/send")
public class SendEmailController {
    @Autowired
    EmailServer emailServer;
        @ApiOperation(value = "发送数据到邮箱",notes = "")
        @UserLoginToken
        @PostMapping("/send")
        public R sendEmail(Long categoriesId){
            try{
                emailServer.sendEmail(categoriesId);
                return R.ok();
            }catch (Exception e){
                e.printStackTrace();
                return R.fail(Constants.FAIL_MSG);
            }
        }
}
