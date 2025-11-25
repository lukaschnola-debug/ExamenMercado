package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatsResponse {

    @JsonProperty("count_mutant_dna")
    private long countMutantDna;

    @JsonProperty("count_human_dna")
    private long countHumanDna;

    private double ratio;

    public StatsResponse(long countMutantDna, long countHumanDna) {
        this.countMutantDna = countMutantDna;
        this.countHumanDna = countHumanDna;

        // Evitamos dividir por cero si no hay humanos
        if (countHumanDna == 0) {
            this.ratio = countMutantDna > 0 ? 1.0 : 0.0;
        } else {
            this.ratio = (double) countMutantDna / countHumanDna;
        }
    }

    // Getters necesarios para que se genere el JSON
    public long getCountMutantDna() { return countMutantDna; }
    public long getCountHumanDna() { return countHumanDna; }
    public double getRatio() { return ratio; }
}
