package com.aprisoft.message.queue.app.repositories;

import com.aprisoft.message.queue.app.entities.AdhereHistory;
import com.aprisoft.message.queue.app.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdhereHistoryRepository extends JpaRepository<AdhereHistory, Long> {
    List<AdhereHistory> findByPatient(Patient patient);
}
