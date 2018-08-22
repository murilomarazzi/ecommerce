package br.com.leonardoferreira.ecommerce.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<Product> findAll() {
        return Arrays.asList(
                new Product(1L, "Product A"),
                new Product(2L, "Product B"),
                new Product(3L, "Product C"),
                new Product(4L, "Product D"),
                new Product(5L, "Product E"));
    }

}
