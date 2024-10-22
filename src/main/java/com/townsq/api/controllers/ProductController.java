package com.townsq.api.controllers;

import com.townsq.api.domain.product.Product;
import com.townsq.api.domain.product.ProductDTO;
import com.townsq.api.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping()
    @Transactional
    public ResponseEntity newProduct(@Valid @RequestBody ProductDTO data) {
        if(productRepository.findByName(data.name()).isPresent()) return ResponseEntity.badRequest().build();
        Product product = new Product(data.name(), data.description(), data.price());
        productRepository.save(product);

        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity list() {
        var products = productRepository.findAll().stream().map(product ->
                new Product(product.getId(), product.getName(), product.getDescription(), product.getPrice()));
        return ResponseEntity.ok(products);
    }
}
