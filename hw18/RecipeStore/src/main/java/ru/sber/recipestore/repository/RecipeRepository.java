package ru.sber.recipestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sber.recipestore.model.Recipe;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
    List<Recipe> findAllByNameContainingIgnoreCase(String name);

    Optional<Recipe> findByName(String name);

    @Override
    void deleteById(Integer id);
}
