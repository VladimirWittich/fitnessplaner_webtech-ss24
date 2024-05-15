package htwberlin.webtech.ss24.fitnessplaner.controller;

import htwberlin.webtech.ss24.fitnessplaner.model.Exercise;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/workoutplan")
@CrossOrigin(origins = "https://fitnessplaner-frontend-webtech-ss24-e6t9.onrender.com/workoutplan")

@RestController
public class ExerciseListController {

    @GetMapping()
    public List<Exercise> getExercise() {
        // Erstelle eine Übung mit Wiederholungen und Gewicht
        Exercise exercise = new Exercise("Bench", 2, List.of(10, 12), List.of(50, 55));

        // Gib eine Liste mit dieser Übung zurück
        return List.of(exercise);
    }
}
