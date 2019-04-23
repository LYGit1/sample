package com.springboot.sample.schedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 配置定时任务
 */
@Component
@Configuration
@EnableScheduling
public class ScheduleTask {

    /**
     * 定时任务
     * 配置上一次任务结束时间和下一次任务开始时间的间隔
     */
//    @Scheduled(fixedDelay = 3000)
//    public void delayTask(){
//        System.out.println("delay schedule task,time="+ LocalDateTime.now());
//    }

    /**
     * 配置上一次任务开始时间和下一次任务开始时间的间隔
     */
//    @Scheduled(fixedRate = 4000)
//    public void rateTask(){
//        System.out.println("rate schedule task,time="+ LocalDateTime.now());
//    }

    /**
     * 配置上一次任务开始时间和下一次任务开始时间的间隔，如果到下一次任务开始时间，但是上一次任务未完成，则跳过本次执行
     */
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void cronTask(){
//        System.out.println("cron schedule task,time="+ LocalDateTime.now());
//    }
}
