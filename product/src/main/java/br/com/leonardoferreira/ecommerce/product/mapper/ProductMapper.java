package br.com.leonardoferreira.ecommerce.product.mapper;

import br.com.leonardoferreira.ecommerce.product.domain.Product;
import br.com.leonardoferreira.ecommerce.product.domain.UserInfo;
import br.com.leonardoferreira.ecommerce.product.domain.request.ProductRequest;
import br.com.leonardoferreira.ecommerce.product.domain.response.ProductResponse;
import java.util.List;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    List<ProductResponse> productsToResponses(List<Product> products);

    @Mappings({ // @formatter:off
            @Mapping(target = "price",      numberFormat = "R$ #.00"),
            @Mapping(target = "createdAt",  dateFormat = "dd/MM/yyyy HH:mm"),
            @Mapping(target = "updatedAt",  dateFormat = "dd/MM/yyyy HH:mm")
    }) // @formatter:on
    ProductResponse productToResponse(Product response);

    @Mappings({ // @formatter:off
            @Mapping(target = "id",          ignore = true),
            @Mapping(target = "name",        source = "request.name"),
            @Mapping(target = "price",       source = "request.price"),
            @Mapping(target = "createdAt",   ignore = true),
            @Mapping(target = "updatedAt",   ignore = true),
            @Mapping(target = "changeAgent", source = "userInfo.username")
    }) // @formatter:on
    Product requestToProduct(ProductRequest request, UserInfo userInfo);

    @InheritConfiguration
    void updateFromRequest(@MappingTarget Product product, ProductRequest productRequest, UserInfo userInfo);

    default Page<ProductResponse> productsToResponses(final Page<Product> products) {
        List<ProductResponse> productResponses = productsToResponses(products.getContent());
        return new PageImpl<>(productResponses, products.getPageable(), products.getTotalElements());
    }
}
