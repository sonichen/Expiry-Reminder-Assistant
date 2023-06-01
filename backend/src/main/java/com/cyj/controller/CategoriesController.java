package com.cyj.controller;

import com.cyj.annotation.UserLoginToken;

import com.cyj.pojo.Categories;

import com.cyj.service.categories.CategoriesService;
import com.cyj.utils.Constants;
import com.cyj.utils.JsonObject;
import com.cyj.utils.R;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
@Api(tags = {"分类模块"})
@RestController
@RequestMapping("/categories")
public class CategoriesController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private CategoriesService categoriesService;

    @ApiOperation(value = "查询全部分类",notes = "下拉框选中的时候用")
    @UserLoginToken
    @PostMapping("/queryAll")
    public JsonObject<Categories> queryAll( ){
        JsonObject<Categories> jsonObject=new JsonObject<>();
        try{
            jsonObject=categoriesService.queryAll();
            jsonObject.setCode(Constants.OK_CODE);

        }catch (Exception e){
            jsonObject.setCode(Constants.FAIL_CODE);
        }
        return jsonObject;
    }

    @ApiOperation(value = "查询默认分类",notes = "")
    @UserLoginToken
    @PostMapping("/querySystem")
    public JsonObject<Categories> querySystem( ){
        JsonObject<Categories> jsonObject=new JsonObject<>();
        try{
            jsonObject=categoriesService.querySystemCategoriesList();
            jsonObject.setCode(Constants.OK_CODE);

        }catch (Exception e){
            jsonObject.setCode(Constants.FAIL_CODE);
        }
        return jsonObject;
    }

    @ApiOperation(value = "查询自定义分类",notes = "")
    @UserLoginToken
    @PostMapping("/queryCustom")
    public JsonObject<Categories> queryCustom( ){
        JsonObject<Categories> jsonObject=new JsonObject<>();
        try{
            jsonObject=categoriesService.queryCategoriesList();
            jsonObject.setCode(Constants.OK_CODE);

        }catch (Exception e){
            jsonObject.setCode(Constants.FAIL_CODE);
        }
        return jsonObject;
    }


    @ApiOperation(value = "新增分类",notes = "填入name【分类名称】")
    @UserLoginToken
    @PostMapping("/add")
    public R add(@RequestBody Categories categories){
        try{
            if(categoriesService.add(categories)!=1){
                return R.fail(Constants.FAIL_MSG);
            }
            return R.ok();
        }catch (Exception e){
            return R.fail(Constants.FAIL_MSG);
        }
    }


    @ApiOperation(value = "删除分类",notes = "id")
    @UserLoginToken
    @ApiImplicitParams({

    })
    @PostMapping("/delete")
    public R delete(long id){
        try{
            if(categoriesService.delete(id)!=1){
                return R.fail(Constants.FAIL_MSG);
            }
            return R.ok();
        }catch (Exception e){
            return R.fail(Constants.FAIL_MSG);
        }
    }

    @ApiOperation(value = "更新分类信息",notes = "填入id,name")
    @UserLoginToken
    @PostMapping("/update")
    public R update(@RequestBody Categories categories){
        try{
            if( categoriesService.updateData(categories)!=1){
                return R.fail(Constants.FAIL_MSG);
            }
            return R.ok();
        }catch (Exception e){
            return R.fail(Constants.FAIL_MSG);
        }
    }

}
