package it.cws.inventoryservice.service;

import it.cws.inventoryservice.dao.ProductRepository;
import it.cws.inventoryservice.entities.Product;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class ProdcutServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProdcutServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product findProduct(String id) {
        return productRepository.findById(id).get();
    }
}
