package com.cdprojektred.javatest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties
@SpringBootApplication
public class JavatestApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavatestApplication.class, args);
    }
}
