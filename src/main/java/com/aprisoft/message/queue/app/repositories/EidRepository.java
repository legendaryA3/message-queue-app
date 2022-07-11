package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.Facility;
import com.aprisoft.message.queue.app.entities.Eid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EidRepository extends JpaRepository<Eid, Long> {
    List<Eid> findByFacilityAndLabNo(Facility facility, String labNo);

    Optional<Eid> findByUuid(String uuid);
}
