package ru.sber.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.sber.db.DataSource;
import ru.sber.model.Ingredient;
import ru.sber.model.Recipe;
import ru.sber.model.RecipeBody;
import ru.sber.model.UnitType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RecipeBodyDAO {
    private final SimpleJdbcInsert jdbcInsert;
    private final JdbcTemplate jdbcTemplate;

    public RecipeBodyDAO(DataSource dataSource) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("recipe_body")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Сохраняет тело рецепта
     *
     * @param recipeBodies список строк тела рецепта
     */
    public void saveAll(List<RecipeBody> recipeBodies) {
        Map<String, Object> params = new HashMap<>();
        SqlParameterSource[] batch = new SqlParameterSource[recipeBodies.size()];
        for (int i = 0; i < recipeBodies.size(); i++) {
            RecipeBody recipeBody = recipeBodies.get(i);
            params.put("recipe_id", recipeBody.getRecipe().getId());
            params.put("ingredient_id", recipeBody.getIngredient().getId());
            params.put("quantity", recipeBody.getQuantity());
            params.put("unit", recipeBody.getUnit().name());
            MapSqlParameterSource parameterSource = new MapSqlParameterSource(params);
            batch[i] = parameterSource;
        }

        jdbcInsert.executeBatch(batch);
    }

    /**
     * Удалить тело рецепта по его id
     *
     * @param recipeId id рецепта, тело которого будет удалено
     */
    public void deleteByRecipeId(int recipeId) {
        String sql = "DELETE FROM recipe_body WHERE recipe_id = ?";
        jdbcTemplate.update(sql, recipeId);
    }

    /**
     * Находит тело рецепта по его id
     *
     * @param recipeId id рецепта
     */
    public List<RecipeBody> findByRecipeId(Integer recipeId) {
        String sql = "SELECT rb.id, rb.quantity, rb.unit, r.id AS recipe_id, " +
                     "r.name AS recipe_name, r.description AS recipe_description, " +
                     "i.id AS ingredient_id, i.name AS ingredient_name " +
                     "FROM recipe_body rb " +
                     "JOIN recipe r ON rb.recipe_id = r.id " +
                     "JOIN ingredient i ON rb.ingredient_id = i.id " +
                     "WHERE rb.recipe_id = ?";
        return jdbcTemplate.query(sql, new Object[]{recipeId}, (rs, rowNum) -> {
            RecipeBody body = new RecipeBody();
            body.setId(rs.getInt("id"));
            body.setQuantity(rs.getDouble("quantity"));
            body.setUnit(UnitType.valueOf(rs.getString("unit")));

            Recipe recipe = new Recipe();
            recipe.setId(rs.getInt("recipe_id"));
            recipe.setName(rs.getString("recipe_name"));
            recipe.setDescription(rs.getString("recipe_description"));
            body.setRecipe(recipe);

            Ingredient ingredient = new Ingredient();
            ingredient.setId(rs.getInt("ingredient_id"));
            ingredient.setName(rs.getString("ingredient_name"));
            body.setIngredient(ingredient);

            return body;
        });
    }
}
