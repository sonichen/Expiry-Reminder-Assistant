package com.cyj.service.quartz;

import com.cyj.config.SpringApplicationUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestQuartz implements Job {
    //获取TestService类
    TestService testService = SpringApplicationUtils.getBean(TestService.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        System.out.println("jobExecutionContext="+jobExecutionContext);
        testService.Test(jobExecutionContext);
    }
}

