package utry.task.po;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/***
 * Http请求表
 * @author dingxinfa
 * @date 2014-07-23
 */
@SuppressWarnings("serial")
public class HttpRequest implements Serializable {
	
	/***
	 * 主键
	 */
	private Integer id;
	
	/***
	 * 计划作业ID
	 */
	private Integer jobId;
	
	/**
	 * 请求地址
	 */
	private String url;
	
	/**
	 * 请求参数
	 */
	private String parameter;
	
	/**
	 * 请求类型
	 */
	private String requestType;

	
	/***
	 * 流水号，序列号，顺序编号(如果序列号相同，以主键为主)
	 */
	
	private Integer serialNumber;

	public String toString(){
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

}
