package com.vikram.openconnect.login.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;


public class HttpClientUtil {
	
	private static final String USER_AGENT = "Mozilla/5.0";

	public String getJSONResponse(String url) throws HttpException{
				
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
 
		// add request header
		request.addHeader("User-Agent", USER_AGENT);
 
		try {
			return getResponse(client, request);
		} catch (Exception e) {
			throw new HttpException();
		}		
	}
	
	public String getJSONResponse(String url,Map<String,String> postParams) throws HttpException{
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);
		
		addParams(postParams, request);
	 
 
		// add request header
		request.addHeader("User-Agent", USER_AGENT);
 
		try {
			return getResponse(client, request);
		} catch (Exception e) {
			throw new HttpException("",e);
		}		
	
	}

	private void addParams(Map<String, String> postParams, HttpPost request)
			throws HttpException {
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		for(Map.Entry<String, String> entry:postParams.entrySet()){
			urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		
		
		try {
			request.setEntity(new UrlEncodedFormEntity(urlParameters));
		} catch (UnsupportedEncodingException e1) {
			throw new HttpException("",e1);
		}
	}

	private String getResponse(HttpClient client, HttpRequestBase request)
			throws IOException, ClientProtocolException {
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader(
		        new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		return result.toString();
	}
	

}
