package com.demo.ActiveMQ.controlller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerResource {

	
	@JmsListener(destination = "inmemory.queue")
	public void listener(String message) {
		System.out.println("Received message "+message);
	}
}
