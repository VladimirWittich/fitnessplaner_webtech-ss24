package htwberlin.webtech.ss24.fitnessplaner.controller;

import htwberlin.webtech.ss24.fitnessplaner.model.Exercise;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/workoutplan")
public class ExerciseListController {

    @GetMapping()
    public List<Exercise> getExercise() {
        // Erstelle eine Übung mit Wiederholungen und Gewicht
        Exercise exercise = new Exercise("Example", "vladimirwittich@web.de", 3, List.of(12, 10, 8), List.of(100, 100, 100), 3000, LocalDate.now());

        return List.of(exercise);
    }


}