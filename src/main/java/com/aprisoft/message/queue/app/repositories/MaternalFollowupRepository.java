package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.MaternalFollowup;
import com.aprisoft.message.queue.app.entities.Patient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MaternalFollowupRepository extends JpaRepository<MaternalFollowup, Long> {
    List<MaternalFollowup> findByPatientAndDateVisit(Patient patient, LocalDate date);

    List<MaternalFollowup> findByPatient(Patient patient);

    List<MaternalFollowup> findByPatient(Patient patient, Pageable pageable);

    Optional<MaternalFollowup> findByUuid(String uuid);
}
