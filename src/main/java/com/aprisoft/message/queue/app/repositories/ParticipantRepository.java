package com.aprisoft.message.queue.app.repositories;

import com.aprisoft.message.queue.app.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
