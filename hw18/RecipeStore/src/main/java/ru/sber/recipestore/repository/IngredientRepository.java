package ru.sber.recipestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sber.recipestore.model.Ingredient;

import java.util.Optional;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
    Optional<Ingredient> findByName(String name);
}
