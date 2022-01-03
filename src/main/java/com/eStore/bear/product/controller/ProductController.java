package com.eStore.bear.product.controller;

import com.eStore.bear.product.dto.Product;
import com.eStore.bear.product.response.ProductResponse;
import com.eStore.bear.product.service.ProductService;
import com.eStore.bear.product.utils.ProductEndpoints;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(ProductEndpoints.PRODUCT_ROOT)
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(ProductEndpoints.SAVE_PRODUCT)
    ResponseEntity<Product> addProduct(@RequestBody @Valid Product product){

        ProductResponse response = productService.addProduct(product);

        log.info("Product added status - {}", response.getMessage());

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping(ProductEndpoints.ALL_PRODUCTS)
    List<Product> productList(){
        return productService.listAllProducts();
    }

    @GetMapping(ProductEndpoints.ALL_PRODUCTS + "/{category}")
    List<Product> productCategoryList(@PathVariable String category){
        return productService.productCategoryList(category);
    }

    @GetMapping(ProductEndpoints.PRODUCT + "/{id}")
    Product productById(@PathVariable String id){
        return productService.productById(id);
    }

    @PutMapping(ProductEndpoints.PRODUCT_UPDATE)
    ProductResponse updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @DeleteMapping(ProductEndpoints.PRODUCT + "/{id}")
    ProductResponse deleteProductById(@PathVariable String id){
        return productService.deleteProductById(id);
    }
}
