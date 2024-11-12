/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-10-22 14:17:59
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-11-12 13:57:29
 * @FilePath: src/main/java/it/softwaredoctor/microservices/product_service/controller/ProductController.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.microservices.product_service.controller;

import it.softwaredoctor.microservices.product_service.dto.ProductRequest;
import it.softwaredoctor.microservices.product_service.dto.ProductResponse;
import it.softwaredoctor.microservices.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest request) {
         return productService.saveProduct(request);
    }
    
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProducts() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
           throw new RuntimeException(e);
        }
        return productService.getProducts();
    }
}
