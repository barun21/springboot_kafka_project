package com.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

	private Logger logger = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
	
	@KafkaListener(topics = "sender-messenger",groupId = "message-reader")
	public void receiveEvent(String eventMessage) {
		logger.info("event received {}", eventMessage);
	}
}
