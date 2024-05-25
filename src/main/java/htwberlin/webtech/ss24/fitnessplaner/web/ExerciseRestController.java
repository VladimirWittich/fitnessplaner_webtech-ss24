package htwberlin.webtech.ss24.fitnessplaner.web;

import htwberlin.webtech.ss24.fitnessplaner.model.Exercise;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseManipulationRequest;
import htwberlin.webtech.ss24.fitnessplaner.web.service.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workoutplan")
public class ExerciseRestController {

    private final ExerciseService exerciseService;

    public ExerciseRestController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/exercises")
    public ResponseEntity<List<Exercise>> fetchExercise() {
        return ResponseEntity.ok(exerciseService.findAll());
    }

    @GetMapping("/exercise/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long id) {
        Exercise exercise = exerciseService.findById(id);
        if (exercise == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercise);
    }

    @PutMapping("/exercise/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable Long id, @RequestBody ExerciseManipulationRequest request) {
        Exercise updatedExercise = exerciseService.update(id, request);
        if (updatedExercise == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedExercise);
    }

    @DeleteMapping("/exercise/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        boolean success = exerciseService.deleteById(id);
        if (!success) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
