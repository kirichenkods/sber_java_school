package ru.sber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sber.model.Ingredient;
import ru.sber.model.Recipe;
import ru.sber.model.RecipeBody;
import ru.sber.model.UnitType;
import ru.sber.repository.IngredientDAO;
import ru.sber.repository.RecipeBodyDAO;
import ru.sber.repository.RecipeDAO;
import ru.sber.ui.UserInterface;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final UserInterface ui;
    private final RecipeDAO recipeDAO;
    private final RecipeBodyDAO recipeBodyDAO;
    private final IngredientDAO ingredientDAO;

    /**
     * Поиск ранее сохраненного рецепта.
     * Если ввести часть названия, то найдет все соответствия.
     */
    public void findRecipe() {
        ui.showMessage("Введите название рецепта, или его часть");
        String name = ui.getUserInput();
        List<Recipe> recipes = recipeDAO.findAllByNameContainingIgnoreCase(name);
        if (recipes.size() == 0) {
            ui.showMessage("Не найдено рецептов с таким названием");
        }
        StringBuilder builder = new StringBuilder();
        recipes.forEach(recipe -> {
            builder.append(recipe.getName()).append("\n");
            builder.append(recipe.getDescription()).append("\n");
            recipe.getRecipeBody().forEach(body -> builder.append(body.toString()).append("\n"));
            builder.append("\n");
        });
        ui.showMessage(builder.toString());
    }

    /**
     * Удалить рецепт по имени
     */
    @Transactional
    public void deleteRecipe() {
        ui.showMessage("Введите название рецепта для удаления");
        String name = ui.getUserInput();
        Optional<Recipe> recipe = recipeDAO.findByName(name);
        if (recipe.isPresent()) {
            Recipe recipeForDel = recipe.get();
            recipeBodyDAO.deleteByRecipeId(recipeForDel.getId());
            recipeDAO.delete(recipeForDel);
            ui.showMessage("Рецепт " + recipeForDel.getName() + " удален");
        } else {
            ui.showMessage("Не найден рецепт с таким именем");
        }
    }

    /**
     * Добавить новый рецепт, или обновить ранее сохраненный
     */
    @Transactional
    public void addOrUpdateRecipe() {
        Recipe recipe = new Recipe();
        ui.showMessage("Введите название рецепта");
        String recipeName = ui.getUserInput();
        recipe.setName(recipeName);

        ui.showMessage("Введите описание рецепта");
        String recipeDescription = ui.getUserInput();
        recipe.setDescription(recipeDescription);

        ui.showMessage("Добавление ингредиентов");
        String input = "";
        while (!input.equals("0")) {
            RecipeBody recipeBody = getOneRowRecipeBody();
            recipe.getRecipeBody().add(recipeBody);

            ui.showMessage("Добавить еще ингредиент?\n 1 - да, 0 - нет");
            input = ui.getUserInput();
        }

        Optional<Recipe> recipeFromDB = recipeDAO.findByName(recipeName);
        // если рецепт был ранее сохранен, то обновятся его описание и тело
        if (recipeFromDB.isPresent()) {
            recipeFromDB.get().setDescription(recipe.getDescription());
            recipe.getRecipeBody().forEach(body -> body.setRecipe(recipeFromDB.get()));
            recipeFromDB.get().setRecipeBody(recipe.getRecipeBody());
            recipeDAO.save(recipeFromDB.get());
            //удалить старое тело и сохранить новое
            recipeBodyDAO.deleteByRecipeId(recipeFromDB.get().getId());
            recipeBodyDAO.saveAll(recipeFromDB.get().getRecipeBody());
            ui.showMessage("рецепт " + recipeName + " обновлен");
        } else {
            Recipe recipeWithId = recipeDAO.save(recipe);
            recipe.getRecipeBody().forEach(body -> body.setRecipe(recipeWithId));
            recipe.setId(recipeWithId.getId());
            recipeBodyDAO.saveAll(recipe.getRecipeBody());
            ui.showMessage("рецепт " + recipeName + " сохранен");
        }
    }

    /**
     * Получить от пользователя тело рецепта (одну строку тела)
     */
    private RecipeBody getOneRowRecipeBody() {
        ui.showMessage("Введите название ингредиента");
        String ingredientName = ui.getUserInput();
        ui.showMessage("Введите количество");
        Double ingredientQuantity = Double.valueOf(ui.getUserInput());
        ui.showMessage("Введите единицы измерения");
        UnitType ingredientUnit = getUnitType();

        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientName);
        // сначала достать ингредиент из базы
        Optional<Ingredient> ingredientDB = ingredientDAO.findByName(ingredientName);
        // если в базе такого нет, то сохранить
        ingredient = ingredientDB.isPresent() ?
                ingredientDB.get() :
                ingredientDAO.save(ingredient);

        RecipeBody recipeBody = new RecipeBody();
        recipeBody.setIngredient(ingredient);
        recipeBody.setQuantity(ingredientQuantity);
        recipeBody.setUnit(ingredientUnit);

        return recipeBody;
    }

    /**
     * Получить от пользователя единицу измерения
     */
    private UnitType getUnitType() {
        ui.showMessage("""
                Введите 1 если ГРАММ
                Введите 2 если МИЛЛИЛИТР
                Введите 3 если ШТУКА
                """);
        String cmd = ui.getUserInput();
        switch (cmd) {
            case "1" -> {
                return UnitType.GRAM;
            }
            case "2" -> {
                return UnitType.MILLILITER;
            }
            default -> {
                return UnitType.PIECE;
            }
        }
    }
}
