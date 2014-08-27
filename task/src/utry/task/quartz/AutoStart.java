package utry.task.quartz;

import java.util.List;

import javax.annotation.Resource;


import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import utry.task.bo.HttpPOJO;
import utry.task.bo.MongoPOJO;
import utry.task.dao.HttpRequestDao;
import utry.task.dao.ScheduleJobDao;
import utry.task.po.HttpRequest;
import utry.task.po.ScheduleJob;

/**
 * 定时任务作业，随系统启动
 * @author dingxinfa
 * @date 2014-07-23
 */
@Component
public class AutoStart implements InitializingBean{
	
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	
	@Resource
	private ScheduleJobDao scheduleJobDao;
	@Resource
	private HttpRequestDao httpRequestDao;
    /** 日志对象 */
	private static final Logger log = Logger.getLogger(AutoStart.class);
		
	@Autowired
	private HttpPOJO httpPOJO;
	
	@Autowired
	private MongoPOJO mongoPOJO;

	/**
	 * 初始化
	 */

	public void afterPropertiesSet() throws Exception {
		
		log.info("自启动加载定时任务");

		
		//从数据库获取数据
		List<ScheduleJob> jobList =scheduleJobDao.findAll();

		//schedulerFactoryBean 由spring创建注入
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		 
		for (ScheduleJob scheduleJob : jobList) {
			if(scheduleJob.getIsEnable()){
				//从数据库获取数据
				List<HttpRequest> hrList =httpRequestDao.findJobId(scheduleJob.getId());
				TaskUtils.taskEnable(scheduler, scheduleJob, hrList, httpPOJO,mongoPOJO);
			}

		}
	}
}
