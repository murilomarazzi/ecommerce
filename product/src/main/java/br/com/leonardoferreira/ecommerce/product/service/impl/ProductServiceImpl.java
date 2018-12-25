package br.com.leonardoferreira.ecommerce.product.service.impl;

import br.com.leonardoferreira.ecommerce.product.domain.Product;
import br.com.leonardoferreira.ecommerce.product.domain.UserInfo;
import br.com.leonardoferreira.ecommerce.product.domain.request.ProductRequest;
import br.com.leonardoferreira.ecommerce.product.domain.response.ProductResponse;
import br.com.leonardoferreira.ecommerce.product.exception.ResourceNotFound;
import br.com.leonardoferreira.ecommerce.product.mapper.ProductMapper;
import br.com.leonardoferreira.ecommerce.product.repository.ProductRepository;
import br.com.leonardoferreira.ecommerce.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponse> findAll(final Pageable pageable) {
        log.info("Method=findAll, pageable={}", pageable);

        Page<Product> products = productRepository.findAll(pageable);

        return productMapper.productsToResponses(products);
    }

    @Override
    @Transactional
    public Long create(final ProductRequest productRequest, final UserInfo userInfo) {
        log.info("Method=create, productRequest={}", productRequest);

        Product product = productMapper.requestToProduct(productRequest, userInfo);
        productRepository.save(product);

        return product.getId();
    }

    @Override
    @Transactional
    public void update(final Long id, final ProductRequest productRequest, final UserInfo userInfo) {
        log.info("Method=update, productRequest={}, userInfo={}", productRequest, userInfo);

        Product product = productRepository.findById(id)
                .orElseThrow(ResourceNotFound::new);

        productMapper.updateFromRequest(product, productRequest, userInfo);

        productRepository.save(product);

    }

    @Override
    @Transactional
    public void delete(final Long id) {
        log.info("Method=delete, id={}", id);

        productRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse findById(final Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(ResourceNotFound::new);
        return productMapper.productToResponse(product);
    }
}
