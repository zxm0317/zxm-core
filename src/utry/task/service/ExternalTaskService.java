package utry.task.service;

import java.util.List;
import java.util.Map;


import utry.task.po.HttpRequest;
import utry.task.po.ScheduleJob;


/***
 * 对外计划工作接口
 * @author dingxinfa
 * @date 2014-08-18
 */
public interface ExternalTaskService {
	

	

	/***
	 * 作业任务启用OR停用
	 * @param isEnable
	 * @param taskCode
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Map updateJobStatus(Boolean isEnable,String taskCode)throws Exception;
	

	
	
	/***
	 * 删除计划作业
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Map deleteJob(String taskCode)throws Exception;
	

	
	
	/***
	 * 编辑的计划任务 保存提交
	 * @param scheduleJob
	 * @param httpRequestList
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Map savejob(ScheduleJob scheduleJob,List<HttpRequest> httpRequestList)throws Exception;
	
	
	
}