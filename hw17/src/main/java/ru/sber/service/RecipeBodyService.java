package ru.sber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.repository.RecipeBodyRepository;

@Service
@RequiredArgsConstructor
public class RecipeBodyService {
    private final RecipeBodyRepository repository;
}
