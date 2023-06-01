package com.cyj.service.schedule;

import com.cyj.pojo.Scheduletable;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyj
 * @since 2022-05-31
 */
public interface ScheduletableService extends IService<Scheduletable> {


    /**
     * 添加
     *
     * @param scheduletable 
     * @return int
     */
    int add(Scheduletable scheduletable);

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
     * @param scheduletable 
     * @return int
     */
    int updateData(Scheduletable scheduletable);

    /**
     * id查询数据
     *
     * @param id id
     * @return Scheduletable
     */
    Scheduletable findById(Long id);
}
