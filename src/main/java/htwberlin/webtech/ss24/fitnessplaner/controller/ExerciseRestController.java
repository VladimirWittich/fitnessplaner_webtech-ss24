package htwberlin.webtech.ss24.fitnessplaner.controller;

import htwberlin.webtech.ss24.fitnessplaner.model.Exercise;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseManipulationRequest;
import htwberlin.webtech.ss24.fitnessplaner.web.service.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workoutplan")
public class ExerciseRestController {

    private final ExerciseService exerciseService;

    public ExerciseRestController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> exercises = exerciseService.findAll();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Exercise>> searchExercise(@RequestParam String query) {
        List<Exercise> exercises = exerciseService.searchByName(query);
        if (exercises.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercises);
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody ExerciseManipulationRequest request) {
        Exercise exercise = exerciseService.create(request);
        return ResponseEntity.ok(exercise);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable Long id, @RequestBody ExerciseManipulationRequest request) {
        Exercise exercise = exerciseService.update(id, request);
        if (exercise == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercise);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id, @AuthenticationPrincipal OidcUser user) {
        boolean success = exerciseService.deleteById(id);
        if (!success) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}