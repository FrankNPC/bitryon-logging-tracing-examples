package io.bitryon.example.web.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import io.bitryon.example.web.kafka.ConsumerLoggerInterceptor;
import io.bitryon.example.web.kafka.ProducerLoggerInterceptor;

@Configuration
public class KafkaBeansConfiguration {

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
		return new KafkaTemplate<>(producerFactory);
	}

	@Bean
	public ProducerFactory<String, String> producerFactory(KafkaProperties properties) {
		Map<String, Object> config = new HashMap<>(properties.buildProducerProperties());
		config.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, 
				ProducerLoggerInterceptor.class.getName());
		return new DefaultKafkaProducerFactory<>(config);
	}
	
	@Bean
	public ConsumerFactory<String, String> consumerFactory(KafkaProperties properties) {
		Map<String, Object> config = new HashMap<>(properties.buildConsumerProperties());
		config.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, 
				ConsumerLoggerInterceptor.class.getName());
		return new DefaultKafkaConsumerFactory<>(config);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
			ConsumerFactory<String, String> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

}
