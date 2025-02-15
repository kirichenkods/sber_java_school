package ru.sber.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeBody {
    private Integer id;
    private Recipe recipe;
    private Ingredient ingredient;
    private Double quantity;
    private UnitType unit;

    @Override
    public String toString() {
        return ingredient.getName() + " " + quantity + " " + unit.toString();
    }
}
