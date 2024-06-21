package htwberlin.webtech.ss24.fitnessplaner.web;

import htwberlin.webtech.ss24.fitnessplaner.model.Exercise;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseManipulationRequest;
import htwberlin.webtech.ss24.fitnessplaner.web.service.ExerciseService;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;


@RestController
@RequestMapping("/workoutplan")
public class ExerciseRestController {

    private final ExerciseService exerciseService;
    private static final Logger logger = Logger.getLogger(ExerciseRestController.class.getName());

    public ExerciseRestController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Exercise>> searchExercise(@RequestParam String query) {
        List<Exercise> exercises = exerciseService.searchByName(query);
        if (exercises.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long id) {
        Exercise exercise = exerciseService.findById(id);
        if (exercise == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercise);
    }

    @GetMapping("/all")
    public List<Exercise> getAllExercises(@RequestParam(required = false) String owner) {
        if (owner != null) {
            return exerciseService.findByOwner(owner);
        } else {
            return exerciseService.findAll();
        }
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody ExerciseManipulationRequest request) {
        logger.log(Level.INFO, "Received request to create exercise: {0}", request);
        if (request.getOwner() == null || request.getOwner().isEmpty()) {
            logger.log(Level.SEVERE, "Error: Owner darf nicht null oder leer sein.");
            return ResponseEntity.badRequest().body(null);
        }
        try {
            Exercise createdExercise = exerciseService.create(request);
            logger.log(Level.INFO, "Successfully created exercise: {0}", createdExercise);
            return ResponseEntity.ok(createdExercise);
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Error creating exercise: {0}", e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }





    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable Long id, @RequestBody ExerciseManipulationRequest request) {
        Exercise exercise = exerciseService.update(id, request);
        if (exercise == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        boolean success = exerciseService.deleteById(id);
        if (!success) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
