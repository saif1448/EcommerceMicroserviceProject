package com.sprac.product.services;

import com.sprac.product.ProductRespository;
import com.sprac.product.dtos.ProductPurchaseRequest;
import com.sprac.product.dtos.ProductPurchaseResponse;
import com.sprac.product.dtos.ProductRequest;
import com.sprac.product.dtos.ProductResponse;
import com.sprac.product.exceptions.ProductPurchaseException;
import com.sprac.product.models.Product;
import com.sprac.product.utils.ClassMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRespository respository;
    private final ClassMapper mapper;

    public Integer createProduct(ProductRequest request) {
        Product product = mapper.mapToProductEntity(request);
        return respository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {

        request
                .forEach(individualRequest -> {
                    if(!isPurchasable(individualRequest)){
                        throw new ProductPurchaseException("Product with ID::"+individualRequest.productId()+ " is not purchasable!");
                    }
                });

        return request.stream()
                .map(this::updatePriceAndSave)
                .map(mapper::maptToProductPurchaseResponse)
                .collect(Collectors.toList());
    }

    private boolean isPurchasable(ProductPurchaseRequest individualRequest){

        Product product = respository.findById(individualRequest.productId()).orElse(null);
        return product != null && product.getAvailableQuantity() >= individualRequest.quantity();
    }

    private Product updatePriceAndSave(ProductPurchaseRequest requestedProdct){

        Product product = respository.findById(requestedProdct.productId()).get();

        product.setAvailableQuantity(product.getAvailableQuantity()-requestedProdct.quantity());

        return respository.save(product);

    }

    public ProductResponse findById(Integer id) {

        return respository.findById(id)
                .map(mapper::mapToProductResponse)
                .orElseThrow(() -> new EntityNotFoundException(String.format("String not found with ID::%s",id)));
    }

    public List<ProductResponse> findAllProducts() {
        return respository.findAll()
                .stream()
                .map(mapper::mapToProductResponse)
                .collect(Collectors.toList());
    }
}
