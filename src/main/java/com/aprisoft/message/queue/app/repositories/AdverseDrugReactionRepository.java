package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.AdverseDrugReaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdverseDrugReactionRepository extends JpaRepository<AdverseDrugReaction, Long> {
}
