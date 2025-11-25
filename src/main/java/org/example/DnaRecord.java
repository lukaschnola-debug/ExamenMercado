package org.example;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity // Esto le dice a Java que esta clase se debe guardar en Base de Datos
public class DnaRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // No permitimos ADNs repetidos (usaremos un hash)
    private String dnaHash;

    private boolean isMutant;

    // Constructores
    public DnaRecord() {}

    public DnaRecord(String dnaHash, boolean isMutant) {
        this.dnaHash = dnaHash;
        this.isMutant = isMutant;
    }

    // Getters y Setters necesarios
    public boolean isMutant() {
        return isMutant;
    }
}
