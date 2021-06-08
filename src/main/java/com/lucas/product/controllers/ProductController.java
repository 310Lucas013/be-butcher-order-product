package com.lucas.product.controllers;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lucas.product.models.HibernateProxyTypeAdapter;
import com.lucas.product.models.Product;
import com.lucas.product.models.dto.ProductDto;
import com.lucas.product.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {
        Gson gson = initiateGson();
        Product product = productService.createProduct(productDto);
        String result = gson.toJson(product);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        Gson gson = initiateGson();
        Product product = productService.getProductById(id);
        String result = gson.toJson(product);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getProducts() {
        Gson gson = initiateGson();
        List<Product> products = productService.getAllProducts();
        String result = gson.toJson(products);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private Gson initiateGson() {
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        boolean exclude = false;
                        try {
                            exclude = EXCLUDE.contains(f.getName());
                        } catch (Exception ignore) {
                        }
                        return exclude;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                });
        return b.create();
    }

    private static final List<String> EXCLUDE = new ArrayList<>() {{

    }};
}
