package utry.task.bo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MongoPOJO {


	@Value("${mongo.ip}")
	private String ip;
	
	@Value("${mongo.port}")
	private Integer port;
	
	@Value("${mongo.logDBName}")
	private String dbName;
	
	@Value("${mongo.logTableName}")
	private String tableName;
	
	
	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public Integer getPort() {
		return port;
	}


	public void setPort(Integer port) {
		this.port = port;
	}


	public String getDbName() {
		return dbName;
	}


	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
