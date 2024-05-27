package htwberlin.webtech.ss24.fitnessplaner.web;

import htwberlin.webtech.ss24.fitnessplaner.model.Exercise;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseEntity;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseManipulationRequest;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseRepository;
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

    @GetMapping("/all")
    public List<Exercise> getAllExercises() {
        return exerciseService.findAll();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Exercise>> searchExercise(@RequestParam String query) {
        List<Exercise> exercises = exerciseService.searchByName(query);
        if (exercises.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercises);
    }

    @PostMapping // POST für das Erstellen einer Übung
    public ResponseEntity<?> createExercise(@RequestBody ExerciseManipulationRequest request) {
        // Überprüfen, ob alle erforderlichen Felder ausgefüllt sind
        if (request.getName() == null || request.getName().isEmpty() ||
                request.getSets() <= 0 || request.getRepetitions() == null || request.getWeight() == null) {
            return ResponseEntity.badRequest().body("Incomplete exercise data");
        }

        // Wenn die Validierung erfolgreich ist, erstellen Sie die Übung und speichern Sie sie in der Datenbank
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
