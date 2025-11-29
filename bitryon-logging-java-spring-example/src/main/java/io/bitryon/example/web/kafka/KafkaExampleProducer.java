package io.bitryon.example.web.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import io.bitryon.example.web.service.MedicService;
import jakarta.annotation.Resource;

@Service
public class KafkaExampleProducer {
	
	@Resource
	MedicService medicService;
	
	private final KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	public KafkaExampleProducer(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(String message) {
		medicService.callSelfInvocation(message);
		kafkaTemplate.send("demo-topic", message);
	}
}
