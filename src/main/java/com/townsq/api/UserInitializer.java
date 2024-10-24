package com.townsq.api;

import com.townsq.api.domain.product.Product;
import com.townsq.api.domain.user.User;
import com.townsq.api.domain.user.UserRole;
import com.townsq.api.repositories.ProductRepository;
import com.townsq.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("townsq_super") == null) {
            User user = new User();
            user.setUsername("townsq_super");
            user.setPassword("$2a$10$eOC1PG842F.hkjOOJQXUIOFjpYrmlNe9fhKHYzgN1w.y2s/X9gt0q"); // Lembre-se de usar uma senha hashed em produção
            user.setRole(UserRole.ADMIN);
            userRepository.save(user);
            System.out.println("Usuário 'admin' criado com sucesso!");
        }

        if(productRepository.findAll().isEmpty()) {
            Product product = new Product("Rice", "White rice", 10);
            productRepository.save(product);
            product = new Product("Bread", "Brown Bread", 5);
            productRepository.save(product);
            product = new Product("Tire", "18 size", 100);
            productRepository.save(product);
        }
    }
}
