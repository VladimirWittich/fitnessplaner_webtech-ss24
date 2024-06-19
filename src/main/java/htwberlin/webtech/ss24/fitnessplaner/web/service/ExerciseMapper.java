package htwberlin.webtech.ss24.fitnessplaner.web.service;

import htwberlin.webtech.ss24.fitnessplaner.model.Exercise;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ExerciseMapper {

    public ExerciseEntity toEntity(Exercise exercise) {
        ExerciseEntity entity = new ExerciseEntity();
        entity.setName(exercise.name());
        entity.setOwner(exercise.owner());
        entity.setSets(exercise.sets());
        entity.setRepetitions(exercise.repetitions()); // Direkte Zuweisung der Listen
        entity.setWeight(exercise.weight()); // Direkte Zuweisung der Listen
        entity.setTotalWeight(exercise.totalWeight());
        entity.setCreatedAt(exercise.createdAt());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public Exercise toRecord(ExerciseEntity entity) {
        return new Exercise(
                entity.getName(),
                entity.getOwner(),
                entity.getSets(),
                entity.getRepetitions(),
                entity.getWeight(),
                entity.getTotalWeight(),
                entity.getCreatedAt()
        );
    }
}
