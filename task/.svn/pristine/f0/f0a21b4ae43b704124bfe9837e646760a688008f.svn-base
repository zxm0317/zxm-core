package utry.task.po;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/***
 * 数据字典表
 * @author dingxinfa
 * @date 2014-07-23
 */
@SuppressWarnings("serial")
public class Data implements Serializable {

	/***
	 * 主键ID
	 */
	
	private Integer id;
	
	/***
	 * 数据类型ID（外键）
	 */
	
	private Integer typeId;
	
	/***
	 * 数据名称
	 */
	
	private String name;
	
	/***
	 * 数据值
	 */
	
	private String value;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
}
