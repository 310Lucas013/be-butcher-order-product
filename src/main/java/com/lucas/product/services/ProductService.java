package com.lucas.product.services;

import com.lucas.product.models.Product;
import com.lucas.product.models.dto.ProductDto;
import com.lucas.product.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setProductNumber(productDto.getProductNumber());

        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.getById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
