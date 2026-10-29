package com.stockflow.model;
import java.math.BigDecimal;

public class Product {

    //variables
    private Long id;
    private String name;
    private String sku;
    private BigDecimal price;
    private int stock;

    //constructores
 public Product(){

 }

 public Product(String name, String sku, BigDecimal price,int stock){

     this.name = name;
     this.sku = sku;
     this.price = price;
     this.stock = stock;

 }

    //getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
