package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.Facility;
import com.aprisoft.message.queue.app.entities.Province;
import com.aprisoft.message.queue.app.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
    List<Facility> findByState(State state);

    List<Facility> findByLgaAndActiveTrue(Province lga);

    @Query(value = "select distinct f.* from patient p " +
            "join facility f on p.facility_id = f.id",
            nativeQuery = true)
    List<Facility> findDistinctByFacilityA();
}
