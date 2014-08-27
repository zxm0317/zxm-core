package utry.task.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.apache.commons.lang.StringUtils;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import utry.task.bo.HttpPOJO;
import utry.task.bo.MongoPOJO;
import utry.task.controller.vo.ScheduleJobVo;
import utry.task.dao.DataDao;
import utry.task.dao.HttpRequestDao;
import utry.task.dao.ScheduleJobDao;
import utry.task.po.HttpRequest;
import utry.task.po.ScheduleJob;
import utry.task.quartz.TaskUtils;
import utry.task.service.ExternalTaskService;

@Service
@Transactional
public class ExternalTaskServiceImpl implements ExternalTaskService {

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




	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map updateJobStatus(Boolean isEnable, String taskCode) throws Exception {
		
		Map map=new HashMap<String, Object>();
		Integer resultCode=0;
		String resultInfo="";
		
		ScheduleJob scheduleJob=scheduleJobDao.findTaskCode(taskCode);
		if(scheduleJob!=null){
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
		    
		    

		}else{
			resultCode=-1;
			resultInfo="任务调度系统不存在taskCode="+taskCode+"的任务！";
		}
		map.put("resultCode", resultCode);
		map.put("resultInfo",resultInfo);
		return map;
	}



	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map deleteJob(String taskCode) throws Exception {
		Map map=new HashMap<String, Object>();
		Integer resultCode=0;
		String resultInfo="";
		
		ScheduleJob scheduleJob=scheduleJobDao.findTaskCode(taskCode);
		
		if(scheduleJob!=null){
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			
			//停用 计划任务池内任务
			TaskUtils.taskDisable(scheduler, scheduleJob);
	
			List<HttpRequest> hrList =httpRequestDao.findJobId(scheduleJob.getId());
			for(HttpRequest hr:hrList){
				httpRequestDao.delete(hr.getId());
			}
			scheduleJobDao.delete(scheduleJob.getId());
			resultCode=1;
			resultInfo="删除成功！";
		}else{
			resultCode=-1;
			resultInfo="任务调度系统不存在taskCode="+taskCode+"的任务！";
		}
		map.put("resultCode", resultCode);
		map.put("resultInfo",resultInfo);
		return map;
	}




	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map savejob(ScheduleJob scheduleJob,List<HttpRequest> httpRequestList) throws Exception {
		Map map=new HashMap<String, Object>();
		Integer resultCode=0;
		String resultInfo="";
		
		//验证HTTP请求中参数是否符合要求。
		for(HttpRequest hr :httpRequestList){
			if(hr.getUrl()==null|| StringUtils.isBlank(hr.getUrl()) || hr.getRequestType()==null || StringUtils.isBlank(hr.getRequestType())){
				resultCode=-1;
				resultInfo="Http请求中参数不正确，请确认！";
				break;
				
			}
		}
		//HTTP请求参数不符合要求则跳出
		if(resultCode.equals(-1)){
			map.put("resultCode", resultCode);
			map.put("resultInfo",resultInfo);
			return map;
		}
		
		//新增或更新 数据库作业任务数据
		ScheduleJob sj=scheduleJobDao.findTaskCode(scheduleJob.getTaskCode());
		if(sj!=null){
			scheduleJob.setId(sj.getId());
		}
		
		if(scheduleJob.getId()!=null ){
			resultCode=scheduleJobDao.update(scheduleJob);
			
			//删除原有http请求
			List<HttpRequest> hrList =httpRequestDao.findJobId(scheduleJob.getId());
			for(HttpRequest hr:hrList){
				httpRequestDao.delete(hr.getId());
			}

		}else{
			resultCode=scheduleJobDao.insert(scheduleJob);
		}
		
		//插入http请求
		for(int i=0;i<httpRequestList.size();i++){
			HttpRequest httpRequest=httpRequestList.get(i);
			
			if(httpRequest.getUrl()==null|| StringUtils.isBlank(httpRequest.getUrl()) || httpRequest.getRequestType()==null || StringUtils.isBlank(httpRequest.getRequestType())){
				continue;
			}
			httpRequest.setJobId(scheduleJob.getId());
			if(httpRequest.getSerialNumber()==null ||"".equals(httpRequest.getSerialNumber())){
				httpRequest.setSerialNumber(i+1);
			}

			httpRequestDao.insert(httpRequest);

		}	
		
		if(resultCode.equals(1)){

			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			if(scheduleJob.getIsEnable()){//启用
				TaskUtils.taskEnable(scheduler, scheduleJob, httpRequestDao.findJobId(scheduleJob.getId()), httpPOJO,mongoPOJO);
			}else{//停用
				TaskUtils.taskDisable(scheduler, scheduleJob);
			}
		}
	    resultInfo="任务【"+scheduleJob.getTaskName()+"】加入计划任务成功！";

		map.put("resultCode", resultCode);
		map.put("resultInfo",resultInfo);
		return map;
		
	}




}
