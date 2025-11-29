package io.bitryon;

import io.bitryon.example.java.kafka.KafkaExampleConsumer;
import io.bitryon.example.java.kafka.KafkaExampleProducer;
import io.bitryon.logger.boostrap.LoggingMethodIntercepter;
import io.bitryon.logger.boostrap.LoggingProxyInitiation;
import io.bitryon.logger.provider.LoggerFactory;
import io.bitryon.logger.provider.LoggerProvider;

public class BitryonJavaExampleBootApplication {
	
	public static void main(String[] args) {
//		// must load before everything. or add in META-INF/spring.factories 
		// 1: load logger agent
		LoggingProxyInitiation.premain(null);

		// 2: load logger configure
		LoggerProvider provider = LoggerFactory.getLoggerProvider();
		
		// 3: load LoggingMethodIntercepter, In spring it doesn't need
		// Must do to setup LoggingMethodIntercepter and LoggerProvider
		new LoggingMethodIntercepter(provider);
		
		new KafkaExampleProducer().sendMessage("random message");
		
		KafkaExampleConsumer kafkaExampleConsumer = new KafkaExampleConsumer();
		kafkaExampleConsumer.onMessaging();
	}
}
