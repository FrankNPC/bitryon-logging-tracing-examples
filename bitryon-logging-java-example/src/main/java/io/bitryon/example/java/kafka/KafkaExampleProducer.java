package io.bitryon.example.java.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import io.bitryon.example.java.service.MedicService;

public class KafkaExampleProducer {
	
	MedicService medicService = new MedicService();
	
	KafkaProducer<String, String> producer;

	public KafkaExampleProducer() {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

		props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, ProducerLoggerInterceptor.class.getName());

		producer = new KafkaProducer<>(props);
	}
	
	public void sendMessage(String message) {
		medicService.callSelfInvocation(message);

		ProducerRecord<String, String> record = new ProducerRecord<>("demo-topic", "key1", message);
		producer.send(record);
	}
}
