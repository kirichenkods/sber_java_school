package ru.sber.recipestore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = "public", name = "ingredient")
public class Ingredient {
    @Id
    @SequenceGenerator(name = "pk_ingredient_seq", sequenceName = "ingredient_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_ingredient_seq")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true, length = 150)
    private String name;

    @OneToMany(mappedBy = "ingredient",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<RecipeBody> recipeBodies = new ArrayList<>();
}
