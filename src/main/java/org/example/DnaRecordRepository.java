package org.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DnaRecordRepository extends JpaRepository<DnaRecord, Long> {

    // Busca si ya existe un análisis con ese hash
    Optional<DnaRecord> findByDnaHash(String dnaHash);

    // Cuenta cuántos son mutantes (true) o humanos (false)
    long countByIsMutant(boolean isMutant);
}
