package com.aprisoft.message.queue.app.repositories;


import com.aprisoft.message.queue.app.entities.OiHistory;
import com.aprisoft.message.queue.app.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OiHistoryRepository extends JpaRepository<OiHistory, Long> {
    List<OiHistory> findByPatient(Patient patient);
}
