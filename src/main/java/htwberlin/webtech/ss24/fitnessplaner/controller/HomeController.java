package htwberlin.webtech.ss24.fitnessplaner.controller;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
public class HomeController {
    @GetMapping("/")
    public String hello(Principal principal) {
        return "Hello, " + principal.getName();
    }  }