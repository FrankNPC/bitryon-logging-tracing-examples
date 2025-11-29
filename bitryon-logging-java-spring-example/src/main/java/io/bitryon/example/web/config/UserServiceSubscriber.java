package io.bitryon.example.web.config;


import java.util.concurrent.TimeUnit;

import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import io.bitryon.example.web.service.rpc.UserService;
import io.bitryon.logger.annotation.LoggingUnit;
import io.bitryon.logger.provider.LoggerProvider;
import io.bitryon.logger.spring.LoggingHttpClientHeaderWriterInterceptor;
import io.bitryon.logger.spring.LoggingMethodPointcut;
import io.bitryon.spring.rmi.http.subscriber.AbstractServiceSubscriber;
import io.bitryon.spring.rmi.http.subscriber.ServiceClientTemplate;
import jakarta.annotation.Resource;

@Configuration
public class UserServiceSubscriber<T> extends AbstractServiceSubscriber implements ServiceClientTemplate<T> {

	@Value("${example.service.user.host}")
	private String profileHost;

	@Override
	public String getBaseUrl() {
		return profileHost;
	}

	@Resource
	LoggingMethodPointcut loggingMethodPointcut;

	@Resource
	LoggerProvider bitryonLoggerProvider;
	
//	private Advisor[] advisors;
//	@Override
//	public Advisor[] getAdvisors() {
//		if (advisors==null) {
//			loggingMethodPointcut.getLoggingTraceMethodInterceptor().addClasses(
//					LoggingUnit.Builder().catchPackages("io.bitryon.example.web.service").build(),
//
//					UserService.class);
//
//			advisors = new Advisor [] { loggingMethodPointcut };
//		}
//		return advisors;
//	}
	
	private CloseableHttpClient httpClient() {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(1024);
		connectionManager.setDefaultMaxPerRoute(1024);
		connectionManager.setConnectionConfigResolver(route -> {
			return ConnectionConfig.custom()
					.setConnectTimeout(15, TimeUnit.SECONDS)
					.setSocketTimeout(15, TimeUnit.SECONDS)
					.setTimeToLive(2, TimeUnit.MINUTES)
					.setValidateAfterInactivity(30, TimeUnit.SECONDS)
					.build();
		});

		return HttpClients.custom()
				.setConnectionManager(connectionManager)
				.setConnectionManagerShared(true)
				.build();
	}
	
	private RestClient restClient;
	@Override
	public RestClient getRestClient() {
		return restClient = restClient!=null ? restClient : RestClient
			.builder(new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient())))
			.baseUrl(getBaseUrl())
			
			// write step log id to the http request header to the next app/service to form traces
			.requestInterceptor(new LoggingHttpClientHeaderWriterInterceptor(bitryonLoggerProvider))
			.build();
	}

	@Bean
	UserService getUserRPCService() {
		return (UserService) loggingMethodPointcut.proxyBeanInstance(// Pointcut to intercept the methods if log on interfaces or class without @Logging. 
				LoggingUnit.Builder().catchPackages("io.bitryon.example.web.service").build(),  //catchLoggingMethod(true)
					this.getProxyFactoryBean(UserService.class, this), UserService.class);
//		return this.getProxyFactoryBean(UserService.class, this);
	}

//	@Bean
//	UserServiceImpl getUserRPCService(UserServiceImpl userService) { // to proxy a direct class without @Logging
////		UserServiceImpl userService = new UserServiceImpl();
//		return (UserServiceImpl) loggingMethodPointcut.proxyBeanInstance(LoggingUnit.Builder().build(), userService);
//	}
}
