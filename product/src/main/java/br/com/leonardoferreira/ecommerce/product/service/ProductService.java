package br.com.leonardoferreira.ecommerce.product.service;

import br.com.leonardoferreira.ecommerce.product.domain.Product;
import br.com.leonardoferreira.ecommerce.product.domain.UserInfo;
import br.com.leonardoferreira.ecommerce.product.domain.request.ProductRequest;
import br.com.leonardoferreira.ecommerce.product.domain.response.ProductResponse;
import br.com.leonardoferreira.ecommerce.product.exception.ResourceNotFound;
import br.com.leonardoferreira.ecommerce.product.mapper.ProductMapper;
import br.com.leonardoferreira.ecommerce.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {
        log.info("Method=findAll");

        return productMapper.productsToResponses((List<Product>) productRepository.findAll());
    }

    @Transactional
    public Long create(final ProductRequest productRequest, final UserInfo userInfo) {
        log.info("Method=create, productRequest={}", productRequest);

        Product product = productMapper.requestToProduct(productRequest, userInfo);
        productRepository.save(product);

        return product.getId();
    }

    @Transactional
    public void update(final Long id, final ProductRequest productRequest, final UserInfo userInfo) {
        log.info("Method=update, productRequest={}, userInfo={}", productRequest, userInfo);

        Product product = productRepository.findById(id)
                .orElseThrow(ResourceNotFound::new);

        productMapper.updateFromRequest(product, productRequest, userInfo);

        productRepository.save(product);

    }

    @Transactional
    public void delete(final Long id) {
        log.info("Method=delete, id={}", id);

        productRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ProductResponse findById(final Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(ResourceNotFound::new);
        return productMapper.productToResponse(product);
    }
}
