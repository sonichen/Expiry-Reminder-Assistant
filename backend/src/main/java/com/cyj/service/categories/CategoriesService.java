package com.cyj.service.categories;

import com.baomidou.mybatisplus.extension.service.IService;

import com.cyj.dto.CategoriesDto;
import com.cyj.pojo.Categories;
import com.cyj.pojo.Categories;
import com.cyj.utils.JsonObject;
import com.cyj.utils.ObjectData;

public interface CategoriesService extends IService<Categories> {

    public ObjectData dataAnalysisInTable();
    public ObjectData dataAnalysisInPie();
    JsonObject<Categories> queryAll( );
    JsonObject<Categories> queryCategoriesList( );
    JsonObject<Categories> querySystemCategoriesList( );
    /**
     * 添加
     *
     * @param categories
     * @return int
     */
    int add(Categories categories);

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改
     *
     * @param categories
     * @return int
     */
    int updateData(Categories categories);

    /**
     * id查询数据
     *
     * @param id id
     * @return Categories
     */
    Categories findById(Long id);
}
