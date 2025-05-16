package com.codingdojo.choretracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;//
@SpringBootApplication
@EntityScan(basePackages = "com.codingdojo.choretracker.models")//
public class ChoreTrackerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChoreTrackerAppApplication.class, args);
	}

}
