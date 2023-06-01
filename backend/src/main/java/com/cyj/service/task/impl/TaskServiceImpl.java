package com.cyj.service.task.impl;

import com.cyj.pojo.Task;
import com.cyj.mapper.TaskMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyj.service.task.TaskService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cyj
 * @since 2022-05-31
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {


    @Override
    public int add(Task task){
        return baseMapper.insert(task);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Task task){
        return baseMapper.updateById(task);
    }

    @Override
    public Task findById(Long id){
        return  baseMapper.selectById(id);
    }
}
