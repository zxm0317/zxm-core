package utry.task.bo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/***
 * View数据列表公共继承类
 * @author dingxinfa
 * @date 2014-07-02
 */

public class CommonDTO {
	
	/**
	 * 开始行
	 */
	private Integer startRow = 0;
	/**
	 * 结束行
	 */
	private Integer endRow = 0;
	/**
	 * 排序
	 */
	private String orderBy;
	
	/***
	 * 搜索关键字
	 */
	private String keywords;
	
	public Integer getStartRow() {
		return startRow;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	public Integer getEndRow() {
		return endRow;
	}
	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String toString(){
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
}
