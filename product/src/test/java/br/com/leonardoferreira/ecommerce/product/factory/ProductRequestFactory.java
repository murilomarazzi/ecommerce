package br.com.leonardoferreira.ecommerce.product.factory;

import br.com.leonardoferreira.ecommerce.product.domain.request.ProductRequest;
import br.com.leonardoferreira.ecommerce.product.factory.base.NonPersistentFactory;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductRequestFactory extends NonPersistentFactory<ProductRequest> {

    @Autowired
    private Faker faker;

    @Override
    protected ProductRequest getDefault() {
        ProductRequest request = new ProductRequest();

        request.setName(faker.commerce().productName());
        request.setPrice(new BigDecimal(faker.commerce().price()));

        return request;
    }

    @Override
    protected ProductRequest getEmpty() {
        return new ProductRequest();
    }

}
