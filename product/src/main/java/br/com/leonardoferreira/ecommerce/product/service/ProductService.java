package br.com.leonardoferreira.ecommerce.product.service;

import br.com.leonardoferreira.ecommerce.product.domain.UserInfo;
import br.com.leonardoferreira.ecommerce.product.domain.request.ProductRequest;
import br.com.leonardoferreira.ecommerce.product.domain.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductResponse> findAll(Pageable pageable);

    Long create(ProductRequest productRequest, UserInfo userInfo);

    void update(Long id, ProductRequest productRequest, UserInfo userInfo);

    void delete(Long id);

    ProductResponse findById(Long id);

}
