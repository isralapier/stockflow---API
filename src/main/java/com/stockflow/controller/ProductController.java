package com.stockflow.controller;

import com.stockflow.model.Product;
import com.stockflow.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    //constructor
    public ProductController(ProductRepository productRepository) {

        this.productRepository = productRepository;

    }
///////////GET/////////////////////////////////
    @GetMapping
    public List<Product> getProducts(){

        return productRepository.findAll();

    }

//////////////////POST//////////////////////////////////
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product){

        return productRepository.save(product);

    }
/////////////////PUT////////////////////////////////////////

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){

        return productRepository.findById(id)
                .map(ResponseEntity::ok)                          //findBY devuelve Optional<Product>
                .orElse(ResponseEntity.notFound().build());                                         //convierte
    }                                                             //devuelve
////////////////////////////////////PUT///////////////////

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody Product productDetails){

        return productRepository.findById(id)
                .map(product -> {

                    product.setName(productDetails.getName());
                    product.setSku(productDetails.getSku());
                    product.setPrice(productDetails.getPrice());
                    product.setStock(productDetails.getStock());

                    Product updatedProduct = productRepository.save(product);

                    return ResponseEntity.ok(updatedProduct);
                })
                .orElse(ResponseEntity.notFound().build());


    }
////////////////////////DELETE//////////////////////////////////////////////

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){

        return productRepository.findById(id)
                .map(product -> {

                    productRepository.delete(product);

                    return ResponseEntity.noContent().<Void>build();

                })
                .orElse(ResponseEntity.notFound().build());

    }
    
}
