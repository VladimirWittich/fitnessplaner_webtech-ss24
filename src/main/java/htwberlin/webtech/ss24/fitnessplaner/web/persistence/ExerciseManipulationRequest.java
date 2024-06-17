package htwberlin.webtech.ss24.fitnessplaner.web.persistence;

import java.util.List;

public class ExerciseManipulationRequest {

    private String name;
    private int sets;
    private List<Integer> repetitions;
    private List<Integer> weight;
    private int totalWeight;
    private String ownerId;
    private String owner;

    public ExerciseManipulationRequest(String name, int sets, List<Integer> repetitions, List<Integer> weight, int totalWeight, String ownerId, String owner) {
        this.name = name;
        this.sets = sets;
        this.repetitions = repetitions;
        this.weight = weight;
        this.totalWeight = totalWeight;
        this.ownerId = ownerId;
        this.owner = owner;
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
        return repetitions;
    }

    public ExerciseManipulationRequest () {}
    public void setRepetitions(List<Integer> repetitions) {
        this.repetitions = repetitions;
    }

    public List<Integer> getWeight() {
        return weight;
    }

    public void setWeight(List<Integer> weight) {
        this.weight = weight;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {this.ownerId = ownerId;
    }
}