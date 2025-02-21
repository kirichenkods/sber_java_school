package ru.sber.recipestore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.recipestore.model.Ingredient;
import ru.sber.recipestore.repository.IngredientRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository repository;

    public Ingredient save(Ingredient ingredient) {
        Optional<Ingredient> ingredientDB = repository.findByName(ingredient.getName());
        // если в базе такого нет, то сохранить
        ingredient = ingredientDB.isPresent() ?
                ingredientDB.get() :
                repository.save(ingredient);
        return ingredient;
    }
}
