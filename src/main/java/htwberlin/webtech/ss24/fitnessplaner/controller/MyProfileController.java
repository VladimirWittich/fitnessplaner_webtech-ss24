package htwberlin.webtech.ss24.fitnessplaner.controller;


import htwberlin.webtech.ss24.fitnessplaner.model.Exercise;
import htwberlin.webtech.ss24.fitnessplaner.model.PersonData;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"https://fitnessplaner-frontend-webtech-ss24-e6t9.onrender.com", "http://localhost:5173"})
@RestController
@RequestMapping("/myprofile")
public class MyProfileController {

    @GetMapping()
    public List<PersonData> getPersonData() {
        // Erstelle eine Übung mit Wiederholungen und Gewicht
        PersonData personData = new PersonData(178, 90, "male");

        return List.of(personData);
    }


}
