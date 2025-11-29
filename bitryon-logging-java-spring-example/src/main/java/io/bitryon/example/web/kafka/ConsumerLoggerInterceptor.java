package io.bitryon.example.web.kafka;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.header.Header;

import io.bitryon.logger.Logger;
import io.bitryon.logger.PreDefinition;
import io.bitryon.logger.provider.LoggerFactory;

public class ConsumerLoggerInterceptor implements ConsumerInterceptor<String, String> {
	private static final Logger logger = LoggerFactory.getLogger();

	@Override
	public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
		records.forEach(record -> {
			Header header = record.headers().lastHeader(PreDefinition.HTTP_HEADER_STEP_LOG_ID);
			if (header!=null) {
				String stepLogId = new String(header.value(), PreDefinition.CharsetEncoding);
				logger.setStepLogId(stepLogId);
			}else {
				logger.reset();// reset to restart trace for each consume
			}
		});

		return records;
	}

	@Override
	public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
	}

	@Override
	public void close() {
	}

	@Override
	public void configure(Map<String, ?> configs) {
	}
}
