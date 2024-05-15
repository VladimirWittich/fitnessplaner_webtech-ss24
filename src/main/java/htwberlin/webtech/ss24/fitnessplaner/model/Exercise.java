package htwberlin.webtech.ss24.fitnessplaner.model;

public record Exercise (
    String name,
    int sets,
    int repetitions,
    double weight,
    double totalweight){
}
