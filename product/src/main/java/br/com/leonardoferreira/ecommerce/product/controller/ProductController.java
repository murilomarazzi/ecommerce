package br.com.leonardoferreira.ecommerce.product.controller;

import br.com.leonardoferreira.ecommerce.product.domain.UserInfo;
import br.com.leonardoferreira.ecommerce.product.domain.request.ProductRequest;
import br.com.leonardoferreira.ecommerce.product.domain.response.ProductResponse;
import br.com.leonardoferreira.ecommerce.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductResponse> findAll() {
        return productService.findAll();
    }

    @PostMapping
    public HttpEntity<?> create(@RequestBody @Valid final ProductRequest productRequest,
                                final UserInfo userInfo) {
        Long id = productService.create(productRequest, userInfo);

        return ResponseEntity.created(URI.create("/products/" + id)).build();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable final Long id,
                                @RequestBody @Valid final ProductRequest productRequest,
                                final UserInfo userInfo) {
        productService.update(id, productRequest, userInfo);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable final Long id) {
        productService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable final Long id) {
        return productService.findById(id);
    }

}
