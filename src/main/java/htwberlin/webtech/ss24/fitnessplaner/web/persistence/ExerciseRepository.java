package htwberlin.webtech.ss24.fitnessplaner.web.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
    List<ExerciseEntity> findByName(String name);
    List<ExerciseEntity> findByNameContainingIgnoreCase(String name);
    List<ExerciseEntity> findByOwner(String owner);
}
