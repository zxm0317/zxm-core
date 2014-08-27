package utry.task.bo;

import java.io.Serializable;
import java.nio.charset.Charset;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;



/***
 * http请求返回数据
 * 
 * @author dingxinfa
 * 
 */
@SuppressWarnings("serial")
public class HttpResultPOJO implements Serializable {

	/***
	 * 状态码
	 */
	private Integer statusCode;

	/***
	 * 状态原因说明
	 */
	private String reasonPhrase;

	/***
	 * 内容MIME类型
	 */
	private String contentMimeType;

	/***
	 * 内容编码
	 */
	private Charset charset;

	/***
	 * 内容实体
	 */
	private String content;

	public HttpResultPOJO() {
	}

	public HttpResultPOJO(Integer statusCode, String reasonPhrase,
			Charset charset, String contentMimeType, String content) {
		this.statusCode = statusCode;
		this.reasonPhrase = reasonPhrase;
		this.charset = charset;
		this.contentMimeType = contentMimeType;
		this.content = content;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}

	public void setReasonPhrase(String reasonPhrase) {
		this.reasonPhrase = reasonPhrase;
	}

	public String getContentMimeType() {
		return contentMimeType;
	}

	public void setContentMimeType(String contentMimeType) {
		this.contentMimeType = contentMimeType;
	}

	public Charset getCharset() {
		return charset;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
}
