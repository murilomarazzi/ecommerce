package br.com.leonardoferreira.ecommerce.product.domain.response;

import lombok.Data;

@Data
public class ProductResponse {

    private Long id;

    private String name;

    private String price;

    private String createdAt;

    private String updatedAt;

}
