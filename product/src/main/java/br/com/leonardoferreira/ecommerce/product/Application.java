package br.com.leonardoferreira.ecommerce.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
