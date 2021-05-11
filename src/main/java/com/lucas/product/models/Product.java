package com.lucas.product.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "product_number")
    private String productNumber;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "MeatType", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(value = EnumType.STRING)
    private List<MeatType> meatTypes;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "product_category")
    private ProductCategory productCategory;
    @Column(name = "short_name")
    private String shortName;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "quantity_measurement")
    private QuantityMeasurement quantityMeasurement;

    public Product() {

    }
}
