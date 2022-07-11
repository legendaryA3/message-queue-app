package com.aprisoft.message.queue.app.repositories;

import com.aprisoft.message.queue.app.entities.Biometric;
import com.aprisoft.message.queue.app.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BiometricRepository extends JpaRepository<Biometric, String> {
    List<Biometric> findByPatient(Patient patient);

    @Query(value = "select * from biometric where facility_id = ?1 and extra->'source'->>'type' = 'mobile' and " +
        "cast(extra->'source'->>'date' as date) > ?2 and archived = false", nativeQuery = true)
    List<Biometric> getMobileBiometric(Long facilityId, LocalDate date);
}
