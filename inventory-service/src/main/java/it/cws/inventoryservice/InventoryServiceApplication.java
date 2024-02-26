package it.cws.inventoryservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.cws.inventoryservice.dao.ProductRepository;
import it.cws.inventoryservice.entities.Product;
import org.hibernate.boot.ResourceLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class InventoryServiceApplication {
@Autowired
	private ResourceLoader resourceLoader;
	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository){
		return args -> {
			// Load the JSON file
			ClassPathResource resource = new ClassPathResource("products.json");

			// Parse JSON into an array of Product objects
			ObjectMapper objectMapper = new ObjectMapper();
			Product[] productsArray = objectMapper.readValue(resource.getInputStream(), Product[].class);

			// Save the products into the database
			List<Product> products = Arrays.asList(productsArray);
			productRepository.saveAll(products);
			/**ObjectMapper objectMapper = new ObjectMapper();
			List<Product> products = Arrays.asList(objectMapper.readValue(new File("products.json"), Product[].class));
			productRepository.saveAll(products);**/
			productRepository.save(Product.builder().id("P01").name("Computer").price(2300).quantity(5)
					.build());
			productRepository.save(Product.builder().id("P02").name("Printer").price(1200).quantity(10)
					.build());
			productRepository.save(Product.builder().id("P03").name("Smart Phone").price(4200).quantity(34)
					.build());
		};
	}

}
