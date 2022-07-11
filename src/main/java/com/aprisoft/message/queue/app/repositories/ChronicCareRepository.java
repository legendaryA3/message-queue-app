package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.repositories.projections.VisitDates;
import com.aprisoft.message.queue.app.entities.ChronicCare;
import com.aprisoft.message.queue.app.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ChronicCareRepository extends JpaRepository<ChronicCare, Long> {
    ChronicCare findByPatientAndDateVisit(Patient patient, LocalDate date);

    List<ChronicCare> findByPatient(Patient patient);

    Optional<ChronicCare> findByUuid(String uuid);

    @Query("select v from ChronicCare v where v.patient = ?1")
    List<VisitDates> findVisitsByPatient(Patient patient);
}
