package com.cyj.controller;

import com.cyj.annotation.UserLoginToken;
import com.cyj.dto.CategoriesDto;
import com.cyj.pojo.Categories;
import com.cyj.service.categories.CategoriesService;
import com.cyj.utils.Constants;
import com.cyj.utils.JsonObject;
import com.cyj.utils.ObjectData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Api(tags = {"数据分析模块"})
@RestController
@RequestMapping("/analysis")
public class AnalysisController {
    @Autowired
    CategoriesService categoriesService;
    @ApiOperation(value = "表格数据",notes = "")
    @UserLoginToken
    @PostMapping("/analysis")
    public ObjectData  querySystem( ){
        ObjectData objectData=null;
        try{
            objectData=categoriesService.dataAnalysisInTable();
            objectData.setCode(Constants.OK_CODE);

        }catch (Exception e){
            e.printStackTrace();
            objectData.setCode(Constants.FAIL_CODE);
        }
        return objectData;
    }

    @ApiOperation(value = "饼图数据",notes = "")
    @UserLoginToken
    @PostMapping("/pie")
    public ObjectData  pie( ){
        ObjectData objectData=null;
        try{
            objectData=categoriesService.dataAnalysisInPie();
            objectData.setCode(Constants.OK_CODE);

        }catch (Exception e){
            e.printStackTrace();
            objectData.setCode(Constants.FAIL_CODE);
        }
        return objectData;
    }

}
