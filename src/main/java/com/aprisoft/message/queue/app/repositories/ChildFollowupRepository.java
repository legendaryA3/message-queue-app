package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.Child;
import com.aprisoft.message.queue.app.entities.Facility;
import com.aprisoft.message.queue.app.entities.ChildFollowup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ChildFollowupRepository extends JpaRepository<ChildFollowup, Long> {
    List<ChildFollowup> findByChildAndDateVisit(Child child, LocalDate date);

    List<ChildFollowup> findByChild(Child child);

    List<ChildFollowup> findByFacility(Facility facility);

    Long countByFacility(Facility facility);

    Long countByChild(Child child);

    Optional<ChildFollowup> findByUuid(String uuid);
}
