package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<Drug, Long> {
}
