package utry.task.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

import utry.task.controller.vo.ScheduleJobVo;
import utry.task.quartz.QuartzJobFactory;

/***
 * 运行任务控制层 (作废)
 * @author dingxinfa
 * @date 2014-07-22
 */
@Controller
@RequestMapping("/quartz")
public class QuartzController {
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	@RequestMapping(value = "/quartz")
	public ModelAndView list(HttpSession session) throws SchedulerException {
		System.out.println("quartz/quartzs");
		ModelAndView mv = new ModelAndView("quartz/quartzs");

		Scheduler scheduler = schedulerFactoryBean.getScheduler();

		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<ScheduleJobVo> jobList = new ArrayList<ScheduleJobVo>();
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				ScheduleJobVo job = new ScheduleJobVo();
				job.setTaskName(jobKey.getName());
				//job.setGroupName(jobKey.getGroup());
				job.setMemo("触发器:" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setStatus(triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCron(cronExpression);
				}
				jobList.add(job);
			}
		}
		mv.addObject("allJobs", jobList);

		return mv;
	}

	// 链接到add页面时是GET请求，会访问这段代码
	@RequestMapping(value = "/add")
	public String add(@ModelAttribute("job") ScheduleJobVo job) {
		return "add";
	}

	// 在具体添加用户时，是post请求，就访问以下代码
	@RequestMapping(value = "/adds")
	public String adds(ScheduleJobVo job) throws IOException, SchedulerException {// 一定要紧跟Validate之后写验证结果类
		String seconds = job.getCron();
		String cronExp = "0/" + seconds + " * * * * ?";
		job.setCron(cronExp);
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getTaskCode());

		// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		// 不存在，创建一个
		if (null == trigger) {
			JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
					.withIdentity(job.getTaskCode()).build();
			jobDetail.getJobDataMap().put("scheduleJob", job);

			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());

			// 按新的cronExpression表达式构建一个新的trigger
			trigger = TriggerBuilder.newTrigger().withIdentity(job.getTaskCode())
					.withSchedule(scheduleBuilder).build();

			scheduler.scheduleJob(jobDetail, trigger);
		} else {
			// Trigger已存在，那么更新相应的定时设置
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}

		return "redirect:/quartz/quartzs";
	}

	@RequestMapping(value = "/{jobGroup}/{jobName}/stop")
	public String stop(@PathVariable String jobGroup, @PathVariable String jobName) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		scheduler.pauseJob(jobKey);

		return "redirect:/quartz/quartzs";
	}

	@RequestMapping(value = "/{jobGroup}/{jobName}/reStart")
	public String reStart(@PathVariable String jobGroup, @PathVariable String jobName) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		scheduler.resumeJob(jobKey);

		return "redirect:/quartz/quartzs";
	}
	
	
	@RequestMapping(value = "/{jobGroup}/{jobName}/startNow")
	public String startNow(@PathVariable String jobGroup, @PathVariable String jobName) throws SchedulerException {

	    Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
	    scheduler.triggerJob(jobKey);


		return "redirect:/quartz/quartzs";
	}
	
	
	@RequestMapping(value = "/{jobGroup}/{jobName}/del")
	public String del(@PathVariable String jobGroup, @PathVariable String jobName) throws SchedulerException {
		
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
	    scheduler.deleteJob(jobKey);
		
		return "redirect:/quartz/quartzs";
	}
	
	@RequestMapping(value = "/{jobGroup}/{jobName}/oneSecond")
	public String oneSecond(@PathVariable String jobGroup, @PathVariable String jobName) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
 	     
	    TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
	     
	    //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
	    CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
	     
	    //表达式调度构建器
	    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");
	     
	    //按新的cronExpression表达式重新构建trigger
	    trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
	    .withSchedule(scheduleBuilder).build();
	     
	    //按新的trigger重新设置job执行
	    scheduler.rescheduleJob(triggerKey, trigger);

		return "redirect:/quartz/quartzs";
	}
	
	@RequestMapping(value = "/{jobGroup}/{jobName}/fiveSeconds")
	public String fiveSeconds(@PathVariable String jobGroup, @PathVariable String jobName) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
	     
	    TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
	     
	    //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
	    CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
	     
	    //表达式调度构建器
	    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
	     
	    //按新的cronExpression表达式重新构建trigger
	    trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
	    .withSchedule(scheduleBuilder).build();
	     
	    //按新的trigger重新设置job执行
	    scheduler.rescheduleJob(triggerKey, trigger);

		return "redirect:/quartz/quartzs";
	}
	

}
