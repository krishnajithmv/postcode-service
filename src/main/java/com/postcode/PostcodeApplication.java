package com.postcode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PostcodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostcodeApplication.class, args);
	}

}
