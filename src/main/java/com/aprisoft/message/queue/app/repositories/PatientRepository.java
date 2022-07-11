package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query(value = "SELECT * FROM patient WHERE last_modified = :lastModified",
            nativeQuery = true)
    List<Patient> findByLastModified (@Param("lastModified") LocalDateTime lastModified);

    List<Patient> findByLastModifiedGreaterThan(LocalDateTime localDateTime);

    long countByLastModifiedIsGreaterThan (LocalDateTime localDateTime);

    @Query(value = "select count(*) as cnt from patient where uuid not in (select cast(patient_id as text) from data_sync)",
    nativeQuery = true)
    Integer countNewPatients ();

}
