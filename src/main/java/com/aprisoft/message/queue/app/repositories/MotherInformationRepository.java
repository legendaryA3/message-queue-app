package com.aprisoft.message.queue.app.repositories;

import com.aprisoft.message.queue.app.entities.MotherInformation;
import com.aprisoft.message.queue.app.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MotherInformationRepository extends JpaRepository<MotherInformation, Long> {
    Optional<MotherInformation> findByPatient(Patient patient);

    Optional<MotherInformation> findByUuid(String uuid);
}
