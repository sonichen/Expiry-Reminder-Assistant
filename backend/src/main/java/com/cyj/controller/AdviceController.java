package com.cyj.controller;

import com.cyj.annotation.UserLoginToken;
import com.cyj.pojo.Advice;
import com.cyj.service.advice.AdviceService;
import com.cyj.utils.Constants;
import com.cyj.utils.JsonObject;
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
@Api(tags = {"意见与建议"})
@RestController
@RequestMapping("/advice")
public class AdviceController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private AdviceService adviceService;


    @ApiOperation(value = "查询记录",notes = "id不用填")
    @UserLoginToken
    @PostMapping("/queryAll")
    public JsonObject<Advice> queryAll( ){
        JsonObject<Advice> jsonObject=new JsonObject<>();
        try{
           jsonObject=adviceService.queryAdviceList();
            jsonObject.setCode(Constants.OK_CODE);

        }catch (Exception e){
            jsonObject.setCode(Constants.FAIL_CODE);
        }
        return jsonObject;
    }


    @ApiOperation(value = "新增 ",notes = "只要content")
    @UserLoginToken
    @PostMapping("/add")
    public R add(@RequestBody Advice advice){
        try{
            if(adviceService.add(advice)!=1){
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
            if(adviceService.delete(id)!=1){
                return R.fail(Constants.FAIL_MSG);
            }
            return R.ok();
        }catch (Exception e){
            return R.fail(Constants.FAIL_MSG);
        }
    }

//    @ApiOperation(value = "更新",notes = "")
//    @UserLoginToken
//    @PostMapping("/update")
//    public R update(@RequestBody Advice advice){
//        try{
//            if( adviceService.updateData(advice)!=1){
//                return R.fail(Constants.FAIL_MSG);
//            }
//            return R.ok();
//        }catch (Exception e){
//            return R.fail(Constants.FAIL_MSG);
//        }
//    }

}
