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


    @GetMapping("/{id}") // GET für eine bestimmte Übung
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long id) {
        Exercise exercise = exerciseService.findById(id);
        if (exercise == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercise);
    }

    @PostMapping // POST für das Erstellen einer Übung
    public ResponseEntity<Exercise> createExercise(@RequestBody ExerciseManipulationRequest request) {
        Exercise exercise = exerciseService.create(request);
        return ResponseEntity.ok(exercise);
    }

    @PutMapping("/{id}") // PUT für das Aktualisieren einer Übung
    public ResponseEntity<Exercise> updateExercise(@PathVariable Long id, @RequestBody ExerciseManipulationRequest request) {
        Exercise updatedExercise = exerciseService.update(id, request);
        if (updatedExercise == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedExercise);
    }

    @DeleteMapping("/{id}") // DELETE für das Löschen einer Übung
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        boolean success = exerciseService.deleteById(id);
        if (!success) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
