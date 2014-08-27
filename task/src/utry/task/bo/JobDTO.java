package utry.task.bo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/***
 * 特征扩展类
 * @author dingxinfa
 * @date 2014-07-07
 */
public class JobDTO extends CommonDTO {

	/***
	 * 是否启用
	 */
	private Boolean isEnable;
	
	public Boolean getIsEnable() {
		return isEnable;
	}



	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}



	public String toString(){
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
}
