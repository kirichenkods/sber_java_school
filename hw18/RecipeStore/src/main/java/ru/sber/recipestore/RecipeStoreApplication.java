package ru.sber.recipestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RecipeStoreApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RecipeStoreApplication.class);
    }
}
