package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.Assessment;
import com.aprisoft.message.queue.app.entities.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
    Assessment findByClientCodeAndFacility(String clientCode, Facility facility);

    Optional<Assessment> findByUuid(String uuid);

    @Query(value = "select * from assessment where facility_id = ?1 and extra->'source'->>'type' = 'mobile' and " +
        "cast(extra->'source'->>'date' as date ) > ?2 and archived = false", nativeQuery = true)
    List<Assessment> getMobileAssessments(Long facilityId, LocalDate date);
}
