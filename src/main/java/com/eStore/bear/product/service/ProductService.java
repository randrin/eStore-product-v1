package com.eStore.bear.product.service;

import com.eStore.bear.product.dto.Product;
import com.eStore.bear.product.exception.CurrencyValidException;
import com.eStore.bear.product.exception.ProductValidException;
import com.eStore.bear.product.repository.ProductRepository;
import com.eStore.bear.product.response.ProductResponse;
import com.eStore.bear.product.service.config.ProductConfiguration;
import com.eStore.bear.product.utils.ProductConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConfiguration productConfiguration;

    public ProductService(ProductRepository productRepository, ProductConfiguration productConfiguration) {
        this.productRepository = productRepository;
        this.productConfiguration = productConfiguration;
    }

    public ProductResponse addProduct(Product product) {

        if (product.getPrice() == 0 && product.getDiscount() > 0) {
            throw new ProductValidException(ProductConstants.PRODUCT_DISCOUNT);
        }

        if (!productConfiguration.getCurrencies().contains(product.getCurrency())) {
            throw new CurrencyValidException(ProductConstants.PRODUCT_CURRENCY + productConfiguration.getCurrencies());
        }

        productRepository.save(product);
        return new ProductResponse(HttpStatus.OK, product.getName() + " added successfully in the Store");
    }

    public List<Product> listAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products == null || products.isEmpty()) {
            throw new ProductValidException("Products not found in the Store");
        }
        return products;
    }

    public List<Product> productCategoryList(String category) {
        List<Product> productsByCategory = productRepository.findByCategory(category);

        if(productsByCategory == null || productsByCategory.isEmpty()) {
            throw new ProductValidException("Products not found for the category" + category + " in the Store");
        }
        return productsByCategory;
    }

    public Product productById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductValidException("Product not found for id - " + id));
    }

    public ProductResponse updateProduct(Product product) {
        Optional<Product> prd =  productRepository.findById(product.getId());
        if(!prd.isPresent()) {
            return new ProductResponse(HttpStatus.NOT_FOUND, "Product to be updated isn't found to the Store.");
        }
        productRepository.save(product);
        return new ProductResponse(HttpStatus.OK, "Product updated successfully.");
    }

    public ProductResponse deleteProductById(String id) {
        Optional<Product> product =  productRepository.findById(id);
        if(!product.isPresent()) {
            return new ProductResponse(HttpStatus.NOT_FOUND, "Product to be deleted isn't found to the Store.");
        }
        productRepository.deleteById(id);
        return new ProductResponse(HttpStatus.OK, "Product deleted successfully.");
    }
}
