package ru.sber.recipestore.service;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.sber.recipestore.controller.RecipeGetController;
import ru.sber.recipestore.model.Recipe;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Epic("Recipe Store Service")
@Feature("Recipe Get Controller")
@WebMvcTest(RecipeGetController.class)
public class RecipeGetControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private RecipeService service;

    @Test
    @Story("Получение стартовой страницы")
    @Description("Проверяет что start возвращает представление index")
    public void givenStartEndpoint_whenRequested_thenIndexViewIsReturned() throws Exception {
        mockMvc.perform(get("/start"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    @Story("Поиск рецептов")
    @Description("Проверяет что find возвращает представление recipes и добавляет атрибуты в модель")
    public void givenFindEndpoint_whenSearchedByName_thenRecipesViewIsReturned() throws Exception {
        String recipeName = "Салат";
        List<Recipe> mockRecipes = Arrays.asList(new Recipe(), new Recipe());

        when(service.findRecipe(recipeName)).thenReturn(mockRecipes);

        mockMvc.perform(get("/find").param("recipeName", recipeName))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipes"))
                .andExpect(model().size(2))
                .andExpect(view().name("recipes"));
    }

    @Test
    @Story("Удаление рецепта")
    @Description("Проверяет что delete/{recipeName} возвращает статус 302 и перенаправляет на start")
    public void givenDeleteEndpoint_whenRecipeDeleted_thenRedirectToStart() throws Exception {
        String recipeName = "Салат";

        mockMvc.perform(get("/delete/" + recipeName))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/start"));
    }
}
