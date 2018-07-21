package br.com.brenohff.later;

import java.util.ArrayList;
import java.util.List;

import br.com.brenohff.later.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.brenohff.later.models.LTCategory;
import br.com.brenohff.later.models.LTUser;
import br.com.brenohff.later.service.CategoryService;
import br.com.brenohff.later.service.UserService;

@SpringBootApplication
public class LaterBackendApplication implements CommandLineRunner {

    @Autowired
    private S3Service s3Service;

    public static void main(String[] args) {
        SpringApplication.run(LaterBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
