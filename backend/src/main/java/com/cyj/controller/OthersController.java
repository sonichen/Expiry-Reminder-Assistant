package com.cyj.controller;

import com.cyj.annotation.UserLoginToken;
import com.cyj.pojo.Others;
import com.cyj.service.others.OthersService;
import com.cyj.utils.Constants;
import com.cyj.utils.JsonObject;
import com.cyj.utils.ObjectData;
import com.cyj.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cyj
 * @since 2022-04-18
 */
@CrossOrigin
@Api(tags = {"用户手册和新人指南"})
@RestController
@RequestMapping("/others")
public class OthersController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private OthersService othersService;

    @ApiOperation(value = "根据模块查询",notes = "新人指南：guide；关于我们：about")
    @PostMapping("/findByModule")
    public ObjectData findByModule(@RequestParam String module){
        ObjectData objectData=new ObjectData();
        try{
            objectData.setData(othersService.findByModuleName(module));
             objectData.setMsg(Constants.OK_MSG);
             objectData.setCode(Constants.OK_CODE);
        }catch (Exception e){
            objectData.setMsg(Constants.FAIL_MSG);
            objectData.setCode(Constants.FAIL_CODE);
        }
        return objectData;
    }

    @ApiOperation(value = "新增")
    @UserLoginToken
    @PostMapping("/add")
    public R add(@RequestBody Others others){
        try{
            if(othersService.add(others)!=1){
                return R.fail(Constants.FAIL_MSG);
            }
            return R.ok();
        }catch (Exception e){
            return R.fail(Constants.FAIL_MSG);
        }
    }


    @ApiOperation(value = "删除")
    @UserLoginToken
    @ApiImplicitParams({

    })
    @PostMapping("/delete")
    public R delete(long id){
        try{
            if(othersService.delete(id)!=1){
                return R.fail(Constants.FAIL_MSG);
            }
            return R.ok();
        }catch (Exception e){
            return R.fail(Constants.FAIL_MSG);
        }
    }

    @ApiOperation(value = "更新",notes = "")
    @UserLoginToken
    @PostMapping("/update")
    public R update(@RequestBody Others others){
        try{
            if( othersService.updateData(others)!=1){
                return R.fail(Constants.FAIL_MSG);
            }
            return R.ok();
        }catch (Exception e){
            return R.fail(Constants.FAIL_MSG);
        }
    }

}
