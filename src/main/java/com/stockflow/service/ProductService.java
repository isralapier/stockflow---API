package com.stockflow.service;

import com.stockflow.model.Product;
import com.stockflow.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    ////////OBTENER TODOS LOS PRODUCTOS //////
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //////OBTENER PRODUCTO POR ID ////////
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /////////CREAR PRODUCTO////////

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }


    ////////ACTUALIZA UN PRODUCTO////////

    public Optional<Product> updateProduct(Long id, Product productDetails) {

        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDetails.getName());
                    product.setSku(productDetails.getSku());
                    product.setPrice(productDetails.getPrice());
                    product.setStock(productDetails.getStock());

                    return productRepository.save(product);
                });
    }

    //////ELIMINAR UN PRODUCTO ////////////

    public boolean deleteProduct(Long id) {

        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return true;
                })
                .orElse(false);
    }
}

