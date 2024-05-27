package htwberlin.webtech.ss24.fitnessplaner.web.service;

import htwberlin.webtech.ss24.fitnessplaner.model.Exercise;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseEntity;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseManipulationRequest;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        List<ExerciseEntity> entities = exerciseRepository.findAll();
        return entities.stream()
                .map(exerciseMapper::toRecord)
                .collect(Collectors.toList());
    }

    public Exercise findById(Long id) {
        Optional<ExerciseEntity> entity = exerciseRepository.findById(id);
        return entity.map(exerciseMapper::toRecord).orElse(null);
    }

    public Exercise update(Long id, ExerciseManipulationRequest request) {
        Optional<ExerciseEntity> entityOptional = exerciseRepository.findById(id);
        if (entityOptional.isPresent()) {
            ExerciseEntity entity = entityOptional.get();
            entity.setName(request.getName());
            entity.setSets(request.getSets());
            entity.setRepetitions(request.getRepetitions());
            entity.setWeights(request.getWeight());
            entity = exerciseRepository.save(entity);
            return exerciseMapper.toRecord(entity);
        }
        return null;
    }

    public boolean deleteById(Long id) {
        if (exerciseRepository.existsById(id)) {
            exerciseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Exercise create(ExerciseManipulationRequest request) {
        ExerciseEntity entity = new ExerciseEntity(
                request.getName(),
                request.getSets(),
                request.getRepetitions(),
                request.getWeight()
        );
        entity = exerciseRepository.save(entity);
        return exerciseMapper.toRecord(entity);
    }
}
