package htwberlin.webtech.ss24.fitnessplaner.model;

import java.time.LocalDateTime;
import java.util.List;

public record Exercise(
        String name,
        int sets,
        List<Integer> repetitions,
        List<Integer> weight,
        int totalWeight,
        Long ownerId, // ownerId als Feld hinzufügen
        LocalDateTime createdAt
) {}