package br.com.leonardoferreira.ecommerce.product.config;

import br.com.leonardoferreira.ecommerce.product.resolver.UserInfoArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private UserInfoArgumentResolver userInfoArgumentResolver;

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userInfoArgumentResolver);
    }
}
