package utry.task.quartz;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import utry.task.bo.HttpPOJO;
import utry.task.bo.HttpResultPOJO;
import utry.task.bo.MongoPOJO;
import utry.task.po.HttpRequest;
import utry.task.po.ScheduleJob;
import utry.task.utils.HttpClient;
import utry.task.utils.MongoManage;


/**
 * 定时任务作业实现工厂
 * @author dingxinfa
 * @date 2014-07-23
 */
@Component
public class QuartzJobFactory implements Job {

    /** 日志对象 */
	//private static final Logger log = Logger.getLogger(QuartzJobFactory.class);

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(JobExecutionContext context) throws JobExecutionException {

		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		String taskName = scheduleJob.getTaskName();
		String taskCode = scheduleJob.getTaskCode();
		Integer taskId = scheduleJob.getId();
		String memo = scheduleJob.getMemo();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sd.format(new Date());
		
		System.out.println("TaskId:"+taskId+"|date:" +dateString +"|taskName:"+taskName+"|taskCode:"+taskCode+"|memo:"+memo );
		
		Map info = new HashMap<String,String>();
		info.put("taskId", taskId);
		info.put("taskName", taskName);
		info.put("taskCode", taskCode);
		
		List<HttpRequest> hrList =(List<HttpRequest>) context.getMergedJobDataMap().get("httpRequestList");
		
		HttpPOJO httpPOJO=(HttpPOJO) context.getMergedJobDataMap().get("httpPOJO");
		
		MongoPOJO mongoPOJO=(MongoPOJO) context.getMergedJobDataMap().get("mongoPOJO");
		if(hrList!=null){
			if(scheduleJob.getIsOrder() && hrList.size()>1){
				//按次序执行
		        Iterator<HttpRequest> it = hrList.iterator();  
		        Integer statusCode=0;
				do{
					HttpRequest hr=it.next();

					Map map = new HashMap<String,String>();
					map.putAll(info);
					map.put("url", hr.getUrl());
					map.put("params", hr.getParameter());
					map.put("requestType", hr.getRequestType());
					
					try {
						HttpClient httpClient=new HttpClient(httpPOJO);
						HttpResultPOJO httpResultPOJO=httpClient.doHttp(hr.getUrl(),hr.getParameter(),hr.getRequestType());
						httpClient.close();
						statusCode=httpResultPOJO.getStatusCode();
						
						map.put("resultStatusCode", httpResultPOJO.getStatusCode());
						map.put("resultReasonPhrase", httpResultPOJO.getReasonPhrase());
						
					} catch (IOException e) {
						e.printStackTrace();
						map.put("javaError", e.getMessage());

					}
					map.put("createTime", new Date());
					MongoManage mongoLog=new MongoManage(mongoPOJO.getIp(), mongoPOJO.getPort(), mongoPOJO.getDbName());
					mongoLog.save(mongoPOJO.getTableName(),map);
					mongoLog.close();
				}while(statusCode.equals(200) && it.hasNext());
				
				
			}else{
				for(HttpRequest hr:hrList){

					Map map = new HashMap<String,String>();
					map.putAll(info);
					map.put("url", hr.getUrl());
					map.put("params", hr.getParameter());
					map.put("requestType", hr.getRequestType());
					
					try {
						HttpClient httpClient=new HttpClient(httpPOJO);
						HttpResultPOJO httpResultPOJO=httpClient.doHttp(hr.getUrl(),hr.getParameter(),hr.getRequestType());
						httpClient.close();
						
						map.put("resultStatusCode", httpResultPOJO.getStatusCode());
						map.put("resultReasonPhrase", httpResultPOJO.getReasonPhrase());
						
					} catch (IOException e) {
						e.printStackTrace();
						
						map.put("javaError", e.getMessage());
					}
					map.put("createTime", new Date());

					MongoManage mongoLog=new MongoManage(mongoPOJO.getIp(), mongoPOJO.getPort(), mongoPOJO.getDbName());
					mongoLog.save(mongoPOJO.getTableName(),map);
					mongoLog.close();
				}
			}
		}
		
		
		
	}
}

