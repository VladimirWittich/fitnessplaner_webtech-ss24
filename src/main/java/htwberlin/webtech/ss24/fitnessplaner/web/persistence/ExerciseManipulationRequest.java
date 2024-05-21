package htwberlin.webtech.ss24.fitnessplaner.web.persistence;

public class ExerciseManipulationRequest {

    private String name;
    private int sets;
    private int repetitions;
    private int weight;

    public ExerciseManipulationRequest(String name, int sets, int repetitions, int weight) {
        this.name = name;
        this.sets = sets;
        this.repetitions = repetitions;
        this.weight = weight;
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

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
