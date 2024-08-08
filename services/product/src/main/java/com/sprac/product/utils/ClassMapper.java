package com.sprac.product.utils;

import com.sprac.product.dtos.ProductPurchaseResponse;
import com.sprac.product.dtos.ProductRequest;
import com.sprac.product.dtos.ProductResponse;
import com.sprac.product.models.Category;
import com.sprac.product.models.Product;
import org.springframework.stereotype.Service;

@Service
public class ClassMapper {

    public ProductResponse mapToProductResponse(Product productEntity){
        return new ProductResponse(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getAvailableQuantity(),
                productEntity.getPrice(),
                productEntity.getCategory().getName(),
                productEntity.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse maptToProductPurchaseResponse(Product product){
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getAvailableQuantity()
        );
    }

    public Product mapToProductEntity(ProductRequest request){
        Product product = Product.builder()
                .name(request.name())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .price(request.price())
                .category(
                        Category.builder()
                                .id(request.categoryId())
                                .build()
                )
                .build();
        return product;
    }
}
