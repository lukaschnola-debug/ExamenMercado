package org.example;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class MutantController {

    private final MutantService mutantService;
    private final StatsService statsService;

    // Conectamos el controlador con los servicios
    public MutantController(MutantService mutantService, StatsService statsService) {
        this.mutantService = mutantService;
        this.statsService = statsService;
    }

    // Endpoint 1: Detectar Mutante (POST /mutant)
    @PostMapping("/mutant")
    public ResponseEntity<Void> verificarMutante(@RequestBody DnaRequest peticion) {
        boolean esMutante = mutantService.analizarAdn(peticion.getDna());

        if (esMutante) {
            return ResponseEntity.ok().build(); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden
        }
    }

    // Endpoint 2: Ver Estadísticas (GET /stats)
    @GetMapping("/stats")
    public ResponseEntity<StatsResponse> verEstadisticas() {
        StatsResponse stats = statsService.getEstadisticas();
        return ResponseEntity.ok(stats);
    }
}