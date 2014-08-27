package utry.task.dao;

import java.util.List;

import utry.task.po.DataType;


/***
 * 数据字典分类表访问
 * @author dingxinfa
 * @date 2014-06-30
 */
public interface DataTypeDao {
	/**
	 * 根据id获取数据字典分类信息
	 * 
	 * @param id
	 * @return DataSort
	 */
	public DataType findId(Integer id);
	
	
	/**
	 * 获取数据字典分类表所有信息集合
	 * 
	 * @return List<DataSort>
	 */
	public List<DataType> findAll();
	
	/**
	 * 返回数据字典分类表总数量
	 * 
	 * @return Integer
	 */
	public Integer count();
	
	/**
	 * 添加数据字典分类信息，返回执行成功数量
	 * 
	 * @param DataType
	 * @return Integer
	 */
	public Integer insert(DataType dataSort);
	
	/**
	 * 根据id删除数据字典分类信息，返回执行成功数量
	 * 
	 * @param id
	 * @return Integer
	 */
	public Integer delete(Integer id);
	
	
	/**
	 * 根据数据字典分类id更新数据，返回执行成功数量
	 * @param DataType
	 * @return Integer
	 */
	public Integer update(DataType dataSort);
}
