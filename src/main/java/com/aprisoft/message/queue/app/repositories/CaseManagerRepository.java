package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.Facility;
import com.aprisoft.message.queue.app.entities.CaseManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CaseManagerRepository extends JpaRepository<CaseManager, Long> {
    List<CaseManager> findByFacilityOrderByName(Facility facility);

    Page<CaseManager> findByFacility(Facility facility, Pageable pageable);

    Optional<CaseManager> findByUuid(String uuid);
}
