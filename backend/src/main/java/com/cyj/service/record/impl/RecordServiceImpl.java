package com.cyj.service.record.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyj.dto.RecordSearchDto;
import com.cyj.mapper.CategoriesMapper;
import com.cyj.mapper.TaskMapper;
import com.cyj.pojo.Record;
import com.cyj.mapper.RecordMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyj.pojo.Task;
import com.cyj.quartz.QuartzCronDateUtils;
import com.cyj.quartz.QuartzManager;
import com.cyj.service.quartz.TestQuartz;
import com.cyj.service.record.RecordService;
import com.cyj.service.task.TaskService;
import com.cyj.utils.DateFormatTest;
import com.cyj.utils.JsonObject;
import com.cyj.utils.TokenUtil;
import com.cyj.utils.secret.SnowflakeIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cyj
 * @since 2022-05-31
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

    @Autowired
    QuartzManager quartzManager;
    @Autowired
    TaskService taskService;
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    RecordMapper recordMapper;
    @Autowired
    CategoriesMapper categoriesMapper;
    @Override
    public JsonObject<Record> queryRecordList(Long state) {
        String userId= TokenUtil.getTokenUserId();
       List<Record> records= recordMapper.queryRecords(userId,state+"");
       for(int i=0;i<records.size();i++){
           Record record=records.get(i);
           System.out.println("record="+record);
//           未过期
           if(state==0){
               records.get(i).setTimeGap(DateFormatTest.calculateTimeGapOnlyHour(record.getCloseTime(),record.getExpireTime()));
           }else if (state==1){//已经过期
               records.get(i).setTimeGap(DateFormatTest.calculateTimeGapOnlyHour(record.getExpireTime(),DateFormatTest.Date2Str(new Date())));
           }else{//临期
               records.get(i).setTimeGap(DateFormatTest.calculateTimeGapOnlyHour(DateFormatTest.Date2Str(new Date()),record.getExpireTime()));
           }

       }
       JsonObject jsonObject=new JsonObject();
       jsonObject.setData(records);
       jsonObject.setCode(records.size());
       return jsonObject;
    }
    @Override
    public JsonObject<Record> queryRecordListByKeyword(String keywords) {
        String userId= TokenUtil.getTokenUserId();
        List<Record> records= recordMapper.queryRecordsByKeywords(keywords,userId);
        for(int i=0;i<records.size();i++){
            records.get(i).setCategoriesName(categoriesMapper.selectById(records.get(i).getCategoriesId()).getName());
        }
        JsonObject jsonObject=new JsonObject();
        jsonObject.setData(records);
        jsonObject.setCode(records.size());
        return jsonObject;
    }

    public JsonObject<Record> queryAllRecord() {
        String userId= TokenUtil.getTokenUserId();
        List<Record> records= recordMapper.queryAllRecord(userId);
        for(int i=0;i<records.size();i++){
            records.get(i).setCategoriesName(categoriesMapper.selectById(records.get(i).getCategoriesId()).getName());
        }
        JsonObject jsonObject=new JsonObject();

        jsonObject.setData(records);
        jsonObject.setCount((long) records.size());
        return jsonObject;
    }

    @Override
    public int add(Record record) throws ParseException {
        record.setUserId(Long.valueOf(TokenUtil.getTokenUserId()));
        record.setState(0l);
        record.setDeleted(0l);
        //创建任务
//        创建临期提醒
        long n = (long)(Math.random() % 29) + 1;
        long n1 = (long)(Math.random() % 29) + 1;
        String uuid1=((new SnowflakeIdUtils(n,n1).nextId())+"");
        record.setCloseTask(uuid1);
        String cron1= QuartzCronDateUtils.getCronByStr(record.getCloseTime());
        System.out.println("当前时间="+new Date());
        System.out.println("corn="+cron1);
        quartzManager.addJob(uuid1,uuid1,uuid1,uuid1, TestQuartz.class, cron1);
        taskService.add(new Task("close",uuid1,uuid1,uuid1,uuid1,cron1));
//        创建到期提醒
        long n111 = (long)(Math.random() % 29) + 1;
        long n11 = (long)(Math.random() % 29) + 1;
        String uuid2=((new SnowflakeIdUtils(n11,n111).nextId())+"");
        record.setExpireTask(uuid2);
        String cron2= QuartzCronDateUtils.getCronByStr(record.getExpireTime());
        quartzManager.addJob(uuid2,uuid2,uuid2,uuid2, TestQuartz.class, cron2);
        taskService.add(new Task("expire",uuid2,uuid2,uuid2,uuid2,cron2));
        return baseMapper.insert(record);
    }

    @Override
    public int delete(Long id){
        System.out.println("开始删除");
        Record record=baseMapper.selectById(id);
        System.out.println("要删除="+record);
        //删除临期任务
        String closeJob=record.getCloseTask();
        QueryWrapper closeWrapper=new QueryWrapper();
        closeWrapper.eq("module","close");
        closeWrapper.eq("jobName",closeJob);
        taskMapper.delete(closeWrapper);
        quartzManager.removeJob(closeJob,closeJob,closeJob,closeJob);
        System.out.println("临期删除成功");
        //删除最终任务
        String expireJob=record.getExpireTime();
        QueryWrapper expireWrapper=new QueryWrapper();
        expireWrapper.eq("module","expire");
        expireWrapper.eq("jobName",expireJob);
        taskMapper.delete(expireWrapper);
        quartzManager.removeJob(expireJob,expireJob,expireJob,expireJob);
        System.out.println("最终删除成功");
        //删除记录
        recordMapper.deleteById(id);
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Record record) throws ParseException {
        //修改临期任务
        String closeJob=record.getCloseTask();
        QueryWrapper closeWrapper=new QueryWrapper();
        closeWrapper.eq("module","close");
        closeWrapper.eq("jobName",closeJob);
        Task closeTask=taskMapper.selectOne(closeWrapper);
        closeTask.setCorn( QuartzCronDateUtils.getCronByStr(record.getCloseTime()));
        taskMapper.updateById(closeTask);
        quartzManager.modifyJobTime(closeJob,closeJob,closeJob,closeJob,QuartzCronDateUtils.getCronByStr(record.getCloseTime()));

        //删除最终任务
        String expireJob=record.getCloseTask();
        QueryWrapper expireWrapper=new QueryWrapper();
        expireWrapper.eq("module","close");
        expireWrapper.eq("jobName",expireJob);
        Task expireTask=taskMapper.selectOne(expireWrapper);
        expireTask.setCorn( QuartzCronDateUtils.getCronByStr(record.getExpireTime()));
        taskMapper.updateById(expireTask);
        quartzManager.modifyJobTime(expireJob,expireJob,expireJob,expireJob,QuartzCronDateUtils.getCronByStr(record.getExpireTime()));
        //修改记录
        return baseMapper.updateById(record);

    }

    @Override
    public Record findById(Long id){
        return  baseMapper.selectById(id);
    }
}
