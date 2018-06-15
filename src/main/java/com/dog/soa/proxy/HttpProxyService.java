package com.dog.soa.proxy;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dog.soa.utils.HttpUtils;

/**
 * 通过HTTP提供代理转发
 * @author jianglong
 * @date 2017年7月4日 上午10:58:14
 */
public class HttpProxyService {

	private final static Logger log = LoggerFactory.getLogger(HttpProxyService.class);
	public static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.151 Safari/535.19";
	public static RequestConfig config = RequestConfig.custom()
			.setSocketTimeout(15000)
			.setConnectTimeout(15000)
			.setConnectionRequestTimeout(15000)
			.setCookieSpec(CookieSpecs.DEFAULT)
			.build();
	public static HttpClient client = HttpClients.custom()
			.setUserAgent(USER_AGENT)
			.setDefaultRequestConfig(config)
			.build();
	
	/**
	 * 主方法入口（测试）
	 * @param args
	 */
	public static void main(String[] args) {
		HttpProxyService proxy = new HttpProxyService();
		String url = "http://172.16.52.8:8983/solr/hollysqm_v8_201703/select?q=*%3A*&fq=id%3A3FAB0BB8BF4F4F60A60FB6DA0CD0AC2B&wt=json&indent=true";
		proxy.get(url,"");
		url = "http://172.16.52.8:8983/solr/hollysqm_v8_201703/select";
		proxy.post(url,dataPairs());
	}
	
	/**
	 * 执行计划远程调用
	 * @param palnId
	 */
	public HttpResponse get(String url,String queryString){
		log.info("执行远程请求："+url+"...");
		HttpGet get = null;
		HttpResponse response = null;
		if (StringUtils.isNotBlank(queryString))
			url = url+"?"+queryString;
		try{
			log.info("客户端开始请求执行地址：" + url);
//			client = HttpClients.createDefault();
			get = new HttpGet(url);			
			get.setConfig(config);			
			response = client.execute(get);
		}catch(Exception e){
			e.printStackTrace();
			log.error("执行远程异常...",e);
		}finally{
			log.info("客户端结执行请求完毕...");				
		}
		return response;
	}
	
	/**
	 * 返回响应结果
	 * @param url
	 * @return
	 */
	public String doGet(String url,String queryString){
		return getResponseValue(this.get(url,queryString));
	}
	
	/**
	 * 返回响应结果
	 * @param url
	 * @param parameMap
	 * @return
	 */
	public String doGet(String url,Map<String,String[]> parameMap){
		return getResponseValue(this.get(url,HttpUtils.parameStrPairs(parameMap)));
	}
	
	/**
	 * 返回响应结果
	 * @param url
	 * @param parameMap
	 * @return
	 */
	public String doPost(String url,Map<String,String[]> parameMap){
		return getResponseValue(this.post(url,HttpUtils.parameEntityPairs(parameMap)));
	}
	
	/**
	 * 提取返回值，并转换成字符串
	 * @param response
	 * @return
	 */
	private String getResponseValue(HttpResponse response){
		if (response!=null){
			try{
				return EntityUtils.toString(response.getEntity(), "UTF-8");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 执行计划远程调用
	 * @param palnId
	 */
	public HttpResponse post(String url,HttpEntity entity){
		log.info("执行远程请求："+url+"...");
		HttpPost post = null;
		HttpResponse response = null;
		try{
			log.info("客户端开始请求执行地址：" + url);
//			client = HttpClients.createDefault();
			post = new HttpPost(url);
			post.setConfig(config);
			post.setEntity(entity);			
			response = client.execute(post);
		}catch(Exception e){
			e.printStackTrace();
			log.error("执行远程异常...",e);
		}finally{
			log.info("客户端结执行请求完毕...");			
		}
		return response;
	}
	
	
	private static UrlEncodedFormEntity dataPairs(){
		//?q=*%3A*&fq=id%3A3FAB0BB8BF4F4F60A60FB6DA0CD0AC2B&wt=json&indent=true
		//构造post数据
        List<NameValuePair> valuePairs = new LinkedList<NameValuePair>();
        valuePairs.add(new BasicNameValuePair("q", "*:*"));
        valuePairs.add(new BasicNameValuePair("fq", "id:3FAB0BB8BF4F4F60A60FB6DA0CD0AC2B"));
        valuePairs.add(new BasicNameValuePair("wt", "json"));
        valuePairs.add(new BasicNameValuePair("indent", "true"));
        
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(valuePairs, Consts.UTF_8);
		return entity;
	}
	
}
