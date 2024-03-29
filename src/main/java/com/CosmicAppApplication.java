package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CosmicAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CosmicAppApplication.class, args);
    }

    @Bean
    PasswordEncoder getPasswordEncode() {
        return new BCryptPasswordEncoder();
    }
}


