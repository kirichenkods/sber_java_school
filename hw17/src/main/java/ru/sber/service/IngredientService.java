package ru.sber.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.repository.IngredientRepository;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository repository;
}
