package br.com.brenohff.later;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class LaterBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaterBackendApplication.class, args);
	}
}
