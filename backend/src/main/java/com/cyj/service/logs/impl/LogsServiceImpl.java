package com.cyj.service.logs.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyj.dto.LogsDto;
import com.cyj.mapper.LogsMapper;
import com.cyj.mapper.UserMapper;
import com.cyj.pojo.Logs;
import com.cyj.pojo.User;
import com.cyj.service.logs.LogsService;
import com.cyj.utils.JsonObject;
import com.cyj.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class LogsServiceImpl extends ServiceImpl<LogsMapper, Logs> implements LogsService {

    @Autowired
    LogsMapper logsMapper;
    @Autowired
    UserMapper userMapper;
    /**
     * 标签列表
     * @param
     * @return
     */
    @Override
    public JsonObject<LogsDto> queryLogsList() {

        List<Logs> dtoList= logsMapper.queryLogs(Long.valueOf(TokenUtil.getTokenUserId()));
        List<LogsDto> list=new ArrayList<>();
        System.out.println(dtoList);
        System.out.println("长度="+dtoList.size());
        for(int i=0;i<dtoList.size();i++){
            Logs temp=dtoList.get(i);
            System.out.println(temp);
            list.add(new LogsDto(temp.getAction(),temp.getIp(),temp.getCreateTime()));
//          list.get(i).setAction(dtoList.get(i).getAction());
//          list.get(i).setIp(dtoList.get(i).getIp());
//          list.get(i).setCreateTime(dtoList.get(i).getCreateTime());
        }
        JsonObject<LogsDto> jsonObject =new JsonObject<>();
        jsonObject.setData(list);
        jsonObject.setCount((long)dtoList.size());
        return jsonObject;
    }

    @Override
    public int add(Logs logs){
        return baseMapper.insert(logs);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Logs logs){
        return baseMapper.updateById(logs);
    }

    @Override
    public Logs findById(Long id){
        return  baseMapper.selectById(id);
    }
}
