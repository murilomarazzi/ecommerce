package br.com.leonardoferreira.ecommerce.product.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
@EnableFeignClients("br.com.leonardoferreira.ecommerce.product.client")
public class FeignConfig {
}
