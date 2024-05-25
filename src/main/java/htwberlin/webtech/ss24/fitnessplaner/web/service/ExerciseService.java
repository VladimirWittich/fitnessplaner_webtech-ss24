package htwberlin.webtech.ss24.fitnessplaner.web.service;

import htwberlin.webtech.ss24.fitnessplaner.model.Exercise;
import htwberlin.webtech.ss24.fitnessplaner.web.service.ExerciseMapper;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseEntity;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseManipulationRequest;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;

    public ExerciseService(ExerciseRepository exerciseRepository, ExerciseMapper exerciseMapper) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseMapper = exerciseMapper;
    }

    public List<Exercise> findAll() {
        List<ExerciseEntity> exercises = exerciseRepository.findAll();
        return exercises.stream()
                .map(exerciseMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Exercise findById(Long id) {
        var exerciseEntity = exerciseRepository.findById(id);
        return exerciseEntity.map(exerciseMapper::toRecord).orElse(null);
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
        exerciseEntity.setWeights(request.getWeight());
        exerciseEntity = exerciseRepository.save(exerciseEntity);

        return exerciseMapper.toRecord(exerciseEntity);
    }

    public boolean deleteById(Long id) {
        if (!exerciseRepository.existsById(id)) {
            return false;
        }

        exerciseRepository.deleteById(id);
        return true;
    }
}
