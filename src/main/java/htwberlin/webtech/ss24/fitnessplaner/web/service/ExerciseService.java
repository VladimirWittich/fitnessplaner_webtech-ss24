package htwberlin.webtech.ss24.fitnessplaner.web.service;

import htwberlin.webtech.ss24.fitnessplaner.model.Exercise;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseEntity;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseManipulationRequest;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> findAll() {
        List<ExerciseEntity> exercises = exerciseRepository.findAll();
        return exercises.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Exercise findById(Long id) {
        var exerciseEntity = exerciseRepository.findById(id);
        return exerciseEntity.map(this::transformEntity).orElse(null);
    }



    public Exercise update(Long id, ExerciseManipulationRequest request) {
        var exerciseEntityOptional = exerciseRepository.findById(id);
        if (exerciseEntityOptional.isEmpty()) {
            return null;
        }

        var exerciseEntity = exerciseEntityOptional.get();
        exerciseEntity.setName(request.getName());
        exerciseEntity.setSets(request.getSets());
        exerciseEntity.setRepetitions(request.getRepetitions());
        exerciseEntity.setWeight(request.getWeight());
        exerciseEntity = exerciseRepository.save(exerciseEntity);

        return transformEntity(exerciseEntity);
    }

    public boolean deleteById(Long id) {
        if (!exerciseRepository.existsById(id)) {
            return false;
        }

        exerciseRepository.deleteById(id);
        return true;
    }

    private Exercise transformEntity(ExerciseEntity exerciseEntity) {
        return new Exercise(
                exerciseEntity.getId(),      // id hinzufügen
                exerciseEntity.getName(),
                exerciseEntity.getSets(),
                exerciseEntity.getRepetitions(),
                exerciseEntity.getWeight()
        );
    }

}