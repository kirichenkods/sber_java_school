package ru.sber.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.sber.db.DataSource;
import ru.sber.model.Ingredient;

import java.util.Optional;

@Repository
public class IngredientDAO {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public IngredientDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("ingredient")
                .usingGeneratedKeyColumns("id");
    }

    /**
     * Сохраняет ингредиент в базе, если такой уже есть в базе, то вернет сохраненный
     * @return возвращает сохраненный ингредиент
     */
    public Ingredient save(Ingredient ingredient) {
        Optional<Ingredient> ingredientDB = findByName(ingredient.getName());
        if (ingredientDB.isEmpty()) {
            BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(ingredient);
            int id = jdbcInsert.executeAndReturnKey(params).intValue();
            ingredient.setId(id);
            return ingredient;
        }

        return ingredientDB.get();
    }

    /**
     * Находит ингредиент в базе по имени
     * @param ingredientName имя ингредиента
     */
    public Optional<Ingredient> findByName(String ingredientName) {
        String sql = "SELECT * FROM ingredient WHERE name = ?";
        RowMapper<Ingredient> rowMapper = ((rs, rowNum) -> new Ingredient(
                rs.getInt("id"),
                rs.getString("name")));
        return jdbcTemplate
                .query(sql, rowMapper, ingredientName)
                .stream()
                .findFirst();
    }
}
