package htwberlin.webtech.ss24.fitnessplaner.web.persistence;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "exercise")
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "owner", nullable = false)
    private String owner;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sets", nullable = false)
    private int sets;

    @Column(name = "repetitions")
    private String repetitions;

    @Column(name = "weight")
    private String weight;

    @Column(name = "total_weight")
    private int totalWeight;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public ExerciseEntity() {}

    public ExerciseEntity(String name, String owner, int sets, List<Integer> repetitions, List<Integer> weight, int totalWeight, LocalDate createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.owner = owner;
        this.sets = sets;
        this.repetitions = convertListToString(repetitions);
        this.weight = convertListToString(weight);
        this.totalWeight = totalWeight;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public List<Integer> getRepetitions() {
        return convertStringToList(repetitions);
    }

    public void setRepetitions(List<Integer> repetitions) {
        this.repetitions = convertListToString(repetitions);
    }

    public List<Integer> getWeight() {
        return convertStringToList(weight);
    }

    public void setWeight(List<Integer> weight) {
        this.weight = convertListToString(weight);
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    private String convertListToString(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        return list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }

    private List<Integer> convertStringToList(String str) {
        if (str == null || str.isEmpty()) {
            return List.of();
        }
        return Arrays.stream(str.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
}
