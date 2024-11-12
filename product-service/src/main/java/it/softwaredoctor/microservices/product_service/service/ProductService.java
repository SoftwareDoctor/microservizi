package it.softwaredoctor.microservices.product_service.service;

import it.softwaredoctor.microservices.product_service.dto.ProductRequest;
import it.softwaredoctor.microservices.product_service.dto.ProductResponse;
import it.softwaredoctor.microservices.product_service.model.Product;
import it.softwaredoctor.microservices.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

//    public ProductResponse saveProduct(ProductRequest productRequest) {
//        Product product = new Product();
//        product.setName(productRequest.id());
//        product.setDescription(productRequest.name());
//        product.setDescription(productRequest.description());
//        product.setPrice(productRequest.price());
//        Product savedProduct = productRepository.save(product);
//        ProductResponse productResponse = ProductResponse.builder()
//                .name(savedProduct.getName())
//                .description(savedProduct.getDescription())
//                .price(savedProduct.getPrice())
//                .build();
//
//        log.info("Product saved successfully with ID: {}", savedProduct.getId());
//        return productResponse;
//    }

    public ProductResponse saveProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        log.info("Product saved successfully with ID: {}", product.getId());
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }


    public List<ProductResponse> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice()))
                .toList();
    }
}
