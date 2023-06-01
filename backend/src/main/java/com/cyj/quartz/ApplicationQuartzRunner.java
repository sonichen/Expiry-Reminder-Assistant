package com.cyj.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationQuartzRunner implements ApplicationRunner {

    @Autowired
    QuartzJobListener quartzJobListener;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        quartzJobListener.contextInitialized();
        System.out.println("QuartzJobListener 启动了");
    }
}
