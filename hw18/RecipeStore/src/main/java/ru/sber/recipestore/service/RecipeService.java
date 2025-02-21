package ru.sber.recipestore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sber.recipestore.model.Ingredient;
import ru.sber.recipestore.model.Recipe;
import ru.sber.recipestore.repository.IngredientRepository;
import ru.sber.recipestore.repository.RecipeBodyRepository;
import ru.sber.recipestore.repository.RecipeRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeBodyRepository recipeBodyRepository;

    /**
     * Поиск ранее сохраненного рецепта по его id
     */
    public Recipe findById(int recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        return recipe.orElse(new Recipe());
    }

    /**
     * Поиск ранее сохраненного рецепта.
     * Если ввести часть названия, то найдет все соответствия.
     */
    public List<Recipe> findRecipe(String recipeName) {
        return recipeRepository.findAllByNameContainingIgnoreCase(recipeName);
    }

    /**
     * Удалить рецепт по имени
     */
    @Transactional
    public void deleteRecipe(String recipeName) {
        Optional<Recipe> recipe = recipeRepository.findByName(recipeName);
        if (recipe.isPresent()) {
            Recipe recipeForDel = recipe.get();
            recipeBodyRepository.deleteAll(recipeForDel.getRecipeBody());
            recipeRepository.delete(recipeForDel);
        }
    }

    /**
     * Сохраняет рецепт.
     * Если такой уже есть в базе, то обновит его описание и тело
     */
    @Transactional
    public void saveRecipe(Recipe recipe) {
        // сначала сохраняются ингредиенты и берутся их id
        recipe.getRecipeBody().forEach(body -> {
            Ingredient ingredient = body.getIngredient();
            Optional<Ingredient> ingredientDB = ingredientRepository.findByName(
                    ingredient.getName());
            if (ingredientDB.isPresent()) {
                body.setIngredient(ingredientDB.get());
            } else {
                Ingredient ingredientWithId = ingredientRepository.save(ingredient);
                body.setIngredient(ingredientWithId);
            }
        });
        Optional<Recipe> recipeFromDB = recipeRepository.findByName(recipe.getName());
        // если рецепт был ранее сохранен, то обновятся его описание и тело
        if (recipeFromDB.isPresent()) {
            //сначала обновляется описание рецепта
            recipeFromDB.get().setDescription(recipe.getDescription());
            recipeRepository.save(recipeFromDB.get());

            //удаляется старое тело рецепта
            recipeBodyRepository.deleteAll(recipeFromDB.get().getRecipeBody());

            //добавляется новое тело
            recipeFromDB.get().setRecipeBody(recipe.getRecipeBody());
            recipeFromDB.get().getRecipeBody().forEach(body -> body.setRecipe(recipeFromDB.get()));
            recipeBodyRepository.saveAll(recipeFromDB.get().getRecipeBody());
        } else {
            Recipe recipeWithId = recipeRepository.save(recipe);
            recipe.getRecipeBody().forEach(body -> body.setRecipe(recipeWithId));
            recipeBodyRepository.saveAll(recipe.getRecipeBody());
        }
    }
}