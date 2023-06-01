package com.cyj.controller;

import com.cyj.annotation.UserLoginToken;
import com.cyj.pojo.Diary;
import com.cyj.service.diary.DiaryService;
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
@Api(tags = {"随记"})
@RestController
@RequestMapping("/diary")
public class DiaryController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private DiaryService diaryService;


    @ApiOperation(value = "查询记录",notes = "id不用填")
    @UserLoginToken
    @PostMapping("/queryAll")
    public JsonObject<Diary> queryAll( ){
        JsonObject<Diary> jsonObject=new JsonObject<>();
        try{
            jsonObject=diaryService.queryDiaryList();
            jsonObject.setCode(Constants.OK_CODE);

        }catch (Exception e){
            e.printStackTrace();
            jsonObject.setCode(Constants.FAIL_CODE);
        }
        return jsonObject;
    }


    @ApiOperation(value = "新增 ",notes = "传content就好")
    @UserLoginToken
    @PostMapping("/add")
    public R add(@RequestBody Diary diary){
        try{
            if(diaryService.add(diary)!=1){
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
            if(diaryService.delete(id)!=1){
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
    public R update(@RequestBody Diary diary){
        try{
            if( diaryService.updateData(diary)!=1){
                return R.fail(Constants.FAIL_MSG);
            }
            return R.ok();
        }catch (Exception e){
            return R.fail(Constants.FAIL_MSG);
        }
    }

}
