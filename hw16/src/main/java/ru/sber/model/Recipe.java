package ru.sber.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private Integer id;
    private String name;
    private String description;
    private List<RecipeBody> recipeBody = new ArrayList<>();
}
