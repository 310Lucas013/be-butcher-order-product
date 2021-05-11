package com.lucas.product.models.dto;

import com.lucas.product.models.ProductCategory;
import com.lucas.product.models.MeatType;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private String productNumber;
    private String name;
    private double price;
    private List<MeatType> meatTypes;
    private ProductCategory productCategory;
    private String shortName;

    public ProductDto() {
    }
}
