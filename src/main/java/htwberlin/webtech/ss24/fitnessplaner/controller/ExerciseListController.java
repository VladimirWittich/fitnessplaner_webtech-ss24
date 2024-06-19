package htwberlin.webtech.ss24.fitnessplaner.controller;

import htwberlin.webtech.ss24.fitnessplaner.model.Exercise;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/workoutplan")
public class ExerciseListController {

    @GetMapping()
    public List<Exercise> getExercise() {
        // Erstelle eine Übung mit Wiederholungen und Gewicht
        Exercise exercise = new Exercise("Bench", "vladimirwittich@web.de", 3, new ArrayList<>(), new ArrayList<>(), 0,  LocalDateTime.now());

        return List.of(exercise);
    }


}