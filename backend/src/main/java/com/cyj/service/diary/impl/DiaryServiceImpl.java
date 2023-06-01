package com.cyj.service.diary.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyj.mapper.DiaryMapper;
import com.cyj.mapper.UserMapper;
import com.cyj.pojo.Diary;
import com.cyj.service.diary.DiaryService;
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
public class DiaryServiceImpl extends ServiceImpl<DiaryMapper, Diary> implements DiaryService {

    @Autowired
    DiaryMapper diaryMapper;
    @Autowired
    UserMapper userMapper;


    @Override
    public JsonObject<Diary> queryDiaryList() {
        JsonObject jsonObject=new JsonObject();
        QueryWrapper<Diary> wrapper=new QueryWrapper<>();
        wrapper.eq("userId",(Long.valueOf(TokenUtil.getTokenUserId())));
        wrapper.orderByDesc("id");
        List<Diary> list=diaryMapper.selectList(wrapper);
        jsonObject.setData(list);
        jsonObject.setCount((long)list.size());
        return jsonObject;
    }

    @Override
    public int add(Diary diary){
        diary.setUserId(Long.valueOf(TokenUtil.getTokenUserId()));
        return baseMapper.insert(diary);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Diary diary){
        return baseMapper.updateById(diary);
    }

    @Override
    public Diary findById(Long id){
        return  baseMapper.selectById(id);
    }
}
