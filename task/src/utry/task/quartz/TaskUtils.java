package utry.task.quartz;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Component;

import utry.task.bo.HttpPOJO;
import utry.task.bo.MongoPOJO;
import utry.task.po.HttpRequest;
import utry.task.po.ScheduleJob;

/***
 * RAM 计划任务池 相关 处理
 * @author dingxinfa
 * @date 2014-07-29
 *
 */
@Component
public class TaskUtils {

	/***
	 * 任务启用,全新启用，如果存在老的定时作业，必须先删除（这样所有参数才能更新）。 
	 * @param scheduler
	 * @param job
	 * @param hrList
	 * @param httpPOJO
	 * @throws SchedulerException
	 */
	public static void taskEnable(Scheduler scheduler,ScheduleJob job,List<HttpRequest> hrList,HttpPOJO httpPOJO,MongoPOJO mongoPOJO) throws SchedulerException{
		
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getTaskCode());
		
		//获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		
		//存在，则删除原有任务池中任务
		if (null != trigger){
			taskDisable(scheduler,job);
		}
		//创建新的任务
		JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class).withIdentity(job.getTaskCode()).build();

		jobDetail.getJobDataMap().put("scheduleJob", job);
		jobDetail.getJobDataMap().put("httpRequestList", hrList);
		jobDetail.getJobDataMap().put("httpPOJO", httpPOJO);
		jobDetail.getJobDataMap().put("mongoPOJO", mongoPOJO);
		
		//表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());
 
		//按新的cronExpression表达式构建一个新的trigger
		trigger = TriggerBuilder.newTrigger().withIdentity(job.getTaskCode()).withSchedule(scheduleBuilder).build();
 
		scheduler.scheduleJob(jobDetail, trigger);

		
	}
	
	/***
	 * 任务停用
	 * @param scheduler
	 * @param job
	 * @throws SchedulerException
	 */
	public static void taskDisable(Scheduler scheduler,ScheduleJob job) throws SchedulerException{
		JobKey jobKey = JobKey.jobKey(job.getTaskCode());
	    scheduler.deleteJob(jobKey);
	}
	
}
