package utry.task.dao;

import java.util.List;

import utry.task.po.Data;


/***
 * 数据字典表访问
 * @author dingxinfa
 * @date 2014-07-23
 */
public interface DataDao {
	/**
	 * 根据id获取数据字典信息
	 * 
	 * @param id
	 * @return Data
	 */
	public Data findId(Integer id);
	
	/***
	 * 根据数据字典typeId,获取该分类数据列表
	 * @param typeId
	 * @return  List<Data>
	 */
	public List<Data> findTypeId(Integer typeId);
	
	/**
	 * 获取数据字典表所有信息集合
	 * 
	 * @return List<Data>
	 */
	public List<Data> findAll();
	
	/**
	 * 返回数据字典表总数量
	 * 
	 * @return Integer
	 */
	public Integer count();
	
	/**
	 * 添加数据字典信息，返回执行成功数量
	 * 
	 * @param Data
	 * @return Integer
	 */
	public Integer insert(Data data);
	
	/**
	 * 根据数据字典信息的id删除数据字典信息，返回执行成功数量
	 * 
	 * @param id
	 * @return Integer
	 */
	public Integer delete(Integer id);
	
	
	/**
	 * 根据数据字典id更新数据，返回执行成功数量
	 * @param Data
	 * @return Integer
	 */
	public Integer update(Data data);
	
	/***
	 * 根据数据字典typeId,获取该分类数量
	 * @param typeId
	 * @return Integer
	 */
	public Integer countTypeId(Integer typeId);
}
