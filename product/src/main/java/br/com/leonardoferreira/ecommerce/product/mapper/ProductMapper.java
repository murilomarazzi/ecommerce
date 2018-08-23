package br.com.leonardoferreira.ecommerce.product.mapper;

import br.com.leonardoferreira.ecommerce.product.domain.Product;
import br.com.leonardoferreira.ecommerce.product.domain.UserInfo;
import br.com.leonardoferreira.ecommerce.product.domain.request.ProductRequest;
import br.com.leonardoferreira.ecommerce.product.domain.response.ProductResponse;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    List<ProductResponse> productsToResponses(List<Product> products);

    @Mappings({
            @Mapping(target = "price",      numberFormat = "R$ #.00"),
            @Mapping(target = "createdAt",  dateFormat = "dd/MM/yyyy HH:mm"),
            @Mapping(target = "updatedAt",  dateFormat = "dd/MM/yyyy HH:mm")
    })
    ProductResponse productToResponse(Product response);

    @Mappings({
            @Mapping(target = "id",          ignore = true),
            @Mapping(target = "name",        source = "request.name"),
            @Mapping(target = "price",       source = "request.price"),
            @Mapping(target = "createdAt",   ignore = true),
            @Mapping(target = "updatedAt",   ignore = true),
            @Mapping(target = "changeAgent", source = "userInfo.username")
    })
    Product requestToProduct(ProductRequest request, UserInfo userInfo);

    @InheritConfiguration
    void updateFromRequest(@MappingTarget Product product, ProductRequest productRequest, UserInfo userInfo);
}
