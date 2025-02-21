package ru.sber.recipestore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sber.recipestore.model.Recipe;
import ru.sber.recipestore.service.RecipeService;

@RequiredArgsConstructor
@Controller
public class RecipePostController {
    private final RecipeService service;

    @PostMapping("/create")
    public String addRecipe(@ModelAttribute Recipe recipe) {
        service.saveRecipe(recipe);

        return "redirect:/start";
    }

    @PostMapping("/edit/{recipeId}")
    public String editRecipe(@PathVariable int recipeId, Model model) {
        Recipe recipe = service.findById(recipeId);
        model.addAttribute("recipe", recipe);
        return "edit";
    }
}
