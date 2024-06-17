package htwberlin.webtech.ss24.fitnessplaner.web;

import htwberlin.webtech.ss24.fitnessplaner.model.Exercise;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseManipulationRequest;
import htwberlin.webtech.ss24.fitnessplaner.web.service.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "https://profwider.github.io/webtech-frontend"})
@RequestMapping("/workoutplan")
public class ExerciseRestController {

    private final ExerciseService exerciseService;

    public ExerciseRestController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long id) {
        Exercise exercise = exerciseService.findById(id);
        if (exercise == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercise);
    }

    @CrossOrigin(origins = {"http://localhost:5173", "https://profwider.github.io/webtech-frontend"})
    @GetMapping("/all")
    public List<Exercise> getAllExercises(@RequestParam(value = "ownerId", required = false) Long ownerId) {
        if (ownerId == null) {
            return exerciseService.findAll();
        }
        return exerciseService.findByOwnerId(ownerId);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Exercise>> searchExercise(@AuthenticationPrincipal OidcUser user, @RequestParam String query) {
        List<Exercise> exercises = exerciseService.searchByName(query);
        if (exercises.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercises);
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@AuthenticationPrincipal OidcUser user, @RequestBody ExerciseManipulationRequest request) {
        // Erstelle ein Exercise-Objekt mit den Informationen aus der ExerciseManipulationRequest
        Exercise exercise = new Exercise(
                request.getName(),
                request.getSets(),
                request.getRepetitions(),
                request.getWeight(),
                request.getTotalWeight(),
                Long.parseLong(user.getSubject()), // Setze den ownerId auf die ID des angemeldeten Benutzers
                LocalDateTime.now() // Setze das Erstellungsdatum auf die aktuelle Zeit
        );
        exercise = exerciseService.create(request);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        boolean success = exerciseService.deleteById(id);
        if (!success) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
