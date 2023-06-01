package com.cyj.quartz;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyj.mapper.ScheduletableMapper;
import com.cyj.mapper.TaskMapper;
import com.cyj.pojo.Scheduletable;
import com.cyj.pojo.Task;
import com.cyj.service.quartz.TestQuartz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuartzJobListener {

    @Autowired
    QuartzManager quartzManager;
    @Autowired
    ScheduletableMapper scheduletableMapper;

    @Autowired
    TaskMapper taskMapper;
    public void contextInitialized() {
        //此处模拟从数据库中获取的数据所得到的list
        List<Map<String,Object>> listMap=new ArrayList<>();
        //数据库去读数据
        List<Task> corns=taskMapper.selectList(null);
        System.out.println(corns);
        for(int i = 0; i <corns.size(); i++){
            Task task=corns.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("jobName",task.getJobName());
            map.put("jobGroupName",task.getJobGroupName());
            map.put("triggerName",task.getTriggerName());
            map.put("triggerGroupName",task.getTriggerGroupName());
            map.put("jobTime",task.getCorn());
            listMap.add(map);
        }
        System.out.println("任务列表="+listMap);
        for (Map<String, Object> oneMap : listMap) {
            try {
                quartzManager.addJob(oneMap.get("jobName").toString(),oneMap.get("jobGroupName").toString(), oneMap.get("triggerName").toString(), oneMap.get("triggerGroupName").toString(), TestQuartz.class, oneMap.get("jobTime").toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
