package htwberlin.webtech.ss24.fitnessplaner.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record Exercise(
        String name,

        String owner,
        int sets,
        List<Integer> repetitions,
        List<Integer> weight,
        int totalWeight,
        LocalDate createdAt



) {}


