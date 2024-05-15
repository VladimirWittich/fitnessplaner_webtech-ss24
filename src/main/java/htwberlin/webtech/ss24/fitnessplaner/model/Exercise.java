package htwberlin.webtech.ss24.fitnessplaner.model;

import java.util.List;

public record Exercise (
        String name,
        int sets,
        List<Integer> repetitions,
        List<Integer> weight) {

    // Berechnung des Gesamtgewichts
    public int totalWeight() {
        int totalWeight = 0;
        for (int i = 0; i < repetitions.size(); i++) {
            totalWeight += repetitions.get(i) * weight.get(i);
        }
        return totalWeight;
    }

    public Exercise () {}
}
