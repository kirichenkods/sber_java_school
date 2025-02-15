package ru.sber.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.sber.db.DataSource;
import ru.sber.model.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RecipeDAO {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final RecipeBodyDAO recipeBodyDAO;

    public RecipeDAO(DataSource dataSource, RecipeBodyDAO recipeBodyDAO) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("recipe")
                .usingGeneratedKeyColumns("id");

        this.recipeBodyDAO = recipeBodyDAO;
    }

    /**
     * Сохраняет рецепт, перед сохранением идет проверка существования этого рецепта в базе,
     * если такой уже есть, то обновит описание ранее сохраненного рецепта.
     */
    public Recipe save(Recipe recipe) {
        Optional<Recipe> recipeDB = findByName(recipe.getName());
        if (recipeDB.isEmpty()) {
            BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(recipe);
            int id = jdbcInsert.executeAndReturnKey(params).intValue();
            recipe.setId(id);
            return recipe;
        }

        String sql = "UPDATE recipe SET description = ? WHERE id = ?";
        jdbcTemplate.update(sql, recipe.getDescription(), recipeDB.get().getId());
        return recipeDB.get();
    }

    /**
     * Поиск рецепта по имени
     */
    public Optional<Recipe> findByName(String name) {
        String sql = "SELECT * FROM recipe WHERE name = ?";
        RowMapper<Recipe> rowMapper = ((rs, rowNum) -> new Recipe(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                new ArrayList<>()));
        return jdbcTemplate.query(sql, rowMapper, name)
                .stream()
                .findFirst();
    }

    /**
     * Удаление рецепта
     */
    public void delete(Recipe recipe) {
        String sql = "DELETE FROM recipe r WHERE r.id = ?";
        jdbcTemplate.update(sql, recipe.getId());
    }

    /**
     * Находит все рецепты по названию, или его части
     * @param name имя рецепта, или часть имени
     * @return список всех найденных рецептов
     */
    public List<Recipe> findAllByNameContainingIgnoreCase(String name) {
        String sql = "SELECT r.id, r.name, r.description " +
                     "FROM recipe r " +
                     "WHERE r.name ILIKE ?;";
        String pattern = "%" + name + "%";
        List<Recipe> recipes = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Recipe.class), pattern);
        recipes.forEach(recipe -> recipe.setRecipeBody(recipeBodyDAO.findByRecipeId(recipe.getId())));

        return recipes;
    }
}
