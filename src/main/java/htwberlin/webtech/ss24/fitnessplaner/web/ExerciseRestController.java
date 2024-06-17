package htwberlin.webtech.ss24.fitnessplaner.web;

import htwberlin.webtech.ss24.fitnessplaner.model.Exercise;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseManipulationRequest;
import htwberlin.webtech.ss24.fitnessplaner.web.service.ExerciseService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@RestController
@RequestMapping("/workoutplan")
public class ExerciseRestController {

    private final ExerciseService exerciseService;

    public ExerciseRestController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/current-user")
    public String getCurrentUserEmail(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            return userDetails.getUsername(); // Zugriff auf den Benutzernamen
        } else {
            throw new IllegalStateException("User not authenticated");
        }
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long id) {
        Exercise exercise = exerciseService.findById(id);
        if (exercise == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercise);
    }

    @CrossOrigin
    @GetMapping("/all")
    public List<Exercise> getAllExercises() {
        return exerciseService.findAll();
    }

    @CrossOrigin
    @GetMapping("/search")
    public ResponseEntity<List<Exercise>> searchExercise(@RequestParam String query) {
        List<Exercise> exercises = exerciseService.searchByName(query);
        if (exercises.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercises);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<String> createExercise(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Exercise exercise) {
        if (userDetails != null) {
            // Hier kannst du den authentifizierten Benutzer verwenden, z.B. zur Speicherung der Übung
            String username = userDetails.getUsername();
            // Hier den Code für die Speicherung der Übung in der Datenbank oder andere Logik einfügen
            return ResponseEntity.ok("Exercise created successfully for user: " + username);
        } else {
            throw new IllegalStateException("User not authenticated");
        }
    }



    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable Long id, @RequestBody ExerciseManipulationRequest request) {
        Exercise exercise = exerciseService.update(id, request);
        if (exercise == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exercise);
    }

    @CrossOrigin
    @DeleteMapping("/{id}") // DELETE für das Löschen einer Übung
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        boolean success = exerciseService.deleteById(id);
        if (!success) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}