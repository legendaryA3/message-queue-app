package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.Anc;
import com.aprisoft.message.queue.app.entities.Facility;
import com.aprisoft.message.queue.app.entities.Patient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AncRepository extends JpaRepository<Anc, Long> {
    Anc findFirstByPatientOrderByDateVisitDesc(Patient patient);

    Anc findFirstByPatientOrderByDateVisit(Patient patient);

    List<Anc> findByPatientAndDateVisit(Patient patient, LocalDate date);

    List<Anc> findByPatient(Patient patient);

    List<Anc> findByFacility(Facility facility, Pageable pageable);

    Long countByFacility(Facility facility);

    List<Anc> findByPatientOrderByDateVisitDesc(Patient patient);

    Optional<Anc> findByUuid(String uuid);
}
