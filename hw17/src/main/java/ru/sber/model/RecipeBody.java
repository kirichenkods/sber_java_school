package ru.sber.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "public", name = "recipe_body")
public class RecipeBody {
    @Id
    @SequenceGenerator(name = "pk_recipe_body_seq", sequenceName = "recipe_body_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_recipe_body_seq")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "quantity", nullable = false)
    private Double quantity;

    @Column(name = "unit", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private UnitType unit;

    @ManyToOne
    @JoinColumn(name = "recipe_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_recipe"))
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_ingredient"))
    private Ingredient ingredient;

    @Override
    public String toString() {
        return ingredient.getName() + " " + quantity + " " + unit.toString();
    }
}
