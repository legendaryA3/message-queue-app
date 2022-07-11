package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.PatientCaseManager;
import com.aprisoft.message.queue.app.entities.CaseManager;
import com.aprisoft.message.queue.app.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientCaseManagerRepository extends JpaRepository<PatientCaseManager, Long> {
    Optional<PatientCaseManager> findByPatient(Patient patient);

    List<PatientCaseManager> findByCaseManager(CaseManager caseManager);
}
