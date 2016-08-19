package com.nateiot.core.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	private static PoolingHttpClientConnectionManager httpClientConnectionManager = null;
	private static HttpClientBuilder httpClientBuilder = null;
	private static RequestConfig requestConfig = null;

	private static int MAX_CONNECTION = 10;
	private static int DEFAULT_MAX_PER_ROUTE = 5;

	private static String IP = "www.gdgxwl.com";
	private static int PORT = 80;

	static {
		requestConfig = RequestConfig
				.custom()
				.build();

		HttpHost target = new HttpHost(IP, PORT);

		httpClientConnectionManager = new PoolingHttpClientConnectionManager();
		httpClientConnectionManager.setMaxTotal(MAX_CONNECTION);
		httpClientConnectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);
		httpClientConnectionManager.setMaxPerRoute(new HttpRoute(target), 20);

		httpClientBuilder = HttpClients.custom();
		httpClientBuilder.setConnectionManager(httpClientConnectionManager);
	}

	public static CloseableHttpClient getHttpClient() {
		return httpClientBuilder.build();
	}

	public static String doGet(String url, Map<String, String> params) {

		if (StringUtils.isEmpty(url)) {
			return null;
		}

		CloseableHttpClient httpClient = getHttpClient();
		RequestBuilder requestBuilder = RequestBuilder
				.get()
				.setUri(url)
				.setConfig(requestConfig);
		
		if (MapUtils.isNotEmpty(params)) {
			List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
			Set<Map.Entry<String, String>> entrySet = params.entrySet();
			for (Map.Entry<String, String> entry : entrySet) {
				String name = entry.getKey();
				String value = entry.getValue();
				NameValuePair nameValuePair = new BasicNameValuePair(name, value);
				paramsList.add(nameValuePair);
			}
			requestBuilder.addParameters(paramsList.toArray(new BasicNameValuePair[paramsList.size()]));
		}
		
		HttpUriRequest httpUriRequest = requestBuilder.build();

		String responseString = null;

		try {
			HttpResponse response = httpClient.execute(httpUriRequest);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				responseString = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
        } 

		return responseString;
	}

	public static String doPost(String url, Map<String, String> params, String jsonString) {
		

		if (StringUtils.isEmpty(url)) {
			return null;
		}

		CloseableHttpClient httpClient = getHttpClient();
		RequestBuilder requestBuilder = RequestBuilder
				.post()
				.setUri(url)
				.setConfig(requestConfig);
		
		if (MapUtils.isNotEmpty(params)) {
			List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
			Set<Map.Entry<String, String>> entrySet = params.entrySet();
			for (Map.Entry<String, String> entry : entrySet) {
				String name = entry.getKey();
				String value = entry.getValue();
				NameValuePair nameValuePair = new BasicNameValuePair(name, value);
				paramsList.add(nameValuePair);
			}
			requestBuilder.addParameters(paramsList.toArray(new BasicNameValuePair[paramsList.size()]));
		}
		
		if (StringUtils.isNotBlank(jsonString)) {
			StringEntity entity = new StringEntity(jsonString, ContentType.APPLICATION_JSON);
			requestBuilder.setEntity(entity);
		}
		
		HttpUriRequest httpUriRequest = requestBuilder.build();

		String responseString = null;

		try {
			HttpResponse response = httpClient.execute(httpUriRequest);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				responseString = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
        } 

		return responseString;
	}

	public static String doPostFile(String url, Map<String, String> params, List<NateiotBinaryBody> bins) {

		if (StringUtils.isEmpty(url)) {
			return null;
		}

		CloseableHttpClient httpClient = getHttpClient();
		RequestBuilder requestBuilder = RequestBuilder
				.post()
				.setUri(url)
				.setConfig(requestConfig);
		
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
		multipartEntityBuilder.setMode(HttpMultipartMode.RFC6532);
		
		if (MapUtils.isNotEmpty(params)) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				String name = entry.getKey();
				String value = entry.getValue();
				multipartEntityBuilder.addTextBody(name, value);
			}
		}
		
		if (CollectionUtils.isNotEmpty(bins)) {
			for (NateiotBinaryBody bin : bins) {
				String name = bin.getName();
				byte[] b = bin.getB();
				String filename = bin.getFilename();
				multipartEntityBuilder.addBinaryBody(name, b, ContentType.APPLICATION_OCTET_STREAM, filename);
				
				
			}
		}
		
		HttpEntity requestEntity = multipartEntityBuilder.build();
		requestBuilder.setEntity(requestEntity);
		
		HttpUriRequest httpUriRequest = requestBuilder.build();

		String responseString = null;

		try {
			HttpResponse response = httpClient.execute(httpUriRequest);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity responseEntity = response.getEntity();
				responseString = EntityUtils.toString(responseEntity, "UTF-8");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
        } 

		return responseString;
	}
}
