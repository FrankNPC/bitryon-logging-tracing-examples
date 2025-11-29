package io.bitryon.example.web.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import io.bitryon.example.web.service.rpc.UserService;
import jakarta.annotation.Resource;

@Service
public class KafkaExampleConsumer {
	
	@Resource
	UserService userService;

	@KafkaListener(topics = "demo-topic", groupId = "demo-group")
	public void listen(String message) {
		userService.getBySessionId(message);
	}
}
