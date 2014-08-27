package utry.task.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import utry.task.bo.JobDTO;
import utry.task.controller.vo.ScheduleJobVo;
import utry.task.po.HttpRequest;
import utry.task.po.ScheduleJob;


/***
 * 计划工作管理接口
 * @author dingxinfa
 * @date 2014-07-25
 */
public interface ScheduleJobService {
	
	/**
	 * 筛选后计划工作列表
	 * @author dingxinfa
	 * @date 2014-07-25
	 * @return List<FeatureView> 
	 * @throws Exception
	 */
	public List<ScheduleJobVo> pagedSearchJobList (JobDTO jobDTO)  throws Exception;
	
	
	/**
	 * 筛选后计划工作总数
	 * @author dingxinfa
	 * @date 2014-07-07
	 * @return Integer
	 * @throws Exception
	 */
	public Integer pagedSearchJobCount (JobDTO jobDTO)  throws Exception;
	
	/***
	 * 作业任务启用OR停用
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Map updateJobStatus(Boolean isEnable,Integer jobId)throws Exception;
	
	
	/***
	 * 立即执行一次任务
	 * @return
	 * @throws Exception
	 */
	public void executeJob(Integer jobId)throws Exception;
	
	
	/***
	 * 删除计划作业
	 * @return
	 * @throws Exception
	 */
	public void deleteJob(Integer jobId)throws Exception;
	
	/***
	 * 计划作业详情
	 * @param jobId
	 * @return
	 * @throws Exception
	 */
	public ModelAndView jobDetail(Integer jobId)throws Exception;
	
	/****
	 * 验证任务编码唯一性
	 * @param jobId
	 * @param taskCode
	 * @return
	 * @throws Exception
	 */
	
	public Integer verifyTaskCode(Integer jobId,String taskCode)throws Exception;
	
	/****
	 * 计划任务修改编辑
	 * @param jobId
	 * @return
	 * @throws Exception
	 */
	
	public ModelAndView editjob(Integer jobId)throws Exception;
	
	/***
	 * 新增 OR 修改计划任务 保存提交
	 * @param scheduleJob
	 * @return
	 * @throws Exception
	 */
	public Integer savejob(ScheduleJob scheduleJob)throws Exception;
	
	
	
	/***
	 * 新增 OR 修改  计划任务 http请求  保存提交
	 * @param scheduleJob
	 * @return
	 * @throws Exception
	 */
	public Integer saveHttpRequest(HttpRequest  httpRequest)throws Exception;
	
	
	/***
	 * 删除 计划任务中的HTTP请求
	 * @param httpRequestId
	 * @return
	 * @throws Exception
	 */
	public Integer delHttpRequest(Integer httpRequestId)throws Exception;
}
