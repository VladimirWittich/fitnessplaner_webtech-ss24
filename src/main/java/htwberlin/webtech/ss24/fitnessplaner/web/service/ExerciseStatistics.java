package htwberlin.webtech.ss24.fitnessplaner.web.service;


public class ExerciseStatistics {
    private String exerciseName;
    private int improvement;

    public ExerciseStatistics(String exerciseName, int improvement) {
        this.exerciseName = exerciseName;
        this.improvement = improvement;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getImprovement() {
        return improvement;
    }
}
