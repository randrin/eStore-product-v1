package com.eStore.bear.product.service;

import com.eStore.bear.product.dto.Product;
import com.eStore.bear.product.exception.CurrencyValidException;
import com.eStore.bear.product.exception.ProductValidException;
import com.eStore.bear.product.repository.ProductRepository;
import com.eStore.bear.product.service.config.ProductConfiguration;
import com.eStore.bear.product.utils.ProductConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConfiguration productConfiguration;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String addProduct(Product product) {

        if(product.getPrice() == 0 && product.getDiscount() > 0) {
            throw new ProductValidException(ProductConstants.PRODUCT_DISCOUNT);
        }

        if(!productConfiguration.getCurrencies().contains(product.getCurrency())) {
            throw new CurrencyValidException(ProductConstants.PRODUCT_CURRENCY + productConfiguration.getCurrencies());
        }

        productRepository.save(product);
        return "product added successfully.";
    }

    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> productCategoryList(String category) {

        return productRepository.findByCategory(category);
    }

    public Product productById(String id) {
        return productRepository.findById(id).get();
    }

    public String updateProduct(Product product) {
        productRepository.save(product);
        return "product updated successfully";
    }

    public String deleteProductById(String id) {
        productRepository.deleteById(id);
        return "product delected successfully.";
    }
}
