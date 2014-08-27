package utry.task.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import utry.task.bo.HttpPOJO;
import utry.task.bo.JobDTO;
import utry.task.bo.MongoPOJO;
import utry.task.controller.vo.ScheduleJobVo;
import utry.task.dao.DataDao;
import utry.task.dao.HttpRequestDao;
import utry.task.dao.ScheduleJobDao;
import utry.task.po.Data;
import utry.task.po.HttpRequest;
import utry.task.po.ScheduleJob;
import utry.task.quartz.TaskUtils;
import utry.task.service.ScheduleJobService;

@Service
@Transactional
public class ScheduleJobServiceImpl implements ScheduleJobService {

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	
	@Resource
	private ScheduleJobDao scheduleJobDao;
	
	@Resource
	private HttpRequestDao httpRequestDao;

	@Resource
	private DataDao dataDao;
	
	
	@Autowired
	private HttpPOJO httpPOJO;

	@Autowired
	private MongoPOJO mongoPOJO;



	
	
	public List<ScheduleJobVo> pagedSearchJobList(JobDTO jobDTO) throws Exception {
		
		List<ScheduleJobVo> sjvList=scheduleJobDao.pagedSearchList(jobDTO);
		
		Scheduler scheduler = schedulerFactoryBean.getScheduler();

		for(int i=0;i<sjvList.size();i++){
			TriggerKey triggerKey = TriggerKey.triggerKey(sjvList.get(i).getTaskCode());
			Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
			if(triggerState!=null){
				sjvList.get(i).setStatus(triggerState.name());
			}
			
		}
		 return sjvList;

	}

	public Integer pagedSearchJobCount(JobDTO jobDTO) throws Exception {
		 return scheduleJobDao.pagedSearchCount(jobDTO);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map updateJobStatus(Boolean isEnable, Integer jobId) throws Exception {
		
		Map map=new HashMap<String, Object>();
		Integer resultCode=0;
		String resultInfo="";
		
		ScheduleJob scheduleJob=scheduleJobDao.findId(jobId);
	    scheduleJob.setIsEnable(isEnable);
	    scheduleJobDao.update(scheduleJob);
		
		ScheduleJobVo job=new ScheduleJobVo(scheduleJob);

		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getTaskCode());

		if(job.getIsEnable()){//启用
			
			List<HttpRequest> hrList =httpRequestDao.findJobId(job.getId());
			TaskUtils.taskEnable(scheduler, scheduleJob, hrList, httpPOJO,mongoPOJO);
			
		    resultInfo="计划任务【"+job.getTaskName()+"】启用成功！";

		}else{//停用

			TaskUtils.taskDisable(scheduler, scheduleJob);
		    resultInfo="计划任务【"+job.getTaskName()+"】停用成功！";
		}
		
		Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
		job.setStatus(triggerState.name());	
		


	    resultCode=1;
	    
	    
		map.put("resultCode", resultCode);
		map.put("resultInfo",resultInfo);
		map.put("job", job);
		return map;
	}

	public void executeJob(Integer jobId) throws Exception {
		
		ScheduleJob job=scheduleJobDao.findId(jobId);
		if(job.getIsEnable()){
		    Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(job.getTaskCode());
		    scheduler.triggerJob(jobKey);
		}

	}

	


	public void deleteJob(Integer jobId) throws Exception {
		
		ScheduleJob scheduleJob=scheduleJobDao.findId(jobId);
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		
		//停用 计划任务池内任务
		TaskUtils.taskDisable(scheduler, scheduleJob);

		List<HttpRequest> hrList =httpRequestDao.findJobId(scheduleJob.getId());
		for(HttpRequest hr:hrList){
			httpRequestDao.delete(hr.getId());
		}
		scheduleJobDao.delete(jobId);
		
	}


	public ModelAndView jobDetail(Integer jobId) throws Exception {

		ModelAndView mv=new ModelAndView();
		ScheduleJob scheduleJob=scheduleJobDao.findId(jobId);
		
		ScheduleJobVo job=new ScheduleJobVo(scheduleJob);
		
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getTaskCode());
		Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
		job.setStatus(triggerState.name());	
		
		mv.addObject("job", job);	

		List<HttpRequest> httpRequestList =httpRequestDao.findJobId(job.getId());
		
		mv.addObject("httpRequestList", httpRequestList);	
		List<Data> requestTypeList=dataDao.findTypeId(1);
		mv.addObject("requestTypeList", requestTypeList);	

		return mv;
	}


	public Integer verifyTaskCode(Integer jobId, String taskCode)
			throws Exception {
		
		ScheduleJob scheduleJob=scheduleJobDao.findTaskCode(taskCode);
		if(scheduleJob==null){
			return 1;
		}else{
			if(jobId!=null && jobId>0){
				if(scheduleJob!=null && scheduleJob.getId().equals(jobId)){
					return 1;
				}
			}
			
		}
		return 0;
	}


	public ModelAndView editjob(Integer jobId) throws Exception {
		ModelAndView mv=new ModelAndView();
		if(jobId!=null && jobId>0){
			ScheduleJob scheduleJob=scheduleJobDao.findId(jobId);
			ScheduleJobVo job=new ScheduleJobVo(scheduleJob);
			mv.addObject("job", job);	
		}
		return mv;
	}


	public Integer savejob(ScheduleJob scheduleJob) throws Exception {
		Integer result=0;
		if(scheduleJob.getId()!=null ){
			result=scheduleJobDao.update(scheduleJob);
		}else{
			result=scheduleJobDao.insert(scheduleJob);
		}
		if(result.equals(1)){

			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			if(scheduleJob.getIsEnable()){//启用
				
				List<HttpRequest> hrList =httpRequestDao.findJobId(scheduleJob.getId());
				TaskUtils.taskEnable(scheduler, scheduleJob, hrList, httpPOJO,mongoPOJO);
			}else{//停用
				TaskUtils.taskDisable(scheduler, scheduleJob);
			}
		}
		return result;
	}


	public Integer saveHttpRequest(HttpRequest httpRequest) throws Exception {
		
		Integer result=0;
		if(httpRequest.getId()!=null ){
			result=httpRequestDao.update(httpRequest);
		}else{
			result=httpRequestDao.insert(httpRequest);
		}
		if(result.equals(1)){
			ScheduleJob scheduleJob=scheduleJobDao.findId(httpRequest.getJobId());
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			if(scheduleJob.getIsEnable()){//启用
				
				List<HttpRequest> hrList =httpRequestDao.findJobId(scheduleJob.getId());
				TaskUtils.taskEnable(scheduler, scheduleJob, hrList, httpPOJO,mongoPOJO);
			}else{//停用
				TaskUtils.taskDisable(scheduler, scheduleJob);
			}
			
		}
		return result;
	}


	public Integer delHttpRequest(Integer httpRequestId) throws Exception {
		
		HttpRequest httpRequest =httpRequestDao.findId(httpRequestId);
		Integer jobId=httpRequest.getJobId();
		Integer result=httpRequestDao.delete(httpRequestId);
		if(result.equals(1)){
			ScheduleJob scheduleJob=scheduleJobDao.findId(jobId);
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			if(scheduleJob.getIsEnable()){//启用
				List<HttpRequest> hrList =httpRequestDao.findJobId(scheduleJob.getId());
				TaskUtils.taskEnable(scheduler, scheduleJob, hrList, httpPOJO,mongoPOJO);
			}
			
		}
		return jobId;
	}


}
