package com.kafka.producer;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;

@Service
public class MessageSender {
	
	public Logger logger = LoggerFactory.getLogger(MessageSender.class);
	KafkaTemplate<String, String> kafkaTemplate;

	public MessageSender(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void messageSender() throws InterruptedException {
		
		// To read real time stream data from wikimedia, we use event source
		String topic = "sender-messenger";
		
		EventHandler eventHandler = new MessageHandler(kafkaTemplate,topic);
		String url = "https://stream.wikimedia.org/v2/stream/recentchange";
		EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
		EventSource eventSource = builder.build();
		eventSource.start();
		TimeUnit.MINUTES.sleep(10);
	}
	
}
