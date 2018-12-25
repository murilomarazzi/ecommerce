package br.com.leonardoferreira.ecommerce.product.factory;

import br.com.leonardoferreira.ecommerce.product.domain.Product;
import br.com.leonardoferreira.ecommerce.product.factory.base.SpringDataFactory;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductFactory extends SpringDataFactory<Product> {

    @Autowired
    private Faker faker;

    @Override
    protected Product getDefault() {
        Product product = new Product();

        product.setName(faker.commerce().productName());
        product.setPrice(new BigDecimal(faker.commerce().price()));

        return product;
    }

    @Override
    protected Product getEmpty() {
        return new Product();
    }

}
