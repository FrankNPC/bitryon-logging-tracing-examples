package io.bitryon.example.java.kafka;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import io.bitryon.example.java.service.rpc.UserServiceImpl;

public class KafkaExampleConsumer {
	
	UserServiceImpl userService = new UserServiceImpl();

	KafkaConsumer<String, String> consumer;

	public KafkaExampleConsumer() {
		Properties props = new Properties();
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "demo-group");
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

		props.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, ConsumerLoggerInterceptor.class.getName());

		consumer = new KafkaConsumer<>(props);
		consumer.subscribe(List.of("demo-topic"));
	}

	public void onMessaging() {
		while (true) {
			for (ConsumerRecord<String, String> record : consumer.poll(Duration.ofSeconds(1L))) {
				listen(record.value());
			}
		}
	}

	public void listen(String message) {
		userService.getBySessionId(message);
	}
}
