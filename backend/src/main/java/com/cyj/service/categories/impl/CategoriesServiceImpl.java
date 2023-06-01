package com.cyj.service. categories.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cyj.dto.CategoriesDto;
import com.cyj.mapper.CategoriesMapper;
import com.cyj.mapper.CategoriesMapper;
import com.cyj.mapper.RecordMapper;
import com.cyj.mapper.UserMapper;
import com.cyj.pojo.Categories;
import com.cyj.service.categories.CategoriesService;
import com.cyj.service.categories.CategoriesService;
import com.cyj.utils.JsonObject;
import com.cyj.utils.ObjectData;
import com.cyj.utils.TokenUtil;
import com.cyj.utils.number.NumberTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cyj
 * @since 2022-04-20
 */
@Service
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper, Categories> implements CategoriesService {

    @Autowired
    CategoriesMapper categoriesMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RecordMapper recordMapper;
    public JsonObject<Categories> queryAll( ){
        JsonObject jsonObject=new JsonObject();
        QueryWrapper<Categories> wrapper=new QueryWrapper<>();
        wrapper.eq("userId",TokenUtil.getTokenUserId());
        wrapper.orderByDesc("id");
        List<Categories> list=categoriesMapper.selectList(wrapper);
        jsonObject.setData(list);
        jsonObject.setCount((long)list.size());
        return jsonObject;
    }
   public JsonObject<Categories> querySystemCategoriesList( ){
        JsonObject jsonObject=new JsonObject();
        QueryWrapper<Categories> wrapper=new QueryWrapper<>();
        wrapper.eq("userId",TokenUtil.getTokenUserId());
        wrapper.eq("description","1");
       wrapper.orderByDesc("id");
        List<Categories> list=categoriesMapper.selectList(wrapper);
        jsonObject.setData(list);
        jsonObject.setCount((long)list.size());
        return jsonObject;
    }

    @Override
    public JsonObject<Categories> queryCategoriesList() {
        JsonObject jsonObject=new JsonObject();
        QueryWrapper<Categories> wrapper=new QueryWrapper<>();
        wrapper.eq("userId",TokenUtil.getTokenUserId());
        wrapper.eq("image","custom");
        wrapper.orderByDesc("id");
        List<Categories> list=categoriesMapper.selectList(wrapper);
        jsonObject.setData(list);
        jsonObject.setCount((long)list.size());
        return jsonObject;
    }

    @Override
    public int add(Categories categories){
       categories.setImage("custom");
        categories.setUserId(Long.valueOf(TokenUtil.getTokenUserId()));
        return baseMapper.insert(categories);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Categories categories){
        return baseMapper.updateById(categories);
    }

    @Override
    public Categories findById(Long id){
        return  baseMapper.selectById(id);
    }

    //数据分析
    public ObjectData  dataAnalysisInTable(){
       ObjectData objectData=new ObjectData();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("userId",TokenUtil.getTokenUserId());
        List<Categories> categoriesList=baseMapper.selectList(wrapper);
        List<CategoriesDto> categoriesDtos=new ArrayList<>();
        CategoriesDto[] arr=new CategoriesDto[categoriesList.size()];
        System.out.println("categoriesList="+categoriesList);
        for(int i=0;i<categoriesList.size();i++){
            if(recordMapper.queryCountsByCategoriesId(categoriesList.get(i).getId()).intValue()!=0){
                arr[i]=(new CategoriesDto(categoriesList.get(i).getName(),
                        recordMapper.queryCountsByCategoriesId(categoriesList.get(i).getId()).intValue())
                );
                categoriesDtos.add(new CategoriesDto(categoriesList.get(i).getName(),
                        recordMapper.queryCountsByCategoriesId(categoriesList.get(i).getId()).intValue()));
            }

        }
        Collections.sort(categoriesDtos);
        System.out.println("========="+categoriesDtos);
        objectData.setData(categoriesDtos);
        return objectData;
    }
    //数据分析
    public ObjectData  dataAnalysisInPie(){
        ObjectData objectData=new ObjectData();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("userId",TokenUtil.getTokenUserId());
        List<Categories> categoriesList=baseMapper.selectList(wrapper);
        List<CategoriesDto> categoriesDtos=new ArrayList<>();
        List resultList=new ArrayList();
        CategoriesDto []arr=new CategoriesDto[categoriesList.size()];
        int total=0;
        for(int i=0;i<categoriesList.size();i++){
//            recordMapper.queryCountsByCategoriesId(categoriesList.get(i).getId());
            categoriesDtos.add(new CategoriesDto(categoriesList.get(i).getName(),
                     recordMapper.queryCountsByCategoriesId(categoriesList.get(i).getId()).intValue()));
            total++;
        }
        System.out.println("列表="+categoriesDtos);
        System.out.println("total="+total);
        for(int i=0;i<categoriesList.size();i++){

            Double doubleValueObject = new Double((100*((double) (categoriesDtos.get(i).getValue())/(double) total)));
            int result=doubleValueObject.intValue();
            if(result!=0){
                arr[i]=(new CategoriesDto(categoriesList.get(i).getName(),
                        result));
                resultList.add(new CategoriesDto(categoriesList.get(i).getName(),
                        result));
            }

        }
        System.out.println("arr="+Arrays.toString(arr));
        objectData.setData(resultList);
        return objectData;
    }
}
