package io.bitryon.example.web.kafka;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import io.bitryon.logger.Logger;
import io.bitryon.logger.PreDefinition;
import io.bitryon.logger.provider.LoggerFactory;

public class ProducerLoggerInterceptor implements ProducerInterceptor<String, String> {
	private static final Logger logger = LoggerFactory.getLogger();

	@Override
	public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
		// CRITICAL .getNextStepLogId()
		String nextSteplogId = logger.getNextStepLogId();
		record.headers().add(PreDefinition.HTTP_HEADER_STEP_LOG_ID, nextSteplogId.getBytes(PreDefinition.CharsetEncoding));
		return record;
	}

	@Override
	public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
	}

	@Override
	public void close() {
	}

	@Override
	public void configure(Map<String, ?> configs) {
	}
}
