package com.eStore.bear.product.service;

import com.eStore.bear.product.dto.Product;
import com.eStore.bear.product.exception.CurrencyValidException;
import com.eStore.bear.product.exception.ProductValidException;
import com.eStore.bear.product.repository.ProductRepository;
import com.eStore.bear.product.response.ProductResponse;
import com.eStore.bear.product.service.config.ProductConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductRepository productRepository;

    private ProductConfiguration productConfiguration;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        productConfiguration = new ProductConfiguration();
        productConfiguration.setCurrencies(List.of("EUR", "FCFA", "USD"));
        productService = new ProductService(productRepository, productConfiguration);
    }

    @Test
    void addProductValidExceptionTest() {
        Product product = new Product();
        product.setPrice(0.0);
        product.setDiscount(10);
        assertThrows(ProductValidException.class, () -> productService.addProduct(product));
    }

    @Test
    void addProductCurrencyValidExceptionTest() {
        Product product = new Product();
        product.setPrice(100);
        product.setDiscount(80);
        product.setCurrency("INR");
        assertThrows(CurrencyValidException.class, () -> productService.addProduct(product));
    }

    @Test
    void addProductTest() {
        Product product = new Product();
        product.setName("Product Name Test");
        product.setPrice(1000.0);
        product.setDiscount(80);
        product.setCurrency("EUR");
        when(productRepository.save(any(Product.class))).thenReturn(product);
        ProductResponse response = productService.addProduct(product);
        ProductResponse expected = new ProductResponse(HttpStatus.OK, product.getName() + " added successfully in the Store");
        assertThat(expected).isEqualTo(response);
    }

    @Test
    void listAllProductsEmptyTest() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(ProductValidException.class, () -> productService.listAllProducts());
    }

    @Test
    void listAllProductsTest() {
        when(productRepository.findAll()).thenReturn(List.of(new Product(), new Product()));
        List<Product> products = productService.listAllProducts();
        assertThat(products).isNotNull();
        assertThat(2).isEqualTo(products.size());
    }

    @Test
    void productCategoryListEmptyTest() {
        when(productRepository.findByCategory(anyString())).thenReturn(Collections.emptyList());
        assertThrows(ProductValidException.class, () -> productService.productCategoryList("test"));
    }

    @Test
    void productCategoryTest() {
        when(productRepository.findByCategory(anyString())).thenReturn(List.of(new Product(), new Product()));
        List<Product> products = productService.productCategoryList("test");
        assertThat(products).isNotNull();
        assertThat(2).isEqualTo(products.size());
    }

    @Test
    void productByIdEmptyTest() {
        assertThrows(ProductValidException.class, () -> productService.productById("123456789"));
    }

    @Test
    void productByIdTest() {
        Product product = new Product();
        product.setName("Test");
        product.setPrice(1000.0);
        product.setDiscount(10.0);
        product.setCurrency("EUR");
        when(productRepository.findById(anyString())).thenReturn(Optional.of(product));
        Product actualProduct = productService.productById("123456789");
        assertThat(actualProduct).isEqualTo(product);
    }
}