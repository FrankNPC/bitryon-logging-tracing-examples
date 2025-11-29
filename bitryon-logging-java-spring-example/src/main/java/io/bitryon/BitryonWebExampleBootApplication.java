package io.bitryon;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "io.bitryon.example" })
public class BitryonWebExampleBootApplication {
	public static void main(String[] args) {
//		// must load before everything. or add in META-INF/spring.factories 
//		io.bitryon.logger.boostrap.LoggingProxyInitiation.premain(null);
//		// start after the logging proxy to launch Opentelemetry
//		io.bitryon.logger.provider.LoggerFactory.getLoggerProvider(
//				new io.bitryon.logger.opentelemetry.OpenTelemetryLogDispatcher("http://127.0.0.1:8134/v1/logs"));
		new SpringApplicationBuilder(BitryonWebExampleBootApplication.class).run(args);
	}
}
