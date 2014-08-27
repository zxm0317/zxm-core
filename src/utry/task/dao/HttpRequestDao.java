package utry.task.dao;

import java.util.List;

import utry.task.po.HttpRequest;


/***
 * http请求表访问
 * @author dingxinfa
 * @date 2014-07-23
 */
public interface HttpRequestDao {
	/**
	 * 根据id获取数据信息
	 * 
	 * @param id
	 * @return HttpRequest
	 */
	public HttpRequest findId(Integer id);
	
	/***
	 * 根据jobId,获取数据列表
	 * @param jobId
	 * @return  List<HttpRequest>
	 */
	public List<HttpRequest> findJobId(Integer jobId);
	
	/**
	 * 获取所有信息集合
	 * 
	 * @return List<HttpRequest>
	 */
	public List<HttpRequest> findAll();
	
	/**
	 * 返回数据总数量
	 * 
	 * @return Integer
	 */
	public Integer count();
	
	/**
	 * 添加信息，返回执行成功数量
	 * 
	 * @param HttpRequest
	 * @return Integer
	 */
	public Integer insert(HttpRequest httpRequest);
	
	/**
	 * 根据id删除数据信息，返回执行成功数量
	 * 
	 * @param id
	 * @return Integer
	 */
	public Integer delete(Integer id);
	
	
	/**
	 * 根据id更新数据，返回执行成功数量
	 * @param HttpRequest
	 * @return Integer
	 */
	public Integer update(HttpRequest httpRequest);
	
	/***
	 * 根据数据字典jobId,获取该分类数量
	 * @param jobId
	 * @return Integer
	 */
	public Integer countJobId(Integer jobId);
}
