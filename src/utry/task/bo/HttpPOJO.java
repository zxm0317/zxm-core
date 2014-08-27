package utry.task.bo;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HttpPOJO {

	@Value("${http.connectTimeout}")
	private Integer connectTimeout;

	@Value("${http.socketTimeout}")
	private Integer socketTimeout;

	@Value("${http.connectionRequestTimeout}")
	private Integer connectionRequestTimeout;

	@Value("${http.connectionPooling.maxTotal}") 
	private Integer maxTotal;
	
	@Value("${http.connectionPooling.defaultMaxPerRoute}")
	private Integer defaultMaxPerRoute;

	public Integer getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public Integer getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(Integer socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public Integer getConnectionRequestTimeout() {
		return connectionRequestTimeout;
	}

	public void setConnectionRequestTimeout(Integer connectionRequestTimeout) {
		this.connectionRequestTimeout = connectionRequestTimeout;
	}

	public Integer getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}

	public Integer getDefaultMaxPerRoute() {
		return defaultMaxPerRoute;
	}

	public void setDefaultMaxPerRoute(Integer defaultMaxPerRoute) {
		this.defaultMaxPerRoute = defaultMaxPerRoute;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
