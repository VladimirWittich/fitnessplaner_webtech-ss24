package htwberlin.webtech.ss24.fitnessplaner.web.persistence;


import jakarta.persistence.*;

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

    @Column(name = "repetitions", nullable = false)
    private int repetitions;

    @Column(name = "weight", nullable = false)
    private int weight;

    public ExerciseEntity(String name, int sets, int repetitions, int weight) {
        this.name = name;
        this.sets = sets;
        this.repetitions = repetitions;
        this.weight = weight;
    }



    protected ExerciseEntity() {
    }

    public Long getId() {
        return id;
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