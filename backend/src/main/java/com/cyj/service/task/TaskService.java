package com.cyj.service.task;

import com.cyj.pojo.Task;
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
public interface TaskService extends IService<Task> {



    /**
     * 添加
     *
     * @param task 
     * @return int
     */
    int add(Task task);

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
     * @param task 
     * @return int
     */
    int updateData(Task task);

    /**
     * id查询数据
     *
     * @param id id
     * @return Task
     */
    Task findById(Long id);
}
