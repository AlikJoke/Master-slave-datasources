package ru.project.master.slave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
public class MasterSlaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterSlaveApplication.class, args);
	}
}
