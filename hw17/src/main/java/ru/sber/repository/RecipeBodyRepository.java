package ru.sber.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sber.model.RecipeBody;

@Repository
public interface RecipeBodyRepository extends CrudRepository<RecipeBody, Integer> {
}
