package ru.sber.recipestore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.recipestore.model.RecipeBody;
import ru.sber.recipestore.repository.RecipeBodyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeBodyService {
    private final RecipeBodyRepository repository;

    public RecipeBody save(RecipeBody body) {
        return repository.save(body);
    }

    public void saveAll(List<RecipeBody> bodies) {
        repository.saveAll(bodies);
    }
}
