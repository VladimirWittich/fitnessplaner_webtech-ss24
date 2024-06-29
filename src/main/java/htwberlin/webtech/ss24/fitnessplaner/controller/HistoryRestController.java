package htwberlin.webtech.ss24.fitnessplaner.controller;

import htwberlin.webtech.ss24.fitnessplaner.model.Exercise;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import htwberlin.webtech.ss24.fitnessplaner.web.service.ExerciseService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/history")
public class HistoryRestController {

    private final ExerciseService exerciseService;

    public HistoryRestController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises(@RequestParam(required = false) String owner, @AuthenticationPrincipal OidcUser user) {
        if (user != null) {
            System.out.println("User email: " + user.getEmail());
            System.out.println("Token claims: " + user.getClaims());
        } else {
            System.out.println("User is null or not authenticated");
        }

        String userEmail = user != null ? user.getEmail() : null;
        List<Exercise> exercises = exerciseService.findByOwner(owner != null ? owner : userEmail);
        return ResponseEntity.ok(exercises);
    }
}
