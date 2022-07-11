package com.aprisoft.message.queue.app.repositories;

import com.aprisoft.message.queue.app.entities.Facility;
import com.aprisoft.message.queue.app.entities.Encounter;
import com.aprisoft.message.queue.app.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EncounterRepository extends JpaRepository<Encounter, Long> {
    Encounter findByPatientAndDateVisit(Patient patient, LocalDate date);

    Optional<Encounter> findByUuid(String uuid);

    List<Encounter> findByFacilityAndLastModifiedAfter(Facility facility, LocalDateTime lastModified);
}
