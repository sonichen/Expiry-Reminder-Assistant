package com.cyj.service.others.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyj.mapper.OthersMapper;
import com.cyj.mapper.UserMapper;
import com.cyj.pojo.Others;

import com.cyj.service.others.OthersService;
import com.cyj.utils.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cyj
 * @since 2022-04-20
 */
@Service
public class OtherServiceImpl extends ServiceImpl<OthersMapper, Others> implements OthersService {

    @Autowired
    OthersMapper othersMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public int add(Others others){
        return baseMapper.insert(others);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Others others){
        return baseMapper.updateById(others);
    }

    @Override
    public Others findById(Long id){
        return  baseMapper.selectById(id);
    }

    public  Others findByModuleName(String module){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("module",module);
        Others others=baseMapper.selectOne(wrapper);
        return others;
    }
}
