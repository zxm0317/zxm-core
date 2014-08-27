package utry.task.po;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/***
 * 数据字典分类表
 * @author dingxinfa
 * @date 2014-07-23
 */
@SuppressWarnings("serial")
public class DataType implements Serializable {

	/***
	 * 主键ID
	 */
	
	private Integer id;
	
	/***
	 * 分类名称
	 */
	
	private String name;
	
	/***
	 * 备注，分类描述
	 */
	
	private String memo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
}
