package utry.task.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import utry.task.bo.HttpPOJO;
import utry.task.bo.HttpResultPOJO;

/**
 * 基于 httpclient 4.3.x版本
 * HttpClient相关
 * @author dingxinfa
 * @date 2014-07-23
 */
@Component
public class HttpClient {


	/**默认编码**/
	private static final String CHARSET = "UTF-8";

	private static CloseableHttpClient httpClient=null;

	public HttpClient(){}
	
	public HttpClient(HttpPOJO httpPOJO){
		doHttpClient(httpPOJO);
	}
	
    /***
     * http请求主入口
     * @param url
     * @param parameter
     * @param requestType
     * @return
     * @throws IOException
     */
	public  HttpResultPOJO doHttp(String url,String parameter,String requestType) throws IOException {
		
		if (StringUtils.isBlank(url)) {
			return null;
		}
		// 请求参数处理
		List<NameValuePair> pairs = doParams(parameter);

		// http请求
		CloseableHttpResponse response;
		if (requestType!=null && requestType.toUpperCase().equals("POST")) {
			response= doPost(url, pairs, httpClient);
		} else {
			response= doGet(url, pairs, httpClient);
		}

		Integer statusCode=null;
		String reasonPhrase=null;
		if (response != null) {
			try {
				statusCode = response.getStatusLine().getStatusCode();
				reasonPhrase = response.getStatusLine().getReasonPhrase();

				if (statusCode == 200) {
					HttpEntity entity = response.getEntity();
				    if (entity != null) { 				    	
						String contentMimeType = ContentType.getOrDefault(entity).getMimeType(); 
						Charset charset = ContentType.getOrDefault(entity).getCharset();
						String content=null;
						
						if(charset!=null){
							content=EntityUtils.toString(entity,charset);
						}else{
							content=EntityUtils.toString(entity);
						}

				    	HttpResultPOJO httpResultPOJO=new HttpResultPOJO(statusCode, reasonPhrase,charset,contentMimeType,content);
						EntityUtils.consume(entity);
				    	return httpResultPOJO;

				    }  
					
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				response.close();
			}
		}	
		return new HttpResultPOJO(statusCode, reasonPhrase,null,null,null);

	}

	
	/***
	 * 关闭连接
	 * @throws IOException
	 */
    public 	void close()throws IOException {
    	if(httpClient!=null){
    		httpClient.close();
    	}
    }

	
	/***
	 * 生成http客户端连接池 连接
	 * 
	 * @param connectTimeout
	 * @param socketTimeout
	 * @return
	 */
    public  void doHttpClient(HttpPOJO httpPOJO) {

		RequestConfig config = RequestConfig.custom()
				.setConnectionRequestTimeout(httpPOJO.getConnectionRequestTimeout())
				.setConnectTimeout(httpPOJO.getConnectTimeout())
				.setSocketTimeout(httpPOJO.getSocketTimeout()).build();
		
		/***
		 * 以下代码https相关，暂不考虑实现
		 */
		
		/*****		
		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		FileInputStream trustStoreInput = new FileInputStream(new File(CLIENT_TRUST_KEY_STORE));
		trustStore.load(trustStoreInput, CLIENT_TRUST_KEY_STORE_PASSWORD.toCharArray());
		 
		KeyStore clientKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		FileInputStream clientKeyStoreInput = new FileInputStream(new File(CLIENT_KEY_STORE));
		clientKeyStore.load(clientKeyStoreInput, CLIENT_KEY_STORE_PASSWORD.toCharArray());
		
		SSLContext sslContext = SSLContexts.custom()
		        .loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
		        .loadKeyMaterial(clientKeyStore, CLIENT_KEY_PASS.toCharArray())
		        .setSecureRandom(new SecureRandom())
		        .useSSL()
		        .build();
		
		
		SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(
		        sslContext, new String[]{"SSLv3"}, null,
		        SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		 */
		
		 
		 
		ConnectionSocketFactory plainSocketFactory = new PlainConnectionSocketFactory();
		
		RegistryBuilder<ConnectionSocketFactory> registryBuilder=RegistryBuilder.<ConnectionSocketFactory>create();
		registryBuilder.register("http", plainSocketFactory);
		 
		//https相关先注释掉，不考虑
		//registryBuilder.register("https", sslSocketFactory);
		
		 
		Registry<ConnectionSocketFactory> registry = registryBuilder.build();
		
		PoolingHttpClientConnectionManager secureConnectionManager = new PoolingHttpClientConnectionManager(registry);
		//连接池最大连接
		secureConnectionManager.setMaxTotal(httpPOJO.getMaxTotal());
		//设置每个站点的连接最大数
		secureConnectionManager.setDefaultMaxPerRoute(httpPOJO.getDefaultMaxPerRoute());
		         
		HttpClientBuilder secureHttpBulder = HttpClients.custom();
		secureHttpBulder.setConnectionManager(secureConnectionManager);
		secureHttpBulder.setDefaultRequestConfig(config);
		//模仿chrome 浏览器
		secureHttpBulder.setUserAgent("UTRY.CN");
		
		httpClient=secureHttpBulder.build();
	}

	/***
	 * 生成http 参数
	 * 
	 * @param parameter     原始json格式字符串参数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private  List<NameValuePair> doParams(String parameter) {
		List<NameValuePair> pairs = null;
		if (StringUtils.isNotBlank(parameter)) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				Map<String, Object> params = mapper.readValue(parameter,
						Map.class);

				if (params != null && !params.isEmpty()) {
					pairs = new ArrayList<NameValuePair>(params.size());
					for (Map.Entry<String, Object> entry : params.entrySet()) {
						String value = entry.getValue().toString();
						if (value != null) {
							pairs.add(new BasicNameValuePair(entry.getKey(),
									value));
						}
					}
				}

			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return pairs;
	}

	

	/**
	 * HTTP Get请求
	 * 
	 * @param url
	 *            请求的url地址,无参数地址
	 * @param pairs
	 *            请求的参数
	 * @param httpClient
	 *            http客户端
	 * @return HttpResponse http响应
	 */
	private  CloseableHttpResponse doGet(String url,
			List<NameValuePair> pairs, CloseableHttpClient httpClient) {
		try {
			if (pairs != null && pairs.size() > 0) {
				url += "?"
						+ EntityUtils.toString(new UrlEncodedFormEntity(pairs,
								CHARSET));
			}

			HttpGet httpGet = new HttpGet(url);

			CloseableHttpResponse response = httpClient.execute(httpGet);
			Integer statusCode = response.getStatusLine().getStatusCode();

	        if (statusCode != 200) {
	        	httpGet.abort();
            }
			return response;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * HTTP Post请求
	 * 
	 * @param url
	 *            请求的url地址,无参数地址
	 * @param pairs
	 *            请求的参数
	 * @param httpClient
	 *            http客户端
	 * @return HttpResponse http响应
	 */
	private  CloseableHttpResponse doPost(String url,
			List<NameValuePair> pairs, CloseableHttpClient httpClient) {
		try {
			HttpPost httpPost = new HttpPost(url);
			if (pairs != null && pairs.size() > 0) {
				httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
			}
			CloseableHttpResponse response = httpClient.execute(httpPost);

			Integer statusCode = response.getStatusLine().getStatusCode();

	        if (statusCode != 200) {
	        	httpPost.abort();
            }
			return response;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}