package ru.sber.recipestore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sber.recipestore.model.Recipe;
import ru.sber.recipestore.service.RecipeService;

import java.util.List;
@Controller
@RequiredArgsConstructor
public class RecipeGetController {
    private final RecipeService service;
    @GetMapping("/start")
    public String start() {
        return "index";
    }

    @GetMapping("/find")
    public String find(@RequestParam("recipeName") String recipeName, Model model) {
        List<Recipe> recipes = service.findRecipe(recipeName);
        model.addAttribute("recipeData", new Recipe());
        model.addAttribute("recipes", recipes);
        return "recipes";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "create";
    }

    @GetMapping("/delete/{recipeName}")
    public String deleteRecipe(@PathVariable String recipeName) {
        service.deleteRecipe(recipeName);
        return "redirect:/start";
    }
}
