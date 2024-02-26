package it.cws.inventoryservice.service;

import it.cws.inventoryservice.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProduct();
    Product findProduct(String id);


}
