package com.cyj.service.schedule.impl;

import com.cyj.pojo.Scheduletable;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cyj.mapper.ScheduletableMapper;
import com.cyj.service.schedule.ScheduletableService;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cyj
 * @since 2022-05-31
 */
@Service
public class ScheduletableServiceImpl extends ServiceImpl<ScheduletableMapper, Scheduletable> implements ScheduletableService {




    @Override
    public int add(Scheduletable scheduletable){
        return baseMapper.insert(scheduletable);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Scheduletable scheduletable){
        return baseMapper.updateById(scheduletable);
    }

    @Override
    public Scheduletable findById(Long id){
        return  baseMapper.selectById(id);
    }
}
