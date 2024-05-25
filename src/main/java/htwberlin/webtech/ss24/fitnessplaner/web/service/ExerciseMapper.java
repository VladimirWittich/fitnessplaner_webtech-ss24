package htwberlin.webtech.ss24.fitnessplaner.web.service;

import htwberlin.webtech.ss24.fitnessplaner.model.Exercise;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseEntity;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper {

    public ExerciseEntity toEntity(Exercise exercise) {
        return new ExerciseEntity(exercise.name(), exercise.sets(), exercise.repetitions(), exercise.weight());
    }

    public Exercise toRecord(ExerciseEntity entity) {
        return new Exercise(entity.getName(), entity.getSets(), entity.getRepetitions(), entity.getWeights());
    }
}