package com.stockflow.service;

import com.stockflow.dto.ProductRequest;
import com.stockflow.dto.ProductResponse;
import com.stockflow.model.Product;
import com.stockflow.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // OBTENER TODOS LOS PRODUCTOS
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }
    // OBTENER PRODUCTO POR ID
    public Optional<ProductResponse> getProductById(Long id) {
        return productRepository.findById(id)
                .map(this::toResponse);
    }

    //CREAR PRODUCTO
    public ProductResponse createProduct(ProductRequest request) {

        Product product = new Product();

        product.setName(request.name());
        product.setSku(request.sku());
        product.setPrice(request.price());
        product.setStock(request.stock());

        Product savedProduct = productRepository.save(product);

        return toResponse(savedProduct);
    }

    // ACTUALIZAR PRODUCTO
    public Optional<ProductResponse> updateProduct(
            Long id,
            ProductRequest request) {

        return productRepository.findById(id)
                .map(product -> {

                    product.setName(request.name());
                    product.setSku(request.sku());
                    product.setPrice(request.price());
                    product.setStock(request.stock());

                    Product updatedProduct = productRepository.save(product);

                    return toResponse(updatedProduct);
                });
    }

    //BORRAR PRODUCTO
    public boolean deleteProduct(Long id) {

        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return true;
                })
                .orElse(false);
    }

    // METODO TORESPONSE
    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getSku(),
                product.getPrice(),
                product.getStock()
        );
    }
}