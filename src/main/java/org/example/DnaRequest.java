package org.example;

// Esta clase es como una caja para recibir los datos que nos mandan
public class DnaRequest {
    private String[] dna;

    // Constructor vacío
    public DnaRequest() {}

    // Métodos para leer y guardar el ADN (Getters y Setters)
    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}