package htwberlin.webtech.ss24.fitnessplaner.web.persistence;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "exercise")
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sets", nullable = false)
    private int sets;

    @Column(name = "repetitions")
    private String repetitions;

    @Column(name = "weights")
    private String weights;

    public ExerciseEntity() {}

    public ExerciseEntity(String name, int sets, List<Integer> repetitions, List<Integer> weights) {
        this.name = name;
        this.sets = sets;
        this.repetitions = convertListToString(repetitions);
        this.weights = convertListToString(weights);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Integer> getWeights() {
        return convertStringToList(weights);
    }

    public void setWeights(List<Integer> weights) {
        this.weights = convertListToString(weights);
    }

    public List<Integer> getRepetitionsAsList() {
        return Arrays.stream(repetitions.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    public void setRepetitionsFromList(List<Integer> repetitions) {
        this.repetitions = String.join(",", repetitions.stream()
                .map(String::valueOf)
                .collect(Collectors.toList()));
    }

    // Similar methods for weights

    private String convertListToString(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        return list.stream()
                .map(Object::toString)
                .reduce((a, b) -> a + "," + b)
                .orElse("");
    }

    private List<Integer> convertStringToList(String str) {
        if (str == null || str.isEmpty()) {
            return List.of();
        }
        String[] strArray = str.split(",");
        return Arrays.stream(strArray)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
}
