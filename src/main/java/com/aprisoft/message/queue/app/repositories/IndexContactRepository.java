package com.aprisoft.message.queue.app.repositories;

import com.aprisoft.message.queue.app.entities.Facility;
import com.aprisoft.message.queue.app.entities.Hts;
import com.aprisoft.message.queue.app.entities.IndexContact;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IndexContactRepository extends JpaRepository<IndexContact, Long> {
    IndexContact findByClientCode(String code);

    IndexContact findByClientCodeAndFacility(String code, Facility facility);

    List<IndexContact> findByHts(Hts hts, Pageable pageable);

    Optional<IndexContact> findByUuid(String uuid);

    @Query(value = "select * from index_contact where facility_id = ?1 and extra->'source'->>'type' = 'mobile' and " +
        "cast(extra->'source'->>'date' as date ) > ?2 and archived = false", nativeQuery = true)
    List<IndexContact> getMobileIndexContacts(Long facilityId, LocalDate date);
}
