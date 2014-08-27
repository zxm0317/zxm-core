package utry.task.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import utry.task.bo.JobDTO;
import utry.task.controller.vo.ScheduleJobVo;
import utry.task.po.HttpRequest;
import utry.task.po.ScheduleJob;
import utry.task.service.ScheduleJobService;
import utry.task.utils.PageController;

/***
 * 计划任务控制层
 * @author dingxinfa
 * @date 2014-07-22
 */
@Controller
@RequestMapping("/job")
public class ScheduleJobController {	
	
	@Resource
	private ScheduleJobService scheduleJobServiceImpl;
	
	/***
	 * 计划工作列表  API
	 * @author dingxinfa
	 * @date 2014-07-25
	 * @param keywords
	 * @param page
	 * @param isEnable
	 * @param session
	 * @return
	 * @throws SchedulerException
	 */
	@RequestMapping(value = "/jobList")
	public ModelAndView jobList(
		@RequestParam(required=false,value="keywords") String keywords,
		@RequestParam(required=false,value="page") Integer page,
		@RequestParam(required=false,value="isEnable") Boolean isEnable,
		HttpSession session)throws Exception {
		
		if(page==null || page.equals(0)){
			page=1;
		}
		session.setAttribute("jobListPage", page);

		JobDTO jobDTO=new JobDTO();
		jobDTO.setKeywords(keywords);
		jobDTO.setIsEnable(isEnable);
		session.setAttribute("jobDTO", jobDTO);

		return jobListPublic(null,jobDTO,page);
	}

	/***
	 * 计划工作列表  分页API
	 * @author dingxinfa
	 * @date 2014-07-25
	 * @param page
	 * @param mv
	 * @param session
	 * @return
	 * @throws SchedulerException
	 */
	@RequestMapping(value = "/pagedJobList")
	public ModelAndView pagedJobList(
		@RequestParam(required=false,value="page") Integer page,
		@RequestParam(required=false,value="mv") ModelAndView mv,
		HttpSession session)throws Exception {
		
		JobDTO jobDTO=(JobDTO)session.getAttribute("jobDTO");
		if(jobDTO==null){
			return jobList(null,null,null,session);
		}

		
		if(page==null || page.equals(0)){
			if(session.getAttribute("jobListPage")!=null){
				page=(Integer) session.getAttribute("jobListPage");
			}else{
				page=1;
			}
		}
		session.setAttribute("jobListPage", page);
		
		return jobListPublic(mv,jobDTO,page);
		
	}

