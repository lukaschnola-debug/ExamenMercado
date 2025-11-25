package org.example;

import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class MutantService {

    private final MutantDetector detector;
    private final DnaRecordRepository repository;

    public MutantService(MutantDetector detector, DnaRecordRepository repository) {
        this.detector = detector;
        this.repository = repository;
    }

    public boolean analizarAdn(String[] dna) {
        // 1. Calculamos el "Hash" (DNI único) del ADN
        String hash = calcularHash(dna);

        // 2. Preguntamos a la base de datos si ya existe
        Optional<DnaRecord> existente = repository.findByDnaHash(hash);
        if (existente.isPresent()) {
            // Si ya existe, devolvemos el resultado guardado (ahorramos trabajo)
            return existente.get().isMutant();
        }

        // 3. Si es nuevo, usamos el Detector
        boolean esMutante = detector.esMutante(dna);

        // 4. Guardamos el resultado en la base de datos para la próxima
        DnaRecord nuevoRegistro = new DnaRecord(hash, esMutante);
        repository.save(nuevoRegistro);

        return esMutante;
    }

    // Método matemático para convertir el Array en un código único (SHA-256)
    private String calcularHash(String[] dna) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            StringBuilder sb = new StringBuilder();
            for (String s : dna) {
                sb.append(s);
            }
            byte[] encodedhash = digest.digest(sb.toString().getBytes());

            // Convertir bytes a texto hexadecimal
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error calculando hash", e);
        }
    }
}
