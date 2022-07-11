package com.aprisoft.message.queue.app.repositories;

import com.aprisoft.message.queue.app.entities.Facility;
import com.aprisoft.message.queue.app.repositories.projections.ReportedDates;
import com.aprisoft.message.queue.app.entities.Laboratory;
import com.aprisoft.message.queue.app.entities.Patient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {
    List<Laboratory> findByPatientAndDateResultReceived(Patient patient, LocalDate date);

    List<Laboratory> findByPatient(Patient patient, Pageable pageable);

    List<Laboratory> findByFacility(Facility facility, Pageable pageable);

    List<Laboratory> findByPatient(Patient patient);

    List<Laboratory> findByPatientOrderByDateResultReceived(Patient patient);

    @Query("select v from Laboratory v where v.patient = ?1")
    List<ReportedDates> findVisitsByPatient(Patient patient);

    Optional<Laboratory> findByUuid(String uuid);

    @Query(value = "select * from laboratory, jsonb_array_elements(lines) with ordinality a(l) where cast(jsonb_extract_path_text(l,'lab_test_id') as integer) = 16 " +
            "and patient_id = ?1 and archived = false and date_result_received <= current_date order by date_result_received desc",
            nativeQuery = true)
    List<Laboratory> findViralLoadTestByPatient(Long patientId);

    @Query(value = "select * from laboratory, jsonb_array_elements(lines) with ordinality a(l) where cast(jsonb_extract_path_text(l,'lab_test_id') as integer) = 1 " +
            "and patient_id = ?1 and archived = false and date_result_received <= current_date order by date_result_received desc",
            nativeQuery = true)
    List<Laboratory> findCD4TestByPatient(Long patientId);

    List<Laboratory> findByPatientAndDateResultReceivedBefore(Patient patient, LocalDate date, Pageable pageable);

    List<Laboratory> findByPatientAndDateResultReceived(Patient patient, LocalDate date, Pageable pageable);
}
