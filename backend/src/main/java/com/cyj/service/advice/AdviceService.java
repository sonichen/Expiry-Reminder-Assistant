package com.cyj.service.advice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cyj.pojo.Advice;
import com.cyj.utils.JsonObject;

public interface AdviceService extends IService<Advice> {


    JsonObject<Advice> queryAdviceList( );

    /**
     * 添加
     *
     * @param advice
     * @return int
     */
    int add(Advice advice);

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
     * @param advice
     * @return int
     */
    int updateData(Advice advice);

    /**
     * id查询数据
     *
     * @param id id
     * @return Advice
     */
    Advice findById(Long id);
}
