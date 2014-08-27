package utry.task.external.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import utry.task.bo.HttpRequestForm;
import utry.task.po.HttpRequest;
import utry.task.po.ScheduleJob;
import utry.task.service.ExternalTaskService;


/***
 * 对外 请求 计划任务控制层
 * @author dingxinfa
 * @date 2014-08-18
 */
@Controller
@RequestMapping("/external")
public class ExternalTaskController {	
	
	@Resource
	private ExternalTaskService externalTaskServiceImpl;


	/***
	 * 任务作业启用
	 * @author dingxinfa
	 * @date 2014-07-25
	 * @param jobId
	 * @param session
	 * @return Map
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/enableTask")
	public @ResponseBody Map enableTask(
		@RequestParam("taskCode") String taskCode,
		HttpSession session) throws Exception {
		
		return externalTaskServiceImpl.updateJobStatus(true,taskCode);

	}
	/***
	 * 任务作业停用
	 * @author dingxinfa
	 * @date 2014-07-25
	 * @param jobId
	 * @param session
	 * @return Map
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/disableTask")
	public @ResponseBody Map disableTask(
			@RequestParam("taskCode") String taskCode,
		HttpSession session) throws Exception {

		return externalTaskServiceImpl.updateJobStatus(false,taskCode);
	}
	
	
	/***
	 * 保存编辑的任务
	 * @author dingxinfa
	 * @date 2014-07-29
	 * @param scheduleJob
	 * @param session
	 * @return Map
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/saveTask")
	public @ResponseBody Map saveTask(
		@RequestParam("taskName") String taskName,
		@RequestParam("taskCode") String taskCode,
		@RequestParam("isEnable") Boolean isEnable,
		@RequestParam(required=false,value = "isOrder") Boolean isOrder,
		@RequestParam("cron") String cron,
		@RequestParam(required=false,value="memo") String memo,
		HttpRequestForm httpRequestForm,
		HttpSession session) throws Exception {
		
		ScheduleJob scheduleJob=new ScheduleJob();
		scheduleJob.setCron(cron);
		scheduleJob.setIsEnable(isEnable);
		if(isOrder==null){
			isOrder=false;
		}
		scheduleJob.setIsOrder(isOrder);
		scheduleJob.setMemo(memo);
		scheduleJob.setTaskCode(taskCode);
		scheduleJob.setTaskName(taskName);
		//System.out.println(scheduleJob.toString());
		
		List<HttpRequest> httpRequestList=httpRequestForm.getHttpRequest();
/*		for(HttpRequest hr:httpRequestForm.getHttpRequest()){
			System.out.println(hr.toString());
		}*/
		return externalTaskServiceImpl.savejob(scheduleJob,httpRequestList);

	}
	
	
	/****
	 * 删除 计划任务
	 * @author dingxinfa
	 * @date 2014-07-29
	 * @param jobId 任务作业ID
	 * @param session
	 * @return ModelAndView
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/deleteTask")
	public @ResponseBody Map deleteTask(
		@RequestParam("taskCode") String taskCode,
		HttpSession session) throws Exception {		
		return externalTaskServiceImpl.deleteJob(taskCode);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
