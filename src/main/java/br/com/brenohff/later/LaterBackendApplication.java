package br.com.brenohff.later;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LaterBackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LaterBackendApplication.class, args);
    }

    @Override
    public void run(String... args) {

    }
}
