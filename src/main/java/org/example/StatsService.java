package org.example;

import org.springframework.stereotype.Service;

@Service
public class StatsService {

    private final DnaRecordRepository repository;

    public StatsService(DnaRecordRepository repository) {
        this.repository = repository;
    }

    public StatsResponse getEstadisticas() {
        // Contamos usando la base de datos
        long countMutant = repository.countByIsMutant(true);
        long countHuman = repository.countByIsMutant(false);

        // Creamos el reporte con los números
        return new StatsResponse(countMutant, countHuman);
    }
}
