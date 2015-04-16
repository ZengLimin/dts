/*package com.zenglm.scheduler;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest implements Job {
	@Override
	//该方法实现需要执行的任务
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Generating report - "
				+ arg0.getJobDetail().getKey().getName());
		System.out.println(new Date().toString());
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws SchedulerException,
			ClassNotFoundException {

		//引进作业程序 
		JobDetail jobDetail = JobBuilder
				.newJob((Class<? extends Job>) Class
						.forName("com.zenglm.scheduler.QuartzTest"))
				.withIdentity("myJob", "myJobGroup")
				.usingJobData("type", "FULL").build();
		//创建调度触发器，设置调度策略
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("trigger_1", "group_1")
				.startNow()
				.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
				.build();

		JobDetail jobDetail1 = JobBuilder.newJob(QuartzTest.class)
				.withIdentity("myJob1", "myJobGroup")
				.usingJobData("type", "FULL").build();

		Trigger trigger1 = TriggerBuilder
				.newTrigger()
				.withIdentity("trigger_2", "group_2")
				.startNow()
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(10) //时间间隔
								.withRepeatCount(5) //重复次数(将执行6次)
				).build();

		//通过SchedulerFactory来获取一个调度器  
		Scheduler sched = StdSchedulerFactory.getDefaultScheduler();
		sched.scheduleJob(jobDetail, trigger);
		sched.scheduleJob(jobDetail1, trigger1);
		sched.start();

	}

}
*/