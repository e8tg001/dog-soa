package com.dog.soa.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

public class HttpUtils {

	
	
	 /** 
     * 处理头信息 
     */  
//    public static Map<String,String> parseHeader(HttpHeaders httpHeaders) {  
//        Map<String,String> httpHeaderMap = null;  
//        for (Map.Entry header : httpHeaders) {  
//            if(String.valueOf(HttpHeaderNames.ACCEPT).equalsIgnoreCase(header.getKey().toString())) {  
//                String value = String.valueOf(header.getValue());  
//                try {  
//                    httpHeaderMap = JSON.parseObject(value, Map.class);  
//                } catch (Exception e) {  
//                    httpHeaderMap = null;  
//                }  
//            }  
//        }
//        return httpHeaderMap;  
//    }  
	
	/**
	 * 将请求的数据进行封装
	 * @param parameMap
	 * @return
	 */
	public static UrlEncodedFormEntity parameEntityPairs(Map<String,String[]> parameMap){
		List<NameValuePair> valuePairs = new LinkedList<NameValuePair>();
		for (Entry<String,String[]> entry : parameMap.entrySet()){
			for (String s : entry.getValue()){
				valuePairs.add(new BasicNameValuePair(String.valueOf(entry.getKey()), s));
			}
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(valuePairs, Consts.UTF_8);
		return entity;
	}
	
	/**
	 * 将请求的数据进行封装
	 * @param parameMap
	 * @return
	 */
	public static String parameStrPairs(Map<String,String[]> parameMap){
		StringBuffer queryString=new StringBuffer();
		for (Entry<String,String[]> entry : parameMap.entrySet()){
			for (String s : entry.getValue()){
				queryString.append("&").append(entry.getKey()).append("=").append(s);
			}
		}
		return queryString.length()>1?queryString.substring(1):"";
	}	
	
	/**
	 * 将传递的参数转换为Map集合
	 * @param queryString
	 * @return
	 */
	public static Map<String,String[]> paramePairs(String queryString){
		Map<String,String[]> parameMap = new HashMap<>();
		queryString = "q=*%3A*&fq=id%3A3FAB0BB8BF4F4F60A60FB6DA0CD0AC2B&wt=json&indent=true";
		String [] parames = queryString.split("&");
		String [] ps = null;
		String [] vs = null;
		for (String s : parames){
			ps = s.split("=");
			if (ps != null && ps.length == 2){
				if (ps[1]!=null && ps[1].indexOf(",")!=-1){
					vs = ps[1].split(",");
				}else {
					vs = new String[]{ps[1]};
				}
				parameMap.put(ps[0], vs);
			}
		}
		return parameMap;
	}
	
	/**
	 * 返回？后的查询条件
	 * @param uri
	 * @return
	 */
	public static String getQueryString(final String uri){
		int l = uri.indexOf("?");
		if (l == -1){
			return "";
		}
		return uri.substring(l + 1);
	}
	
	/**
	 * 默认最HTTP路径中最后一个/后为服务标识
	 * @param uri
	 * @return
	 */
	public static String getServerLabel(String uri){
		int l = uri.indexOf("?");
		if (l == -1){
			l = uri.length();
		}
		uri = uri.substring(0,l);
		return uri.substring(uri.lastIndexOf("/") + 1);
	}
}
