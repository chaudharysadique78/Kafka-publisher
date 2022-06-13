package com.kafkaapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KafkaPublisherApplication {
	@Autowired
	private KafkaTemplate<String, Object> template;

	private String topic = "spring-kafka";

	//This is used when we want to send simple string no configuration required for this
	@GetMapping("/publish/{name}")
	public String publishMessage(@PathVariable String name) {
		template.send(topic, "Hii " + name);
		return "Data has been send";
	}
	
	// This is used when need to send object we need to create configuration class for kafka,(KafkaPublisherConfig) class created for this
	@GetMapping("/publishJson")
	public String publishMessage() {
		User user=new User(101, "Sadik", new String[] {"Mumbai","Sakinaka","Netaji nager"});
		User user2=new User(102, "Sadik2", new String[] {"Mumbai","Sakinaka","Netaji nager"});
		User user3=new User(103, "Sadik3", new String[] {"Mumbai","Sakinaka","Netaji nager"});
		User user4=new User(104, "Sadik4", new String[] {"Mumbai","Sakinaka","Netaji nager"});
		template.send(topic,user);
		template.send(topic,user2);
		template.send(topic,user3);
		template.send(topic,user4);
		return "JSON Data has been published";
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaPublisherApplication.class, args);
	}

}
