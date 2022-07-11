package com.aprisoft.message.queue.app.repositories;

import com.aprisoft.message.queue.app.entities.Observation;
import com.aprisoft.message.queue.app.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObservationRepository extends JpaRepository<Observation, String> {
    List<Observation> findByPatientAndType(Patient patient, String type);
}
