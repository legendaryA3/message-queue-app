package com.aprisoft.message.queue.app.repositories;

import com.aprisoft.message.queue.app.entities.Facility;
import com.aprisoft.message.queue.app.entities.Hts;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HtsRepository extends JpaRepository<Hts, Long> {
    Hts findByFacilityAndClientCode(Facility facility, String code);

    List<Hts> findByFacility(Facility facility, Pageable pageable);

    List<Hts> findByFacility(Facility facility);

    Hts findByFacilityAndSurnameAndOtherNamesAndGenderAndDateBirth(Facility facility, String surname, String otherNames, String gender, LocalDate dateBirth);

    Optional<Hts> findByUuid(String uuid);

    @Query(value = "select * from hts where facility_id = ?1 and extra->'source'->>'type' = 'mobile' and " +
        "cast(extra->'source'->>'date' as date ) > ?2 and archived = false", nativeQuery = true)
    List<Hts> getMobileHts(Long facilityId, LocalDate date);
}
