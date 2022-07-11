package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.AdrHistory;
import com.aprisoft.message.queue.app.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdrHistoryRepository extends JpaRepository<AdrHistory, Long> {

    List<AdrHistory> findByPatient(Patient patient);
}