	/****
	 * 计划工作列表 公共
	 * @author dingxinfa
	 * @date 2014-07-25
	 * @param mv
	 * @param jobDTO
	 * @param page
	 * @return
	 * @throws Exception
	 */
	private  ModelAndView jobListPublic(ModelAndView mv,JobDTO jobDTO,Integer page) throws Exception {
		if(mv!=null){
			mv.setViewName("job/jobList");
		}else{
			mv = new ModelAndView("job/jobList");
		}
		
		PageController pageController=new PageController(scheduleJobServiceImpl.pagedSearchJobCount(jobDTO), page, 12);
		pageController.setUrl("/job/pagedJobList.do");
		jobDTO.setStartRow(pageController.getPageStartRow());
		jobDTO.setEndRow(pageController.getPageEndRow());
		
		mv.addObject("pageController", pageController);
		
		List<ScheduleJobVo> sjvList=scheduleJobServiceImpl.pagedSearchJobList(jobDTO);
/*		for(ScheduleJobVo sjv:sjvList){
			System.out.println(sjv.toString());
		}*/
				
		mv.addObject("scheduleJobList", sjvList);	

		return mv;
		
	}
	
	
	/***
	 * 立即执行一次任务
	 * @author dingxinfa
	 * @date 2014-07-25
	 * @param jobId
	 * @param session
	 * @return Map
	 * @throws Exception
	 */
	@RequestMapping(value = "/executeJob")
	public @ResponseBody Integer executeJob(
		@RequestParam("jobId") Integer jobId,
		HttpSession session) throws Exception {
		
		 scheduleJobServiceImpl.executeJob(jobId);
		 return 1;
	}
	
	
	
	
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
	@RequestMapping(value = "/enableJob")
	public @ResponseBody Map enableJob(
		@RequestParam("jobId") Integer jobId,
		HttpSession session) throws Exception {
		
		return scheduleJobServiceImpl.updateJobStatus(true,jobId);

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
	@RequestMapping(value = "/disableJob")
	public @ResponseBody Map disableJob(
		@RequestParam("jobId") Integer jobId,
		HttpSession session) throws Exception {

		return scheduleJobServiceImpl.updateJobStatus(false,jobId);
	}
	
	
	
	/***
	 * 验证任务编码唯一性
	 * @author dingxinfa
	 * @date 2014-07-28
	 * @param jobId
	 * @param taskCode
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/verifyTaskCode")
	public @ResponseBody Integer verifyTaskCode(
			@RequestParam(required=false,value="jobId") Integer jobId,
			@RequestParam("taskCode") String taskCode,
			HttpSession session)throws Exception {
		
		return scheduleJobServiceImpl.verifyTaskCode(jobId,taskCode);
	}
	
	
	/***
	 * 任务作业详情
	 * @author dingxinfa
	 * @date 2014-07-28
	 * @param jobId
	 * @param session
	 * @return Map
	 * @throws Exception
	 */
	@RequestMapping(value = "/jobDetail")
	public ModelAndView jobDetail(
		@RequestParam("jobId") Integer jobId,
		HttpSession session) throws Exception {
		ModelAndView mv =scheduleJobServiceImpl.jobDetail(jobId);
		mv.setViewName("job/jobDetail");
		return mv;
	}
	
	/***
	 * 添加OR编辑 任务
	 * @author dingxinfa
	 * @date 2014-07-28
	 * @param jobId
	 * @param session
	 * @return Map
	 * @throws Exception
	 */
	@RequestMapping(value = "/editJob")
	public ModelAndView editJob(
		@RequestParam(required=false,value="jobId") Integer jobId,
		HttpSession session) throws Exception {
		ModelAndView mv =scheduleJobServiceImpl.editjob(jobId);
		mv.setViewName("job/editJob");
		return mv;
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
	@RequestMapping(value = "/savejob")
	public String savejob(
		@RequestParam(required=false,value="jobId") Integer jobId,
		@RequestParam("taskName") String taskName,
		@RequestParam("taskCode") String taskCode,
		@RequestParam("isEnable") Boolean isEnable,
		@RequestParam("isOrder") Boolean isOrder,
		@RequestParam("cron") String cron,
		@RequestParam(required=false,value="memo") String memo,
		HttpSession session) throws Exception {
		
		ScheduleJob scheduleJob=new ScheduleJob();
		scheduleJob.setCron(cron);
		scheduleJob.setId(jobId);
		scheduleJob.setIsEnable(isEnable);
		scheduleJob.setIsOrder(isOrder);
		scheduleJob.setMemo(memo);
		scheduleJob.setTaskCode(taskCode);
		scheduleJob.setTaskName(taskName);
		//System.out.println(scheduleJob.toString());
		
		Integer result=scheduleJobServiceImpl.savejob(scheduleJob);
		
		if(result.equals(1)){
			return "redirect:/job/jobDetail.do?jobId="+scheduleJob.getId();
		}else{
			return "redirect:/job/editjob.do?jobId="+scheduleJob.getId();
		}

	}
	
	
	/***
	 * 保存编辑的任务HTTP请求
	 * @author dingxinfa
	 * @date 2014-07-29
	 * @param jobId
	 * @param httpRequestId
	 * @param serialNumber
	 * @param requestType
	 * @param url
	 * @param parameter
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveHttpRequest")
	public String saveHttpRequest(
		@RequestParam("jobId") Integer jobId,
		@RequestParam(required=false,value="httpRequestId") Integer httpRequestId,
		@RequestParam("serialNumber") Integer serialNumber,
		@RequestParam("requestType") String requestType,
		@RequestParam("url") String url,
		@RequestParam(required=false,value="parameter") String parameter,
		HttpSession session) throws Exception {
		
		HttpRequest  httpRequest=new HttpRequest();
		httpRequest.setId(httpRequestId);
		httpRequest.setJobId(jobId);
		httpRequest.setParameter(parameter);
		httpRequest.setRequestType(requestType);
		httpRequest.setSerialNumber(serialNumber);
		httpRequest.setUrl(url);
		
		
		
		scheduleJobServiceImpl.saveHttpRequest(httpRequest);

		
		return "redirect:/job/jobDetail.do?jobId="+jobId;
	}
	
	/***
	 * 
	 * 删除计划任务中的HTTP请求
	 * @author dingxinfa
	 * @date 2014-07-29
	 * @param httpRequestId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delHttpRequest")
	public String delHttpRequest(
		@RequestParam("httpRequestId") Integer httpRequestId,
		HttpSession session) throws Exception {
		
		Integer jobId= scheduleJobServiceImpl.delHttpRequest(httpRequestId);

		
		return "redirect:/job/jobDetail.do?jobId="+jobId;

		
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
	@RequestMapping(value = "/deleteJob")
	public String deleteJob(
		@RequestParam("jobId") Integer jobId,
		HttpSession session) throws Exception {		
		scheduleJobServiceImpl.deleteJob(jobId);

		return "redirect:/job/pagedJobList.do";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
