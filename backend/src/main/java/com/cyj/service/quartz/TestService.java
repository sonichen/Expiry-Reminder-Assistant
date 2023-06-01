package com.cyj.service.quartz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyj.mapper.RecordMapper;
import com.cyj.mapper.TaskMapper;
import com.cyj.mapper.UserMapper;
import com.cyj.pojo.Record;
import com.cyj.pojo.Task;
import com.cyj.service.task.TaskService;
import com.cyj.utils.wx.SendMsg;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class TestService {
    @Autowired
    TaskMapper taskMapper;

    @Autowired
    RecordMapper recordMapper;
    @Autowired
    UserMapper userMapper;
    //执行任务
    public void Test(JobExecutionContext jobExecutionContext){
        //在数据库找到该任务开始
        String trigger=jobExecutionContext.getTrigger().getJobKey().toString();
        String jobName= (String) trigger.subSequence(0,(int)(trigger.length()/2));
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("jobName",jobName);
        Task task=taskMapper.selectOne(wrapper);
//        设置record的状态
        QueryWrapper queryWrapper=new QueryWrapper();
        System.out.println("这是"+task.getModule()+"任务");
        System.out.println(task.getModule().equals("expire"));
        System.out.println(task.getModule().equals("close"));
        if(task.getModule().equals("close")){
//            queryWrapper.eq("module","close");
            queryWrapper.eq("closeTask",jobName);
            Record record=recordMapper.selectOne(queryWrapper);
            System.out.println("record=close="+record);
            record.setState(2l);
            recordMapper.updateById(record);
            //执行任务
            log.info(record.getImage()+"在"+new Date()+"进行临期提醒");
            String userOpenId=userMapper.selectById(record.getUserId()).getWxOpenid();
            SendMsg.sendMsg(userOpenId,record,task);
        }else if ((task.getModule().equals("expire"))){
//            queryWrapper.eq("module","expire");
            queryWrapper.eq("expireTask",jobName);
            Record record=recordMapper.selectOne(queryWrapper);
            System.out.println("record=expire="+record);
            record.setState(1l);
            recordMapper.updateById(record);
            //任务结束
//            recordMapper.deleteById(record.getId());
            log.info(record.getImage()+"在"+new Date()+"进行最后提醒");
            String userOpenId=userMapper.selectById(record.getUserId()).getWxOpenid();
            SendMsg.sendMsg(userOpenId,record,task);
        }

        //在数据库找到该任务结束
        //任务记录deleted=1,设置为已执行
        taskMapper.deleteById(task.getId());


    }
}

