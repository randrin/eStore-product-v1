package com.eStore.bear.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private Integer id;
    private String name;
    private Category category;
    private double price;
    private String currency;
    private double discount;
    private String discountDescription;
    private List<String> imageURLs;

}
