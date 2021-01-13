package com.eStore.bear.product.dto;

import com.eStore.bear.product.utils.ProductConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Document(collection = "Products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private String id;

    @NotNull(message = ProductConstants.PRODUCT_NAME)
    private String name;

    @NotNull(message = ProductConstants.PRODUCT_CATEGORY)
    private Category category;

    @Min(0)
    @NotNull(message = ProductConstants.PRODUCT_PRICE)
    private double price;

    private String currency;

    @Max(100)
    @Min(0)
    private double discount;
    private String discountDescription;
    private List<String> imageURLs;

}
