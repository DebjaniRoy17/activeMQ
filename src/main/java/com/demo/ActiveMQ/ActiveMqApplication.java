package com.demo.ActiveMQ;

import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
@EnableJms
@Configuration
public class ActiveMqApplication {

	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;
	
	public static void main(String[] args) {
		SpringApplication.run(ActiveMqApplication.class, args);
	}

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("inmemory.queue");
		
	}
	
	//this is configured only when we are NOT using in-memory active MQ
	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(brokerUrl);
		return factory;
	}
	
	//this is configured only when we are NOT using in-memory active MQ
	@Bean
	public JmsTemplate jmsTemplate() {
		return new JmsTemplate(activeMQConnectionFactory());
	}
}
