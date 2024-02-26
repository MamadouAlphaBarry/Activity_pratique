package it.cws.inventoryservice.web;

import it.cws.inventoryservice.entities.Product;
import it.cws.inventoryservice.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")

public class ProductRestController {
private ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('USER')")
    public List<Product> index(){
        return  productService.findAllProduct();
    }
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public String save(){
        return  "Index Procut";
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public Product findById(@PathVariable(value = "id") String id){
        return  productService.findProduct(id);
    }
    @GetMapping("/auth")
    @PreAuthorize("hasRole('HR')")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }
}
