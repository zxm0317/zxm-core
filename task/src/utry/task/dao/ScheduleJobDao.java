package utry.task.dao;

import java.util.List;

import utry.task.bo.JobDTO;
import utry.task.controller.vo.ScheduleJobVo;
import utry.task.po.ScheduleJob;


/***
 * 计划作业表
 * @author dingxinfa
 * @date 2014-07-22
 */
public interface ScheduleJobDao {

	
	/**
	 * 根据id获取数据信息
	 * 
	 * @param id
	 * @return ScheduleJob
	 */
	public ScheduleJob findId(Integer id);
	
	
	
	/**
	 * 根据taskCode 获取数据信息
	 * 
	 * @param taskCode
	 * @return List<ScheduleJob>
	 */
	public ScheduleJob findTaskCode(String  taskCode);
	
	
	/**
	 * 获取表所有信息集合
	 * 
	 * @return List<ScheduleJob>
	 */
	public List<ScheduleJob> findAll();
	
	/**
	 * 返回表总数量
	 * 
	 * @return Integer
	 */
	public Integer count();

	
	/**
	 * 添加数据，返回执行成功数量
	 * 
	 * @param acheduleJob
	 * @return Integer
	 */
	public Integer insert(ScheduleJob acheduleJob);
	
	/**
	 * 根据 id删除  数据信息，返回执行成功数量
	 * 
	 * @param id
	 * @return Integer
	 */
	public Integer delete(Integer id);
	
	
	/**
	 * 根据id更新 数据信息，返回执行成功数量
	 * @param acheduleJob
	 * @return Integer
	 */
	public Integer update(ScheduleJob acheduleJob);
	
	
	/***
	 * 根据筛选项条件获取 信息
	 * @param jobDTO
	 * @return List<ScheduleJobVo>
	 */
	public List<ScheduleJobVo> pagedSearchList(JobDTO jobDTO);
	
	
	/***
	 * 根据筛选项条件获取 数量
	 * @param jobDTO
	 * @return Integer
	 */
	public Integer pagedSearchCount(JobDTO jobDTO);
}
