package com.asimio.api.demo.main;

import com.asimio.api.demo.reader.CsvReader;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.asimio.api.demo.main.HttpHostsConfiguration.HttpHostConfiguration;

@SpringBootApplication(scanBasePackages = { "com.asimio.api.demo.main", "com.asimio.api.demo.rest","com.asimio.api.demo.reader","com.asimio.api.demo.cache"})
public class ResttemplateTroubleshootingSvc2Application {

	@Autowired
	private HttpHostsConfiguration httpHostConfiguration;

	@Bean
	public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
		PoolingHttpClientConnectionManager result = new PoolingHttpClientConnectionManager();
		result.setMaxTotal(this.httpHostConfiguration.getMaxTotal());
		// Default max per route is used in case it's not set for a specific route
		result.setDefaultMaxPerRoute(this.httpHostConfiguration.getDefaultMaxPerRoute());
		// and / or
		if (CollectionUtils.isNotEmpty(this.httpHostConfiguration.getMaxPerRoutes())) {
			for (HttpHostConfiguration httpHostConfig : this.httpHostConfiguration.getMaxPerRoutes()) {
				HttpHost host = new HttpHost(httpHostConfig.getHost(), httpHostConfig.getPort(), httpHostConfig.getScheme());
				// Max per route for a specific host route
				result.setMaxPerRoute(new HttpRoute(host), httpHostConfig.getMaxPerRoute());
			}
		}
		return result;
	}

	@Bean
	public RequestConfig requestConfig() {
		RequestConfig result = RequestConfig.custom()
			.setConnectionRequestTimeout(15000)
			.setConnectTimeout(15000)
			.setSocketTimeout(15000)
			.build();
		return result;
	}

	@Bean
	public CloseableHttpClient httpClient(PoolingHttpClientConnectionManager poolingHttpClientConnectionManager, RequestConfig requestConfig) {
		CloseableHttpClient result = HttpClientBuilder
			.create()
			.setConnectionManager(poolingHttpClientConnectionManager)
			.setDefaultRequestConfig(requestConfig)
			.build();
		return result;
	}

	@Bean
	public RestTemplate restTemplate(HttpClient httpClient) {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		return new RestTemplate(requestFactory);
	}

	public static void main(String[] args) {
		SpringApplication.run(ResttemplateTroubleshootingSvc2Application.class, args);
	}
}
