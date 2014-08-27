package utry.task.bo;

import java.util.List;

import utry.task.po.HttpRequest;


/***
 * Spring MVC 接收list参数
 * @author dingxinfa
 * @date 2014-08-19
 */
public class HttpRequestForm {

	
	private List<HttpRequest> httpRequest;

	public List<HttpRequest> getHttpRequest() {
		return httpRequest;
	}

	public void setHttpRequest(List<HttpRequest> httpRequest) {
		this.httpRequest = httpRequest;
	}
	
	
	
}
