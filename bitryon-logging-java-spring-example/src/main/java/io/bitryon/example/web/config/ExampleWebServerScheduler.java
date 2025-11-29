package io.bitryon.example.web.config;

import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;

import io.bitryon.example.web.service.SMTPEmailScheduler;
import io.bitryon.example.web.service.SMTPEmailService;
import io.bitryon.logger.Logger;
import io.bitryon.logger.provider.LoggerProvider;
import jakarta.annotation.Resource;

@Configuration
@EnableScheduling
public class ExampleWebServerScheduler {

	@Resource
	SMTPEmailScheduler emailScheduler;

	@Resource
	SMTPEmailService smtpEmailService;
	
	@Resource
	Logger logger;

	@Resource
	LoggerProvider bitryonLoggerProvider;
	
//	@Bean("JobSchedulerPool")
//	ThreadPoolTaskScheduler JobSchedulerPool() {
//		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
//		threadPoolTaskScheduler.setPoolSize(2);
//		threadPoolTaskScheduler.setThreadNamePrefix("ExampleWebServerScheduler-");
//		return threadPoolTaskScheduler;
//	}

	//@Scheduled(fixedDelay = 15 * 1000) 
	public void sendEmails() {
		for(int i=0; i<10; i++) {
			smtpEmailService.sendVerificationUrl("somebodyemailnotknowhoisabcdxxx@whoismail.unknown", "http://localhost/verifyyouremail");
			logger.reset();
		}
	}
	
	//@Scheduled(fixedDelay = 5*1000, initialDelay = 5*1000) 
	public void runEmailRunnable() {
		emailScheduler.runMailRunnable();
	}

	private boolean changed = false;
	//@Scheduled(fixedDelay = 60 * 1000) // print log head in logs if want to see boot-info and configures changes after all resource loaded
	public void refreshLogHeadIfNeeded() throws IOException {
		if (changed) { return; }// if only need once.
		changed = bitryonLoggerProvider.refresh();
	}
}
