package com.cyj.service.advice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyj.mapper.AdviceMapper;
import com.cyj.mapper.UserMapper;
import com.cyj.pojo.Advice;
import com.cyj.service.advice.AdviceService;
import com.cyj.utils.JsonObject;
import com.cyj.utils.TokenUtil;
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
public class AdviceServiceImpl extends ServiceImpl<AdviceMapper, Advice> implements AdviceService {

    @Autowired
    AdviceMapper adviceMapper;
    @Autowired
    UserMapper userMapper;


    @Override
    public JsonObject<Advice> queryAdviceList() {
        JsonObject jsonObject=new JsonObject();
        QueryWrapper<Advice> wrapper=new QueryWrapper<>();
        wrapper.eq("userId",(Long.valueOf(TokenUtil.getTokenUserId())));
        wrapper.orderByDesc("id");
        List<Advice> list=adviceMapper.selectList(wrapper);
        jsonObject.setData(list);
        jsonObject.setCount((long)list.size());
        return jsonObject;
    }

    @Override
    public int add(Advice advice){
        advice.setUserId(Long.valueOf(TokenUtil.getTokenUserId()));
        return baseMapper.insert(advice);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Advice advice){
        return baseMapper.updateById(advice);
    }

    @Override
    public Advice findById(Long id){
        return  baseMapper.selectById(id);
    }
}
