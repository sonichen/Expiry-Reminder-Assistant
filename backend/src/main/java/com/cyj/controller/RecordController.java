package com.cyj.controller;

import com.cyj.annotation.UserLoginToken;
import com.cyj.pojo.Record;
import com.cyj.service.record.RecordService;
import com.cyj.utils.Constants;
import com.cyj.utils.JsonObject;
import com.cyj.utils.R;
import com.cyj.utils.api.APIUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cyj
 * @since 2022-04-18
 */
@CrossOrigin
@Api(tags = {"物品记录"})
@RestController
@RequestMapping("/record")
public class RecordController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private RecordService recordService;
    @ApiOperation(value = "条形码")
    @PostMapping("/getGoodsInfo")
    public String getGoodsInfo(String code){
        return APIUtils.getGoodsDetails(code);
    }

    @ApiOperation(value = "根据状态显示记录",notes = "0-未过期；1-已过期；2-即将过期")
    @UserLoginToken
    @PostMapping("/queryAllByState")
    public JsonObject<Record> queryAllByState(Long state){
        JsonObject<Record> jsonObject=new JsonObject<>();
        try{
            jsonObject=recordService.queryRecordList(state);
            jsonObject.setCode(Constants.OK_CODE);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.setCode(Constants.FAIL_CODE);
        }
        return jsonObject;
    }
    @ApiOperation(value = "查询记录,通过关键字")
    @UserLoginToken
    @PostMapping("/queryAllByKeyword")
    public JsonObject<Record> queryAllByKeyword(String keyword){
        JsonObject<Record> jsonObject=new JsonObject<>();
        try{
            jsonObject=recordService.queryRecordListByKeyword(keyword);
            jsonObject.setCode(Constants.OK_CODE);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.setCode(Constants.FAIL_CODE);
        }
        return jsonObject;
    }
    @ApiOperation(value = "查询全部")
    @UserLoginToken
    @PostMapping("/queryAllRecord")
    public JsonObject<Record> queryAllRecord(){
        JsonObject<Record> jsonObject=new JsonObject<>();
        try{
            jsonObject=recordService.queryAllRecord();
            jsonObject.setCode(Constants.OK_CODE);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.setCode(Constants.FAIL_CODE);
        }
        return jsonObject;
    }



    @ApiOperation(value = "新增 ",notes = "")
    @UserLoginToken
    @PostMapping("/add")
    public R add(@RequestBody Record record){
        System.out.println("增加="+record);
        try{
            if(recordService.add(record)!=1){
                return R.fail(Constants.FAIL_MSG);
            }
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
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
            if(recordService.delete(id)!=1){
                return R.fail(Constants.FAIL_MSG);
            }
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(Constants.FAIL_MSG);
        }
    }

    @ApiOperation(value = "更新",notes = "")
    @UserLoginToken
    @PostMapping("/update")
    public R update(@RequestBody Record record){
        try{
            if( recordService.updateData(record)!=1){
                return R.fail(Constants.FAIL_MSG);
            }
            return R.ok();
        }catch (Exception e){
            return R.fail(Constants.FAIL_MSG);
        }
    }

}
