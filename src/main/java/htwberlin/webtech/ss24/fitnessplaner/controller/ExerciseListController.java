package htwberlin.webtech.ss24.fitnessplaner.controller;


import htwberlin.webtech.ss24.fitnessplaner.model.Exercise;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/workoutplan")
@RestController
@CrossOrigin(origins = "http://localhost:5173")


public class ExerciseListController {

    @GetMapping()
    public List<Exercise> getExercise() {
        return List.of(
                new Exercise("", 0)
        );
    }
}
