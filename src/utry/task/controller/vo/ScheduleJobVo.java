package utry.task.controller.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import utry.task.po.ScheduleJob;

@SuppressWarnings("serial")
public class ScheduleJobVo implements Serializable {
	/**
	 * 主键
	 */
	private Integer id;
	/***
	 * 任务名称
	 */
	
	private String taskName;
	
	/***
	 * 任务编码 唯一
	 */
	private String taskCode;
	
	/**
	 * 任务状态  是否启用
	 */
	private Boolean isEnable;
	
	/**
	 * 任务请求  是否按顺序执行
	 */
	private Boolean isOrder;
	
	/***
	 * 任务运行时间表达式
	 */
	private String cron;
	
	/***
	 * 任务描述，备注
	 */
	private String memo;
	
	/***
	 * 当前运行状态
	 */
	private String status;

	
	public ScheduleJobVo(){
	}
	
	public ScheduleJobVo(ScheduleJob job){
		this.id = job.getId();
		this.taskName = job.getTaskName();
		this.cron=job.getCron();
		this.isEnable=job.getIsEnable();
		this.isOrder=job.getIsOrder();
		this.memo=job.getMemo();
		this.taskCode=job.getTaskCode();
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTaskName() {
		return taskName;
	}


	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}


	public String getTaskCode() {
		return taskCode;
	}


	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}


	public Boolean getIsEnable() {
		return isEnable;
	}


	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}


	public Boolean getIsOrder() {
		return isOrder;
	}


	public void setIsOrder(Boolean isOrder) {
		this.isOrder = isOrder;
	}


	public String getCron() {
		return cron;
	}


	public void setCron(String cron) {
		this.cron = cron;
	}


	public String getMemo() {
		return memo;
	}


	public void setMemo(String memo) {
		this.memo = memo;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
}
