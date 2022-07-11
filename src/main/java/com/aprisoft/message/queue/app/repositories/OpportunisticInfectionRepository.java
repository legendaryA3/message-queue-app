package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.OpportunisticInfection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpportunisticInfectionRepository extends JpaRepository<OpportunisticInfection, Long> {
}
