package io.bitryon.example.web.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

import io.bitryon.logger.provider.LoggerProvider;
import io.bitryon.logger.spring.AutoConfigurationBitryonLogger;
import io.bitryon.logger.spring.LoggingHttpRequestWebFilter;
import io.bitryon.spring.rmi.http.prodiver.AutoConfigurationServiceProvider;
import jakarta.annotation.Resource;

@Configuration
@Import(value= {
	AutoConfigurationBitryonLogger.class, // to load the configs from application.yml for logging related beans

	AutoConfigurationServiceProvider.class, // Turn on RPC service provider
})
public class ExampleWebServerConfiguration {

	@Resource
	LoggerProvider bitryonLoggerProvider;

	// read step log id from last app/service/http request header to form traces, and log http payload for specific paths
	@Bean
	FilterRegistrationBean<LoggingHttpRequestWebFilter> loggingResetInFilter() {
		FilterRegistrationBean<LoggingHttpRequestWebFilter> reg = new FilterRegistrationBean<>(
				new LoggingHttpRequestWebFilter(bitryonLoggerProvider, true));
		reg.setOrder(Ordered.HIGHEST_PRECEDENCE);
		reg.addUrlPatterns("/api", "/api/*", "/remote_api/*");
		return reg;
	}
}
