package htwberlin.webtech.ss24.fitnessplaner.web.service;

import htwberlin.webtech.ss24.fitnessplaner.model.Exercise;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ExerciseMapper {

    public ExerciseEntity toEntity(Exercise exercise) {
        ExerciseEntity entity = new ExerciseEntity(
                exercise.name(),
                exercise.owner(),
                exercise.sets(),
                exercise.repetitions(),
                exercise.weight(),
                exercise.totalWeight(),
                exercise.ownerId(),
                LocalDateTime.now(),
                null
        );
        return entity;
    }


    public Exercise toRecord(ExerciseEntity entity) {
        return new Exercise(
                entity.getName(),
                entity.getOwner(),
                entity.getSets(),
                entity.getRepetitions(),
                entity.getWeights(),
                entity.getTotalWeight(),
                entity.getOwerId(),
                entity.getCreatedAt()
        );
    }


}