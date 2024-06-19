package htwberlin.webtech.ss24.fitnessplaner.web.service;

import htwberlin.webtech.ss24.fitnessplaner.model.Exercise;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseEntity;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseManipulationRequest;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;

    @Autowired
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

    public List<Exercise> searchByName(String name) {
        List<ExerciseEntity> exercises = exerciseRepository.findByName(name);
        return exercises.stream()
                .map(exerciseMapper::toRecord)
                .collect(Collectors.toList());
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

        return exerciseMapper.toRecord(exerciseEntity);
    }

    public Exercise create(ExerciseManipulationRequest request) {
        if (request.getOwner() == null) {
            throw new IllegalArgumentException("Owner darf nicht null sein.");
        }

        ExerciseEntity entity = new ExerciseEntity(
                request.getName(),
                request.getOwner(), // Use owner from request
                request.getSets(),
                request.getRepetitions(),
                request.getWeight(),
                request.getTotalWeight(),
                LocalDateTime.now(),
                null // updatedAt initialisieren
        );
        entity = exerciseRepository.save(entity);
        return exerciseMapper.toRecord(entity);
    }



    public boolean deleteById(Long id) {
        if (!exerciseRepository.existsById(id)) {
            return false;
        }

        exerciseRepository.deleteById(id);
        return true;
    }

    public List<Exercise> findByOwner(String owner) {
        List<ExerciseEntity> exercises = exerciseRepository.findByOwner(owner);
        return exercises.stream()
                .map(exerciseMapper::toRecord)
                .collect(Collectors.toList());
    }
}
